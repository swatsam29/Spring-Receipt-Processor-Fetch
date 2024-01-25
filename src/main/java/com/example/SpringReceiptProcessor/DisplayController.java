package com.example.SpringReceiptProcessor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DisplayController {

    @GetMapping("/")
    public String index(){
        return "Hello Swati!!";
    }

    
}
