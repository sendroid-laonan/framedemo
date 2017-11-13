package com.sendroids.framedemo.controller;

import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.rowset.serial.SerialArray;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "/index";
    }

    /**
     * 查询全部User
     * */
    @RequestMapping(value = "/user_list")
    public String selectUser(Model model, User user, @RequestParam(defaultValue = "1") Integer currentPage,
                             @RequestParam(defaultValue = "10") Integer size){
        if(currentPage<=0){
            currentPage = 1;
        }
        int total = userService.getCount();
        System.out.println("total:"+total);
        int lastPage = total/size;
        if(lastPage*size<total){
            lastPage = lastPage + 1;
        }
        if(currentPage>lastPage){
            currentPage = lastPage;
        }
        List<User> users = userService.findAllByState(currentPage,size);

        model.addAttribute("datas",users);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("lastPage",lastPage);
        model.addAttribute("Count",total);
        model.addAttribute("query",user);

        return "case/user/user_list";
    }

    /**
     * add_user页面跳转
     * */
    @RequestMapping(value = "/add_user",method = RequestMethod.GET)
    public String add_User(Model model){
        model.addAttribute("user",new User());
        return "case/user/add_user";
    }

    /**
     * 添加用户
     * */
    @RequestMapping(value = "/user_add",method = RequestMethod.POST)
    public String addUser(Model model, @Validated User user, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("user",user);
            return "case/user/add_user";
        }
        userService.save(user);
        return "redirect:/user/user_list";
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @param page 当前页
     * @param way （1 物理删除）（2 逻辑删除）
     * */
    @RequestMapping("/user_delete")
    @ExceptionHandler(IOException.class)
    public String deleteUser(@RequestParam(value = "id") long id,@RequestParam(value = "way") int way,
                             @RequestParam(defaultValue = "0") Integer page){
        if(way==1){
            userService.delete(id);
        }else {
            User newuser = userService.findUserById(id);
            newuser.setState(2);
            userService.update(newuser);
        }
        return "redirect:/user/user_list?page="+page;
    }

    @RequestMapping(value = "/update_user",method = RequestMethod.GET)
    public String update_User(@RequestParam(value = "id") long id,Model model,
                            @RequestParam(defaultValue = "1") Integer page){
        User user = userService.getUserById(id);
        model.addAttribute("page",page);
        Optional<User> optional = Optional.ofNullable(user);
        model.addAttribute("user",optional.orElseThrow(RuntimeException::new));

        return "case/user/add_user";
    }

    @RequestMapping(value = "/user_update",method = RequestMethod.POST)
    public String updateUser(User user){
        userService.update(user);
        return "user_list";
    }
}
