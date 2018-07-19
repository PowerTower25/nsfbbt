package com.bbt.hackathon.nsfweb.controller;

import com.bbt.hackathon.nsfweb.data.UserInsights;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class InsightController {
    
    @CrossOrigin
    @RequestMapping("/insight")
    public UserInsights chatList(@RequestParam(value="email") String email) {
        return UserInsights.getInsight(email);
    }
}
