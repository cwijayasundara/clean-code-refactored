package main.com.cw.util;

/**
 * Created by cwijayasundara on 08/02/2017.
 */
public class RomanCharToDecimalConverter {

    public int convertRomanToDecimal(final char galCode) {
        switch (galCode) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
