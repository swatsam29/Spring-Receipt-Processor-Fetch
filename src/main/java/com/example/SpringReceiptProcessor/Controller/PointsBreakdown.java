package com.example.SpringReceiptProcessor.Controller;

import java.util.List;

public class PointsBreakdown {
    private int totalPoints;
    private List<String> breakdownDetails;

    public PointsBreakdown(int totalPoints, List<String> breakdownDetails) {
        this.totalPoints = totalPoints;
        this.breakdownDetails = breakdownDetails;
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

            if (breakdownDetails != null) {
                for (String detail : breakdownDetails) {
                    display.append(" ").append(detail).append("\n");
                }
            }
        } else {
            display.append("No points earned. Breakdown details:\n");
            if (breakdownDetails != null) {
                for (String detail : breakdownDetails) {
                    display.append(" ").append(detail).append("\n");
                }
            }
        }

        return display.toString();
    }
}
