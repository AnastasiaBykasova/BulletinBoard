package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
//    public String Home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    public String Home(Model model) {
        model.addAttribute("title", "Main page");
        return "homepage";
    }
}
