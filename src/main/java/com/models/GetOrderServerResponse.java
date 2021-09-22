package com.models;

import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class GetOrderServerResponse {
        public String credit;
        public String firstName;
        public String lastName;
        public String email;
        public double totalCost;
        public Order order;

        @ServerTimestamp
        public Date date;

        public GetOrderServerResponse(){}
}
