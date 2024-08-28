package com.example.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RestController
@RequestMapping("/gmo-callback")
public class CallbackController {

    @PostMapping("/payment-status")
    public ResponseEntity<String> receivePaymentStatus(@RequestBody Map<String, String> payload) {
        System.out.println("---------------------");
        System.out.println(payload);
        System.out.println("---------------------");
        // Extract data from the payload
        String orderId = payload.get("OrderID");
        String status = payload.get("Status"); // Status of the payment, e.g., "0" for success
        String recurringId = payload.get("RecurringID");
        String amount = payload.get("Amount");
        String payDate = payload.get("PayDate");

        // Log the received data for debugging
        System.out.println("Received payment status callback:");
        System.out.println("OrderID: " + orderId);
        System.out.println("Status: " + status);
        System.out.println("RecurringID: " + recurringId);
        System.out.println("Amount: " + amount);
        System.out.println("PayDate: " + payDate);

        // Check status and process accordingly
        if ("0".equals(status)) {
            // Status "0" indicates a successful payment
            System.out.println("Payment successful for OrderID: " + orderId);
            // Update your system to reflect the successful payment
            // e.g., mark invoice as paid, send confirmation email, etc.
        } else {
            // Handle other statuses (failed, pending, etc.)
            System.out.println("Payment not successful for OrderID: " + orderId + " with status: " + status);
            // Update your system to reflect the failed payment
        }

        // Respond to GMO to acknowledge the receipt of the callback (usually 200 OK)
        return ResponseEntity.ok("Received");
    }
}
