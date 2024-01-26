package com.example.SpringReceiptProcessor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutRequestController {
      @GetMapping("/putreq")
    public String index(){
        return "Select a value";
    }
}

