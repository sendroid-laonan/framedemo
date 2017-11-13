package com.sendroids.framedemo.controller;

import com.sendroids.framedemo.entity.Town;
import com.sendroids.framedemo.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/town")
public class TownController {

    @Autowired
    private TownService townService;

    @RequestMapping("/")
    public String index() {
        return "case/town/town_list";
    }

    @RequestMapping("/town_list")
    public String town_list() {
        return "case/town/town_list";
    }

    @RequestMapping(value = "/town_list.json",method = RequestMethod.GET)
    @ResponseBody
    public List<Town> town_list_json(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "100")int size) {
        List<Town> towns = townService.findAllByState(page,size);
        return towns;
    }

}
