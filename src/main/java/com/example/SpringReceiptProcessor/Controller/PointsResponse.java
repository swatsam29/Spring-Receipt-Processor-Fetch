package com.example.SpringReceiptProcessor.Controller;

import java.util.List;

public class PointsResponse {
    private PointsBreakdown points;

    public PointsResponse(PointsBreakdown points) {
        this.points = points;
    }

    public PointsResponse(int zeroPoints, List<String> breakdownDetails) {
        this.points = new PointsBreakdown(zeroPoints, breakdownDetails);
    }

    public String displayPoints() {
        StringBuilder display = new StringBuilder();
        
        if (points.getTotalPoints() > 0) {
            display.append("Total Points: ").append(points.getTotalPoints()).append("\n");
            display.append("Breakdown:\n");

            for (String detail : points.getBreakdownDetails()) {
                display.append(" ").append(detail).append("\n");
            }
        } else {
            display.append("No points earned. Breakdown details:\n");
            for (String detail : points.getBreakdownDetails()) {
                display.append(" ").append(detail).append("\n");
            }
        }

        return display.toString();
    }
}
