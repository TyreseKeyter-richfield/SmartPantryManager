package com.TyreseKeyter.smartpantrymanager;
import java.util.List;
public class Recipe {
    private int id;
    private String name;
    private String steps;
    private List<String>requiredingredients;
    public Recipe(int id, String name, String steps, List<String>requiredingredients){
        this.id=id;
        this.name=name;
        this.steps=steps;
        this.requiredingredients=requiredingredients;
    }
    public int getId(){return id;}
    public String getName(){return name;}
    public String getSteps(){return steps;}
    public List<String>getRequiredingredients(){return requiredingredients;}
}