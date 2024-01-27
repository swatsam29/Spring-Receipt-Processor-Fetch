package com.example.SpringReceiptProcessor.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;

import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SpringReceiptProcessor.Model.ReceiptsData;

@RestController
@RequestMapping("/receipts")
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    //StringBuilder display = new StringBuilder();

    

    private final Map<String, ReceiptsData> receiptsDataMap = new HashMap<>();
    

    @PostMapping("/generate-id")
    public GenerateIdResponse generateId(@RequestBody ReceiptsData receipt) {
        receipt.setPurchaseDateTime(LocalDateTime.now());
        String generatedId = UUID.randomUUID().toString();
        logger.info("ID is generated: {}", generatedId);
        PointsBreakdown pointsBreakdown = PointsCalculator.calculatePoints(receipt);
            receipt.setPoints(pointsBreakdown.getTotalPoints());
        receiptsDataMap.put(generatedId, receipt);
        return new GenerateIdResponse(generatedId);
        

        
    }

    @GetMapping("/{id}/points")
        public ResponseEntity<?> getPoints(@PathVariable String id) {
            if (id == null || id.isEmpty()) {
                return ResponseEntity.badRequest().body("The receipt ID is required");
            }
    
            ReceiptsData receipt = lookupReceiptById(id);
    
            if (receipt != null) {
                try {
                    PointsBreakdown pointsBreakdown = PointsCalculator.calculatePoints(receipt);

                    return ResponseEntity.ok(pointsBreakdown);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                         .body("Error calculating points");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("The receipt is invalid");
            }
        }
    

    private ReceiptsData lookupReceiptById(String id) {
        
        return receiptsDataMap.get(id);
    }
}
