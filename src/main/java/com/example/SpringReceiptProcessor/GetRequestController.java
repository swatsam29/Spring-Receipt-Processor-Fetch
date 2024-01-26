package com.example.SpringReceiptProcessor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetRequestController {

    @GetMapping("/getreq")
    public String index(){
        return "Price: $35";
    }
}
