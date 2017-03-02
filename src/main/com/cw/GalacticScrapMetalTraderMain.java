package main.com.cw;

import main.com.cw.converter.GalacticCodeToDecimalConverter;
import main.com.cw.converter.NumberConverter;
import main.com.cw.decoder.GalacticQueryDecoder;
import main.com.cw.mapper.GalacticInputMapper;

/**
 * Created by cwijayasundara on 23/01/2017.
 */

public class GalacticScrapMetalTraderMain {

    private static GalacticInputMapper galacticInputMapper = new GalacticInputMapper();
    private static NumberConverter  numberConverter= new GalacticCodeToDecimalConverter();
    private static GalacticQueryDecoder queryDecoder = new GalacticQueryDecoder(galacticInputMapper,numberConverter);

    public static void main(String args[]){
        queryDecoder.decodeGalacticQueries();

    }
}

