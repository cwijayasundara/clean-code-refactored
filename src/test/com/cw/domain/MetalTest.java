package com.cw.domain;

import main.com.cw.domain.Metal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cwijayasundara on 14/02/2017.
 */
public class MetalTest {

    private Metal silver;
    private Metal gold;
    private Metal iron;

    @Before
    public void setUp(){
        silver = new Metal("Silver");
        gold = new Metal("Gold");
        iron = new Metal("Iron");
    }

    // to be replaced by the real values from the input
    @Test
    public void shouldTestSilverUnitPrice() throws  Exception{
        float unitPrice = silver.calculateMetalUnitPriceInDecimal(4, 100);
        Assert.assertEquals(unitPrice, 25,0);
    }

    @Test
    public void shouldTestGoldUnitPrice() throws  Exception{
        float unitPrice = silver.calculateMetalUnitPriceInDecimal(2, 1000);
        Assert.assertEquals(unitPrice, 500,0);
    }

    @Test
    public void shouldTestIronUnitPrice() throws  Exception{
        float unitPrice = silver.calculateMetalUnitPriceInDecimal(2, 50);
        Assert.assertEquals(unitPrice, 25,0);
    }

}
