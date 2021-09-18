package com.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    public List<FoodItem> food;
    public List<BeverageItem> drink;

    public Order(){}
}
