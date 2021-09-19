package com.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class Order {
    @NotNull(message = "Food item array cannot be null.")
    public List<FoodItem> food;
    @NotNull(message = "Beverage item array cannot be null.")
    public List<BeverageItem> drink;

    public Order(){}
}
