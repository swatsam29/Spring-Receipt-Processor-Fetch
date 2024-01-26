// package com.example.SpringReceiptProcessor;

// import lombok.Data;
// import org.springframework.web.bind.annotation.*;

// import java.math.BigDecimal;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.UUID;

// @RestController
// @RequestMapping("/receipts")
// public class ReceiptController {

//     private final Map<String, Integer> pointsMap = new HashMap<>();

//     @PostMapping("/generate-id")
//     public GenerateIdResponse generateId(@RequestBody Receipt receipt) {
//         String generatedId = UUID.randomUUID().toString();
//         return new GenerateIdResponse(generatedId);
//     }

//     @GetMapping("/{id}/points")
//     public PointsResponse getPoints(@PathVariable String id) {
//         try {
//             Receipt receipt = lookupReceiptById(id);

//             if (receipt == null) {
//                 System.out.println("Receipt not found for ID: " + id);
//                 return new PointsResponse(0);
//             }

//             int awardedPoints = calculatePoints(receipt);
//             pointsMap.put(id, awardedPoints);

//             logReceiptDetails(id, receipt, awardedPoints);

//             return new PointsResponse(awardedPoints);
//         } catch (Exception e) {
//             handleException(e);
//             return new PointsResponse(0);
//         }
//     }

//     private Receipt lookupReceiptById(String id) {
//         // Implement logic to fetch receipt details by ID
//         // Return null if the receipt is not found (handle this in getPoints)
//         return new Receipt();
//     }

//     private void logReceiptDetails(String id, Receipt receipt, int awardedPoints) {
//         System.out.println("ID: " + id);
//         System.out.println("Retailer: " + receipt.getRetailer());
//         System.out.println("Total: " + receipt.getTotal());
//         System.out.println("Points awarded: " + awardedPoints);
//         System.out.println("Total Points: " + awardedPoints);
//     }

//     private void handleException(Exception e) {
//         e.printStackTrace();
//     }

//     private int calculatePoints(Receipt receipt) {
//         int points = calculateAlphanumericPoints(receipt.getRetailer()) +
//                 (isRoundDollarAmount(receipt.getTotal()) ? 50 : 0) +
//                 (isMultipleOfQuarter(receipt.getTotal()) ? 25 : 0) +
//                 calculateItemPoints(receipt.getItems().length);

//         for (Item item : receipt.getItems()) {
//             points += calculateDescriptionPoints(item.getShortDescription(), item.getPrice());
//         }

//         points += (isDayOdd(receipt.getPurchaseDate()) ? 6 : 0) +
//                 (isAfter2PMAndBefore4PM(receipt.getPurchaseTime()) ? 10 : 0);

//         return points;
//     }

//     private int calculateAlphanumericPoints(String retailer) {
//         return retailer.replaceAll("[^a-zA-Z0-9]", "").length();
//     }

//     private boolean isRoundDollarAmount(String total) {
//         double amount = Double.parseDouble(total);
//         return amount % 1 == 0;
//     }

//     private boolean isMultipleOfQuarter(String total) {
//         double amount = Double.parseDouble(total);
//         return BigDecimal.valueOf(amount).remainder(BigDecimal.valueOf(0.25)).equals(BigDecimal.ZERO);
//     }

//     private int calculateItemPoints(int totalItems) {
//         return (totalItems / 2) * 5;
//     }

//     private int calculateDescriptionPoints(String description, String price) {
//         int descriptionLength = description.trim().length();
//         return (descriptionLength % 3 == 0) ? (int) Math.ceil(Double.parseDouble(price) * 0.2) : 0;
//     }

//     private boolean isDayOdd(String purchaseDate) {
//         try {
//             int day = Integer.parseInt(new SimpleDateFormat("d").format(new SimpleDateFormat("yyyy-MM-dd").parse(purchaseDate)));
//             return day % 2 != 0;
//         } catch (ParseException e) {
//             return false;
//         }
//     }

//     private boolean isAfter2PMAndBefore4PM(String purchaseTime) {
//         try {
//             Date parsedTime = new SimpleDateFormat("HH:mm").parse(purchaseTime);
//             Date after2PM = new SimpleDateFormat("HH:mm").parse("14:00");
//             Date before4PM = new SimpleDateFormat("HH:mm").parse("16:00");
//             return parsedTime.after(after2PM) && parsedTime.before(before4PM);
//         } catch (ParseException e) {
//             return false;
//         }
//     }

//     @Data
//     public static class Receipt {
//         private String retailer;
//         private String purchaseDate;
//         private String purchaseTime;
//         private Item[] items;
//         private String total;
//     }

//     @Data
//     public static class Item {
//         private String shortDescription;
//         private String price;
//     }

//     @Data
//     public static class GenerateIdResponse {
//         private String id;

//         public GenerateIdResponse(String id) {
//             this.id = id;
//         }
//     }

//     @Data
//     public static class PointsResponse {
//         private int points;

//         public PointsResponse(int points) {
//             this.points = points;
//         }
//     }
// }
