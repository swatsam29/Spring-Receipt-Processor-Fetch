package com.example.SpringReceiptProcessor.Controller;

import java.util.List;

public class PointsResponse {
    public PointsBreakdown points;

    public PointsResponse(PointsBreakdown points) {
        this.points = points;
    }

    public PointsResponse(int zeroPoints, List<String> breakdownDetails) {
        this.points = new PointsBreakdown(zeroPoints, breakdownDetails);
    }
}
