package com.cw.mapper;

import main.com.cw.domain.GalacticCode;
import main.com.cw.domain.GalacticQuery;
import main.com.cw.domain.Metal;
import main.com.cw.mapper.GalacticInputMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by cwijayasundara on 23/01/2017.
 */

public class GalacticInputMapperTest {

    private GalacticInputMapper galacticInputMapper;

    @Before
    public void setUp() throws Exception {
        galacticInputMapper = new GalacticInputMapper();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shoudTestInputtoGalacticCodeMapperWithSampleValues(){
        List<GalacticCode> tokenToRomanMap = galacticInputMapper.mapInputToGalacticCodes();
        Assert.assertNotNull(tokenToRomanMap);
        Assert.assertEquals(tokenToRomanMap.size(),4);
        Assert.assertEquals(tokenToRomanMap.get(0).getGalacticCode(),"glob");
        Assert.assertEquals(tokenToRomanMap.get(0).getRomanCode(),'I');
        Assert.assertEquals(tokenToRomanMap.get(0).getDecimal(),1);
        Assert.assertEquals(tokenToRomanMap.get(1).getGalacticCode(),"prok");
        Assert.assertEquals(tokenToRomanMap.get(1).getRomanCode(),'V');
        Assert.assertEquals(tokenToRomanMap.get(1).getDecimal(),5);
        Assert.assertEquals(tokenToRomanMap.get(2).getGalacticCode(),"pish");
        Assert.assertEquals(tokenToRomanMap.get(2).getRomanCode(),'X');
        Assert.assertEquals(tokenToRomanMap.get(2).getDecimal(),10);
        Assert.assertEquals(tokenToRomanMap.get(3).getGalacticCode(),"tegj");
        Assert.assertEquals(tokenToRomanMap.get(3).getRomanCode(),'L');
        Assert.assertEquals(tokenToRomanMap.get(3).getDecimal(),50);

    }

    @Test
    public void shouldTestMetalToDecimalPriceMapper() {
        List<Metal> metalList = galacticInputMapper.getMetalPriceInDec();
        Assert.assertNotNull(metalList);
        Assert.assertEquals(metalList.get(0).getMetalName(),"Silver");
        Assert.assertEquals(metalList.get(0).getUnitPrice(), new Float("17.0").floatValue(),0);
        Assert.assertEquals(metalList.get(1).getMetalName(),"Gold");
        Assert.assertEquals(metalList.get(1).getUnitPrice(), new Float("14450.0").floatValue(), 0);
        Assert.assertEquals(metalList.get(2).getMetalName(),"Iron");
        Assert.assertEquals(metalList.get(2).getUnitPrice(), new Float("195.5").floatValue(), 0);
    }

    @Test
    public void shouldTestQueryList() throws Exception{
        List<GalacticQuery> queryList = galacticInputMapper.getQueryListFromInput();
        Assert.assertNotNull(queryList);
        Assert.assertEquals(queryList.size(),5);
        Assert.assertEquals(queryList.get(0).getMetal(), null);
        Assert.assertEquals(queryList.get(0).isValidQuery(), true);
        Assert.assertEquals(queryList.get(0).getGalacticCode().get(0).getGalacticCode(), "pish");
        Assert.assertEquals(queryList.get(0).getGalacticCode().get(1).getGalacticCode(), "tegj");
        Assert.assertEquals(queryList.get(0).getGalacticCode().get(2).getGalacticCode(), "glob");
        Assert.assertEquals(queryList.get(0).getGalacticCode().get(3).getGalacticCode(), "glob");
        Assert.assertEquals(queryList.get(1).getMetal().getMetalName(), "Silver");
        Assert.assertEquals(queryList.get(1).isValidQuery(), true);
        Assert.assertEquals(queryList.get(1).getGalacticCode().get(0).getGalacticCode(), "glob");
        Assert.assertEquals(queryList.get(1).getGalacticCode().get(1).getGalacticCode(), "prok");
        Assert.assertEquals(queryList.get(2).getMetal().getMetalName(), "Gold");
        Assert.assertEquals(queryList.get(2).isValidQuery(), true);
        Assert.assertEquals(queryList.get(2).getGalacticCode().get(0).getGalacticCode(), "glob");
        Assert.assertEquals(queryList.get(2).getGalacticCode().get(1).getGalacticCode(), "prok");
        Assert.assertEquals(queryList.get(3).getMetal().getMetalName(), "Iron");
        Assert.assertEquals(queryList.get(3).isValidQuery(), true);
        Assert.assertEquals(queryList.get(3).getGalacticCode().get(0).getGalacticCode(), "glob");
        Assert.assertEquals(queryList.get(3).getGalacticCode().get(1).getGalacticCode(), "prok");
        Assert.assertEquals(queryList.get(4).getMetal(), null);
        Assert.assertEquals(queryList.get(4).isValidQuery(), false);
        Assert.assertEquals(queryList.get(4).getGalacticCode(), Collections.emptyList());
    }
}