package com.sendroids.framedemo.controller;

import com.sendroids.framedemo.entity.Role;
import com.sendroids.framedemo.entity.Town;
import com.sendroids.framedemo.entity.Users;
import com.sendroids.framedemo.service.TownService;
import com.sendroids.framedemo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService userService;
    private TownService townService;

    /**
     * 查询全部Users
     * */
    @GetMapping(value = "/index")
    public String selectUser(Model model, Users user, @RequestParam(defaultValue = "1") Integer currentPage,
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
        List<Users> users = userService.findAllByState(currentPage,size);

        model.addAttribute("datas",users);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("lastPage",lastPage);
        model.addAttribute("Count",total);
        model.addAttribute("query",user);

        return "case/users/user_list";
    }

    /**
     * add_user页面跳转
     * */
    @GetMapping(value = "/file")
    public String initAddUser(Model model){
        model.addAttribute("users",new Users());
        return "case/users/add_user";
    }

    /**
     * 添加用户
     * */
    @PostMapping(value = "/file")
    public String addUser(Model model, @Validated Users users, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("users", users);
            return "case/users/add_user";
        }
        userService.save(users);
        return "redirect:/users/index?order=desc";
    }

    /**
     * 删除用户
     * @param id 用户ID
     * @param page 当前页
     * @param way （1 物理删除）（2 逻辑删除）
     * */
    @GetMapping(value = "/delete")
    @ExceptionHandler(Exception.class)
    public String deleteUser(@RequestParam(value = "id") long id,@RequestParam(value = "way") int way,
                             @RequestParam(defaultValue = "0") int page){
        if(way==1){
            userService.delete(id);
        }else {
            Users newuser = userService.findUserById(id);
            newuser.setState(2);
            userService.update(newuser);
        }
        return "redirect:/users/index?page="+page;
    }

    @GetMapping(value = "/update")
    public String initUpdateUser(@RequestParam(value = "id") long id,Model model,
                              @RequestParam(defaultValue = "1") Integer page){
        Users users = userService.getUserById(id);
        model.addAttribute("page",page);
        model.addAttribute("role", users.getRoles().get(0));
        model.addAttribute("town",users.getTown());
        Optional<Users> optional = Optional.ofNullable(users);
        model.addAttribute("users",optional.orElseThrow(EntityNotFoundException::new));

        return "case/users/add_user";
    }

    @PutMapping(value = "/file")
    public String updateUser(Users users){
        userService.update(users);
        return "redirect:/users/index";
    }
}
