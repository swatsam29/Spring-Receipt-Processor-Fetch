package com.example.SpringReceiptProcessor.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.SpringReceiptProcessor.Model.ReceiptsData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/receipts")
public class MainController {

    private final Map<String, PointsBreakdown> MapPoints = new HashMap<>();
    private final Map<String, ReceiptsData> receiptsDataMap = new HashMap<>();

    @PostMapping("/generate-id")
    public GenerateIdResponse generateId(@RequestBody ReceiptDTO receipt) {
        String generatedId = UUID.randomUUID().toString();
        return new GenerateIdResponse(generatedId);
    }

    @GetMapping("/{id}/points")
    public PointsResponse getPoints(@PathVariable String id) {
        ReceiptsData receipt = lookupReceiptById(id);

        if (receipt != null) {
            PointsBreakdown totalPoints = PointsCalculator.calculatePoints(receipt);
            MapPoints.put(id, totalPoints);

            // Use the display method to get the formatted breakdown
            String breakdownDisplay = totalPoints.display();

            // You might want to log the breakdown or use it in your application
            System.out.println(breakdownDisplay);

            // Modify the PointsResponse creation based on totalPoints and breakdownDetails
            if (totalPoints.getTotalPoints() > 0) {
                return new PointsResponse(totalPoints);
            } else {
                return new PointsResponse(0, totalPoints.getBreakdownDetails());
            }
        } else {
            // Handle case where receipt is not found
            return new PointsResponse(0, List.of());
        }
    }

    private ReceiptsData lookupReceiptById(String id) {
        return receiptsDataMap.get(id);
    }

    static class PointsResponse {
        public PointsBreakdown points;

        public PointsResponse(PointsBreakdown points) {
            this.points = points;
        }

        public PointsResponse(int zeroPoints, List<String> breakdownDetails) {
            this.points = new PointsBreakdown(zeroPoints, breakdownDetails);
        }
    }
}
