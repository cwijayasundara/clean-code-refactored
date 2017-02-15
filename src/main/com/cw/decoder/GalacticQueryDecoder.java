package main.com.cw.decoder;

import main.com.cw.converter.NumberConverter;
import main.com.cw.domain.GalacticCode;
import main.com.cw.domain.GalacticQuery;
import main.com.cw.domain.Metal;
import main.com.cw.mapper.GalacticInputMapper;

import java.util.Iterator;
import java.util.List;

/**
 * Created by cwijayasundara on 23/01/2017.
 *
 * This class has the logic to decode the inter galactic codes and prices and print out the responses.
 *
 */

public class GalacticQueryDecoder {

    private GalacticInputMapper galacticInputMapper;

    private NumberConverter numberConverter;

    private final String invalidQueryString = "I have no idea what you are talking about";

    private final String credits = "Credits";

    public GalacticQueryDecoder(GalacticInputMapper galacticInputMapper, NumberConverter numberConverter){
        this.galacticInputMapper = galacticInputMapper;
        this.numberConverter = numberConverter;
    }

    /*
     * This method decode the galactic queries and produce the expected output.
     */
    public boolean decodeGalacticQueries(){

        List<GalacticQuery> galacticQueries = galacticInputMapper.getQueryListFromInput();
        Iterator<GalacticQuery> itr = galacticQueries.iterator();
        boolean isOk = false;

        while(itr.hasNext()){
            GalacticQuery query = itr.next();
            StringBuilder consolidatedRomanCodes=new StringBuilder();
            StringBuilder consolidatedGalacticCodes = new StringBuilder();
            List<GalacticCode> galacticCodes = query.getGalacticCode();
            printOutput(query, consolidatedRomanCodes, consolidatedGalacticCodes, galacticCodes);
            isOk = true;
        }
        return isOk;
    }

    /*
     * private method to print the output. Not really happy with methods that are void/unit as these have side effects
     * than just taking an input and return an output.
     */
    private void printOutput(GalacticQuery query, StringBuilder consolidatedRomanCodes,
                             StringBuilder consolidatedGalacticCodes,
                             List<GalacticCode> galacticCodes) {

        if (galacticCodes != null && galacticCodes.size() != 0){
            for (GalacticCode galCode:galacticCodes){
                consolidatedRomanCodes.append(galCode.getRomanCode());
                consolidatedGalacticCodes.append(galCode.getGalacticCode() + " ");
            }
            int intValueForGalcticCodes = numberConverter.convert(consolidatedRomanCodes.toString());
            if (query.getMetal() == null){
                System.out.println(consolidatedGalacticCodes + "is " + intValueForGalcticCodes);
            }else if (query.getMetal() != null){
                decodeQueriesWithMetals(query, consolidatedRomanCodes, consolidatedGalacticCodes);
            }
        } else{
            System.out.println(invalidQueryString);
        }
    }

    /*
     * Private method of printOutput to handle the Galactic queries with Metals inside it.
     */
    private void decodeQueriesWithMetals(GalacticQuery query, StringBuilder consolidatedRomanCodes,
                                         StringBuilder consolidatedGalacticCodes) {
        int intValue = numberConverter.convert(consolidatedRomanCodes.toString());
        Metal metal = query.getMetal();
        float unitPrice = metal.getUnitPrice();
        float totalPrice = unitPrice * intValue;
        System.out.println(consolidatedGalacticCodes + metal.getMetalName() + " is " + totalPrice + " " + credits);
    }
}
