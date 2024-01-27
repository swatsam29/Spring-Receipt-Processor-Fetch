package com.example.SpringReceiptProcessor.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.example.SpringReceiptProcessor.Model.ReceiptsData;

@RestController
@RequestMapping("/receipts")
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    

    private final Map<String, ReceiptsData> receiptsDataMap = new HashMap<>();

    @PostMapping("/generate-id")
    public GenerateIdResponse generateId(@RequestBody ReceiptsData receipt) {
        String generatedId = UUID.randomUUID().toString();
        logger.info("ID is generated: {}", generatedId);
        receiptsDataMap.put(generatedId, receipt);
        return new GenerateIdResponse(generatedId);

        
    }

    @GetMapping("/{id}/points")
    public PointsBreakdown getPoints(@PathVariable String id) {
        ReceiptsData receipt = lookupReceiptById(id);

        if (receipt != null) {
            try {
                return PointsCalculator.calculatePoints(receipt);
            } catch (Exception e) {
                e.printStackTrace();
                return new PointsBreakdown(0, List.of("Error calculating points"));
            }
        } else {
            return new PointsBreakdown(0, List.of());
        }
    }

    private ReceiptsData lookupReceiptById(String id) {
        
        return receiptsDataMap.get(id);
    }
}
