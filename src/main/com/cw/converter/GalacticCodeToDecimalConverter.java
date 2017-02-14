package main.com.cw.converter;

import main.com.cw.util.RomanCharToDecimalConverter;
import main.com.cw.validator.GalacticCodeFormatValidator;

/**
 * Created by cwijayasundara on 23/01/2017.
 * This class implement the generic NumberConverter interface and override the convert() to
 * convert a galactic code to the curresponding decimal / arabic number
 */
public class GalacticCodeToDecimalConverter implements NumberConverter {

    private GalacticCodeFormatValidator galacticCodeFormatValidator;

    private RomanCharToDecimalConverter romanCharToDecimalConverter;

    public GalacticCodeToDecimalConverter(){
        romanCharToDecimalConverter = new RomanCharToDecimalConverter();
        galacticCodeFormatValidator = new GalacticCodeFormatValidator();
    }

    /* convert the given galactic code to curresponding decimal value
     *
     */
    public int convert(final String numberToConvert){

        String galacticString = numberToConvert.toUpperCase();
        int galacticSum=0;
        int counter=0;
        int galacticeValueLength = galacticString.length();

        if (galacticCodeFormatValidator.isValidFormat(galacticString)){
            // loop if value(n+1) > value(n) then value(n+1) - value(n)
            while(counter< galacticeValueLength){

                if(counter+1<galacticeValueLength && (convertGalacticeCodeToInt(galacticString.charAt(counter + 1)) >
                    convertGalacticeCodeToInt(galacticString.charAt(counter)))){
                    galacticSum +=  (convertGalacticeCodeToInt(galacticString.charAt(counter + 1)) -
                        convertGalacticeCodeToInt(galacticString.charAt(counter)));
                counter +=2;

            }
            else {
                galacticSum += convertGalacticeCodeToInt(galacticString.charAt(counter));
                counter += 1;
                }
              }
            }
        else{
            System.err.println("The provided galactic code is not well formed ..");
            // set the return decimal to -1 in case the format is not correct..
            galacticSum = -1;
        }
            return galacticSum;
    }

    // private method to return the int value for the curresponding galactic code
    private int convertGalacticeCodeToInt(final char galCode) {
        return romanCharToDecimalConverter.convertRomanToDecimal(galCode);
    }
}
