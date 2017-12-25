package com.sendroids.framedemo.controller;

import com.sendroids.framedemo.opencv.OpenCV;
import com.sendroids.framedemo.service.OpencvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;

@Controller
@RequestMapping("/opencv")
public class OpencvController {

    @Autowired
    private OpencvService opencvService;

    @GetMapping("/index")
    public String index(){
        return "opencv";
    }

    @PostMapping("/start")
    public String start(double stu[],
                        double ans[]){
        opencvService.start(stu, ans);
        return "opencv";
    }
}
