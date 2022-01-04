package com.java.firebaseclient.services;

import com.models.PostChargeToCardRequest;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@Configuration
@PropertySource("classpath:environment.properties")
public class PaymentsService {
    @Value("${stripe.key.secret}")
    private String secretKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey = secretKey;
    }

    public Charge charge(PostChargeToCardRequest request)throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", request.getAmount());
        chargeParams.put("currency", request.getCurrency());
        chargeParams.put("description", request.getDescription());
        chargeParams.put("source", request.getStripeToken());
        chargeParams.put("receipt_email", request.getStripeEmail());
        return Charge.create(chargeParams);
    }
}
