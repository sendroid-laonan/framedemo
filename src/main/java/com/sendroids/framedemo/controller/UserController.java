package com.sendroids.framedemo.controller;

import com.sendroids.framedemo.entity.User;
import com.sendroids.framedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    public String selectUser(Model model, User user, @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "10") Integer size){
//        Page<User> users = userService.findAllByPage(user, page, size);
        List<User> users = userService.findAllByState();
        model.addAttribute("datas",users);
        model.addAttribute("page",page);
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
}
