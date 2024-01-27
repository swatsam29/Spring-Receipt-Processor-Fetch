package com.example.SpringReceiptProcessor.Controller;

import java.util.List;

public class PointsBreakdown {
    public int totalPoints;
    public List<String> breakdownDetails;

    public PointsBreakdown(int totalPoints, List<String> breakdownDetails) {
        this.totalPoints = totalPoints;
        this.breakdownDetails = breakdownDetails;
    }

    public PointsBreakdown(String string) {
        // TODO Auto-generated constructor stub
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public List<String> getBreakdownDetails() {
        return breakdownDetails;
    }

    public String display() {
        StringBuilder display = new StringBuilder();

        if (totalPoints > 0) {
            display.append("Total Points: ").append(totalPoints).append("\n");
            display.append("Breakdown:\n");

            for (String detail : breakdownDetails) {
                display.append(" ").append(detail).append("\n");
            }

            
            display.append(" + ---------\n");
            display.append(" = ").append(totalPoints).append(" points\n");
        } else {
            display.append("No points earned. Breakdown details:\n");
            for (String detail : breakdownDetails) {
                display.append(" ").append(detail).append("\n");
            }
        }

        return display.toString().replaceAll("\"", "");
    }
}
