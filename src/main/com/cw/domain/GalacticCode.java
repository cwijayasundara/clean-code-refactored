package main.com.cw.domain;

import main.com.cw.util.RomanCharToDecimalConverter;

/**
 * Created by cwijayasundara on 08/02/2017.
 */

public class GalacticCode {

    private String galacticCode;
    private char romanCode;
    private int decimal;
    private RomanCharToDecimalConverter romanCharToDecimalConverter;

    //constructor
    public GalacticCode(String galacticCode, char romanCode){
        romanCharToDecimalConverter = new RomanCharToDecimalConverter();
        this.galacticCode = galacticCode;
        this.romanCode = romanCode;
        this.decimal = assignDecimal(romanCode);
    }

    public String getGalacticCode() {
        return galacticCode;
    }

    public void setGalacticCode(String galacticCode) {
        this.galacticCode = galacticCode;
    }

    public char getRomanCode() {
        return romanCode;
    }

    public void setRomanCode(char romanCode) {
        this.romanCode = romanCode;
    }

    public int getDecimal() {
        return decimal;
    }

    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }

    private int assignDecimal(final char romanCode) {
        return romanCharToDecimalConverter.convertRomanToDecimal(romanCode);
    }
}
