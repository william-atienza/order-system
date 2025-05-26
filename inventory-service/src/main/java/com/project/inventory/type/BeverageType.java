package com.project.inventory.type;

public enum BeverageType {
    BOTTLED_WATER("Bottled Water"),
    CARBONATED("Carbonated"),
    COFFEE("Coffee"),
    JUICE("Juice"),
    TEA("Tea");

    private final String name;
    BeverageType(String name){
        this.name = name;
    }
}