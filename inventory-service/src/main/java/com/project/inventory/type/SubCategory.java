package com.project.inventory.type;

public enum SubCategory {

    //Category.BAKERY
    BREADS("Breads", Category.BAKERY),
    CAKES("Cakes", Category.BAKERY),
    COOKIES("Cookies", Category.BAKERY),
    PIES("Pies", Category.BAKERY),
    MUFFINS("Muffins", Category.BAKERY),
    WRAPS("Wraps", Category.BAKERY),

    //Category.BEVERAGE
    BOTTLED_WATER("Bottled Water", Category.BEVERAGES),
    CARBONATED("Carbonated", Category.BEVERAGES),
    COFFEE("Coffee", Category.BEVERAGES),
    JUICE("Juice", Category.BEVERAGES),
    TEA("Tea", Category.BEVERAGES),

    //Category.DAIRY_PRODUCT
    BUTTER("Butter", Category.DAIRY_PRODUCT),
    CHEESE("Cheese", Category.DAIRY_PRODUCT),
    MILK("Milk", Category.DAIRY_PRODUCT),
    YOGURT("Yoghurt", Category.DAIRY_PRODUCT),


    //Category.FRUITS_VEGETABLES
    FRUITS("Fruit", Category.FRUITS_VEGETABLES),
    VEGETABLES("Vegetables", Category.FRUITS_VEGETABLES);

    private final String name;
    private final Category category;
    SubCategory(String name, Category category){
        this.name = name;
        this.category = category;
    }
}
