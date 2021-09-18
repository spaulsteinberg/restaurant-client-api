package com.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodItem {
    public double price;
    public String category;
    public String main;
    public List<FoodAddition> additions;
    public List<String> subtractions;

    public FoodItem(){}
}
