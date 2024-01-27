package com.example.SpringReceiptProcessor.Controller;

import com.example.SpringReceiptProcessor.Model.ReceiptsData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointsCalculator {

    static Logger logger = LoggerFactory.getLogger(MainController.class);

    public static PointsBreakdown calculatePoints(ReceiptsData receipt) {
        int totalPoints = 0;
        List<String> breakdownDetails = new ArrayList<>();

      
        logger.info("Retailer: {}", receipt.getRetailer());
        logger.info("Total: {}", receipt.getTotal());



        totalPoints += calculateAlphanumericPoints(receipt.getRetailer());
        breakdownDetails.add(String.format("%d points - retailer name has %d characters", totalPoints, receipt.getRetailer().length()));

        if (isRoundDollarAmount(receipt.getTotal())) {
            totalPoints += 50;
            breakdownDetails.add("50 points - total is a round dollar amount with no cents");
        }

        if (isMultipleOfQuarter(receipt.getTotal())) {
            totalPoints += 25;
            breakdownDetails.add("25 points - total is a multiple of 0.25");
        }

        int itemPoints = calculateItemPoints(receipt.getItems().size());
        totalPoints += itemPoints;
        breakdownDetails.add(String.format("%d points - %d items (%d pairs @ 5 points each)", itemPoints, receipt.getItems().size(), itemPoints / 5));

        for (ReceiptsData.Item item : receipt.getItems()) {
            BigDecimal itemPrice = item.getPrice();
            int descriptionPoints = calculateDescriptionPoints(item.getShortDescription(), itemPrice);
            totalPoints += descriptionPoints;
        
            int roundedPoints = itemPrice.multiply(BigDecimal.valueOf(0.2))
                              .setScale(0, RoundingMode.UP)
                              .intValue();
        
            String breakdownDetail = String.format("%d Points - \"%s\" is %d characters (a multiple of 3)\n" +
                    "   item price of %s * 0.2 = %.2f, rounded up is %d points",
                    descriptionPoints, item.getShortDescription(), item.getShortDescription().trim().length(),
                    itemPrice, itemPrice.multiply(BigDecimal.valueOf(0.2)).doubleValue(), roundedPoints);
        
            breakdownDetails.add(breakdownDetail);
        }

        if (isDayOdd(receipt.getPurchaseDateTime())) {
            totalPoints += 6;
            breakdownDetails.add("6 points - purchase day is odd");
        }

        if (isAfter2PMAndBefore4PM(receipt.getPurchaseDateTime())) {
            totalPoints += 10;
            breakdownDetails.add("10 points - time of purchase is after 2:00 pm and before 4:00 pm");
        }

        return new PointsBreakdown(totalPoints, breakdownDetails);
    }

    private static int calculateAlphanumericPoints(String retailer) {
        return retailer.replaceAll("[^a-zA-Z0-9]", "").length();
    }

    private static boolean isRoundDollarAmount(BigDecimal total) {
        return total.stripTrailingZeros().scale() <= 0;
    }

    private static boolean isMultipleOfQuarter(BigDecimal total) {
        return total.remainder(new BigDecimal("0.25")).equals(BigDecimal.ZERO);
    }

    private static int calculateItemPoints(int totalItems) {
        return (totalItems / 2) * 5;
    }

    private static int calculateDescriptionPoints(String description, BigDecimal price) {
        int descriptionLength = description.trim().length();
        if (descriptionLength % 3 == 0) {
            double itemPrice = price.doubleValue();
            return (int) Math.ceil(itemPrice * 0.2);
        }
        return 0;
    }

    private static boolean isDayOdd(LocalDateTime localDateTime) {
        try {
            int day = localDateTime.getDayOfMonth();
            return day % 2 != 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isAfter2PMAndBefore4PM(LocalDateTime localDateTime) {
        try {
            int hour = localDateTime.getHour();
            return hour > 14 && hour < 16;
        } catch (Exception e) {
            return false;
        }
    }
}
