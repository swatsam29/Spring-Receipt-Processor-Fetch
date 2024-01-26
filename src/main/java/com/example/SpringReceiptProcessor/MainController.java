package com.example.SpringReceiptProcessor;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/receipts")
public class MainController {

    private final Map<String, Integer> pointsMap = new HashMap<>();

    @PostMapping("/generate-id")
    public GenerateIdResponse generateId(@RequestBody ReceiptEntity receipt) {
        String generatedId = UUID.randomUUID().toString();
        return new GenerateIdResponse();
    }

}

   