package com.models;

import com.google.type.Date;

public class OrderRequest {
    private String Credit;
    private Date Date;
    private String FirstName;
    private String LastName;
    private String Email;
    private float TotalCost;
    private Order Order;

    public OrderRequest(){ }
    public OrderRequest(String credit, Date date, String firstname, String lastname, String email, float totalcost, Order order){
        this.Credit = credit;
        this.Date = date;
        this.FirstName = firstname;
        this.LastName = lastname;
        this.Email = email;
        this.TotalCost = totalcost;
        this.Order = order;
    }
    public OrderRequest(OrderRequest or){
        this.Credit = or.Credit;
        this.Date = or.Date;
        this.FirstName = or.FirstName;
        this.LastName = or.LastName;
        this.Email = or.Email;
        this.TotalCost = or.TotalCost;
        this.Order = or.Order;
    }
}
