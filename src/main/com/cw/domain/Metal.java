package main.com.cw.domain;

/**
 * Created by cwijayasundara on 08/02/2017.
 */
public class Metal {

    private String metalName;
    private float unitPrice;

    public Metal(String name){
        this.metalName = name;
    }

    public float calculateMetalUnitPriceInDecimal(int numberOfUnits, float price){
        float unitPrice = 0;
        if(numberOfUnits != 0){
            unitPrice = price/numberOfUnits;
        }
        return unitPrice;
    }

    public String getMetalName() {
        return metalName;
    }

    public void setMetalName(String metalName) {
        this.metalName = metalName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
