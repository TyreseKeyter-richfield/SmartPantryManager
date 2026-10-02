package com.TyreseKeyter.smartpantrymanager;

public class Ingredient {
    private int id;
    private String name;
    private double quantity;
    private String unit;
    private String expirydate;

    public Ingredient(String name, double quantity, String unit, String expirydate){
        this.name=name;
        this.quantity=quantity;
        this.unit=unit;
        this.expirydate=expirydate;
    }
    public Ingredient(int id, String name, double quantity, String unit, String expirydate){
        this.id=id;
        this.name=name;
        this.quantity=quantity;
        this.unit=unit;
        this.expirydate=expirydate;
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public double getQuantity(){return quantity;}
    public void setQuantity(double quantity){this.quantity=quantity;}
    public String getUnit(){return unit;}
    public void setUnit(String unit){this.unit=unit;}
    public String getexpirydate(){return expirydate;}
    public void setexpirydate(String expirydate){this.expirydate=expirydate;}
}
