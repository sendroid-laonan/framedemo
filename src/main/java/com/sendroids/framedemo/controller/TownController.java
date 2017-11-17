package com.sendroids.framedemo.controller;

import com.sendroids.framedemo.entity.Town;
import com.sendroids.framedemo.entity.Users;
import com.sendroids.framedemo.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/file")
    public String initAdd(Model model) {
        model.addAttribute("town",new Town());
        return "case/town/add_town";
    }

    @PostMapping(value = "/file")
    public String addUser(Model model, @Validated Town town, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("town", town);
            return "case/town/add_town";
        }
        townService.save(town);
        return "redirect:/town/index";
    }


    @GetMapping(value = "/update")
    public String initUpdateTown(@RequestParam(value = "id") long id,Model model){
        Town town = townService.getTownById(id);
        Optional<Town> optional = Optional.ofNullable(town);
        model.addAttribute("town",optional.orElseThrow(EntityNotFoundException::new));

        return "case/town/add_town";
    }

    @PutMapping(value = "/file")
    public String updateUser(Town town){
        townService.update(town);
        return "redirect:/town/";
    }

    /**
     * 所有Date类型绑定统一时间格式
     * * */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
