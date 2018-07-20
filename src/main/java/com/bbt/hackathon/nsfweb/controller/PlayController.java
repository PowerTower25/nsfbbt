package com.bbt.hackathon.nsfweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlayController {
    
    @RequestMapping("/play")
    public String play() {
        return "play";
    }
}
