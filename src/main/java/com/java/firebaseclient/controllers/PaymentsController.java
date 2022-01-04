package com.java.firebaseclient.controllers;

import com.java.firebaseclient.services.PaymentsService;
import com.models.Currency;
import com.models.PostChargeToCardRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/client")
@Configuration
@PropertySource("classpath:environment.properties")
@SuppressWarnings("unused")
public class PaymentsController {

    @Value("${stripe.key.public}")
    private String stripePublicKey;
    @Autowired
    private PaymentsService paymentsService;

    @PostMapping("/payment/charge")
    public String pay(@RequestBody PostChargeToCardRequest request, Model model) throws StripeException {
        request.setDescription("Charging to card");
        request.setCurrency(Currency.USD);
        Charge charge = paymentsService.charge(request);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler
    public String handleError(StripeException ex){
        return ex.getMessage();
    }
}
