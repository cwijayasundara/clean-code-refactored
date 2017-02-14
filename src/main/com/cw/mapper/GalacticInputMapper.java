package main.com.cw.mapper;

import main.com.cw.converter.GalacticCodeToDecimalConverter;
import main.com.cw.converter.NumberConverter;
import main.com.cw.domain.GalacticCode;
import main.com.cw.domain.Metal;
import main.com.cw.domain.GalacticQuery;
import main.com.cw.util.FileReaderUtil;

import java.util.*;

/**
 * Created by cwijayasundara on 23/01/2017.
 *
 * This class uses the FileReader to read the records and expose () to put the records into
 * different buckets and perform the basic logic such as to convert the galactic codes to decimal etc.
 *
 * I tend to keep the methods short and to the single responsibility pattern and code to be self explanatory
 * hence did not add a lot of comments but put details where I felt required.
 *
 * Also I tend to keep away from class level <b></b>mutable properties </b> that would work in a single threaded env
 * but breaks with concurrency if not synced.
 *
 */

public class GalacticInputMapper {

    private final FileReaderUtil fileReaderUtil;

    // program to interfaces
    private final NumberConverter romanNumberToArabicNumberConverter;

    public GalacticInputMapper(){
        fileReaderUtil = new FileReaderUtil();
        romanNumberToArabicNumberConverter = new GalacticCodeToDecimalConverter();
    }

    /*should be injected through a .properties file + adding a getter() and a setter() to inject env specific paths
     *and test.properties in unit tests
     */
    private String filePath = "src/main/com/cw/resources/input.txt";

    private final String  codeTokenStr="is";

    private final String regExPattern = "((?<=:)|(?=:))|( )";

    private final String creditsStr = "credits";

    private final String howMuch = "how much";

    private final String questionMark = "?";

    private final String howManyCredits = "how many Credits";

    // common method to get the file content from the file reader util
    private List<String> getFileContent(){
        return fileReaderUtil.readFile(filePath);
    }

    /* This method returns a Map<String, String> containing values like {tegj=L, glob=I, prok=V, pish=X}
     * <b>Expect the records to be of <code> <is> <roman char> format eg: glob is I </b>
     */
    public List<GalacticCode> mapInputToGalacticCodes(){

        List<String> fileInput = getFileContent();
        List<GalacticCode> galacticCodes= new ArrayList<>();
        Iterator itr = fileInput.iterator();

        while(itr.hasNext()){
            String line =(String) itr.next();
            String token[]= line.split(regExPattern); // split the line to tokens
            if(token.length ==3 && (token[1].equalsIgnoreCase(codeTokenStr))){
                GalacticCode galacticCode = new GalacticCode(token[0],token[token.length -1].charAt(0));
                galacticCodes.add(galacticCode);
            }
        }
        return galacticCodes;
    }

    /* This method returns a map of <metal, price-in-decimal>
     * Expect the records to be of <b>format : <code> <code> <metal> <is> <value> <credits> format </b>
     * eg : glob glob Silver is 34 Credits
     */
    public List<Metal> getMetalPriceInDec(){

        List<GalacticCode> galacticCodes = mapInputToGalacticCodes();
        List<Metal> metalList = new ArrayList<>();
        List<String> fileInputList = getFileContent();
        Iterator itr = fileInputList.iterator();

        while(itr.hasNext()){
            String line = (String) itr.next();
            if (line.toLowerCase().endsWith(creditsStr)) {
                String token[] = line.split(regExPattern);//["glob", "glob", "Silver", "is", "34", "Credits"]
                StringBuffer valueInRomanStr = new StringBuffer();
                extractRomanNumaralOfCost(galacticCodes, token, valueInRomanStr);
                int decimalValueOfTheMetal = romanNumberToArabicNumberConverter.convert(valueInRomanStr.toString());//II = 2
                Metal metal = new Metal(token[2]);
                float metalUnitPrice = metal.calculateMetalUnitPriceInDecimal(decimalValueOfTheMetal, new Float(token[4]));
                metal.setUnitPrice(metalUnitPrice);
                metalList.add(metal);
            }
        }
        return metalList;
    }

    // helper method of getMetalPriceInDec
    private void extractRomanNumaralOfCost(List<GalacticCode> galacticCodes, String[] token, StringBuffer valueInRomanStr) {
        for (String tok: token) {
            Iterator galCodeitr = galacticCodes.iterator();
            while(galCodeitr.hasNext()){
                GalacticCode galacticCode = (GalacticCode) galCodeitr.next();
                if (tok.equalsIgnoreCase(galacticCode.getGalacticCode())){
                    valueInRomanStr.append(galacticCode.getRomanCode());
                }
            }
        }
    }

    /* This method returns a Map<String, List<String>> with valid queries and invalid queries>
     * The logic to determine a valid query vs an invalid query is as below.
     * if a line contains a valid <code> like pish or glob that maps to a Roman letter then its a valid query
     * Else if a line does not contain any valid code its an invalid query
     */
    public List<GalacticQuery> getQueryListFromInput(){

        List<GalacticQuery> queryList = new ArrayList<>();// list to send out
        List<String> fileInputList = getFileContent();
        Iterator itr = fileInputList.iterator();
        List<GalacticCode> galacticCodes = mapInputToGalacticCodes();
        List<Metal> metalList = getMetalPriceInDec();

        while(itr.hasNext()){
            String line = (String) itr.next();
            GalacticQuery query = new GalacticQuery();
            Metal metalToSetToList = null;
            if ((line.toLowerCase().contains(howMuch) || (line.contains(howManyCredits))) && line.toLowerCase().contains(questionMark)){
                String token[] = line.split(regExPattern);
                List<GalacticCode> galacticCodeList= new ArrayList<>();
                metalToSetToList = extractCodesAndMetalNames(galacticCodes, metalList,token, galacticCodeList);
                query.setGalacticCode(galacticCodeList);
                if (galacticCodeList.size() != 0) query.setValidQuery(true); else query.setValidQuery(false);
                if(metalToSetToList != null) query.setMetal(metalToSetToList); else query.setMetal(null);
                queryList.add(query);
                }
            }
        return queryList;
    }

    // helper method of getQueryListFromInput
    private Metal extractCodesAndMetalNames(List<GalacticCode> galacticCodes, List<Metal> metalList,
                                             String[] token, List<GalacticCode> galacticCodeList) {
        Metal metalToSetToList=null;
        for (String tok: token){
            for (GalacticCode galCode: galacticCodes){
                String code = galCode.getGalacticCode();
                char romanCode = galCode.getRomanCode();
                GalacticCode codeFromLine;
                if (tok.equalsIgnoreCase(code))
                {
                    codeFromLine = new GalacticCode(tok,romanCode);
                    galacticCodeList.add(codeFromLine);
                }
            }
            for (Metal metal : metalList){
                String metName = metal.getMetalName();
                if (tok.equalsIgnoreCase(metName)){
                    metalToSetToList = new Metal(tok);
                    metalToSetToList.setUnitPrice(metal.getUnitPrice());
                }
            }
        }
        return metalToSetToList;
    }
}
