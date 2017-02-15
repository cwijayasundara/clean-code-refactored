package com.cw.util;

import main.com.cw.util.RomanCharToDecimalConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cwijayasundara on 15/02/2017.
 */
public class RomanCharToDecimalConverterTest {

    private RomanCharToDecimalConverter romanCharToDecimalConverter;

    @Before
    public void setUp(){
        romanCharToDecimalConverter = new RomanCharToDecimalConverter();
    }

    @Test
    public void shouldTestRomanCharI(){
        int i = romanCharToDecimalConverter.convertRomanToDecimal('I');
        Assert.assertEquals(i, 1);
    }

    @Test
    public void shouldTestRomanCharV(){
        int i = romanCharToDecimalConverter.convertRomanToDecimal('V');
        Assert.assertEquals(i, 5);
    }

    @Test
    public void shouldTestRomanCharX(){
        int i = romanCharToDecimalConverter.convertRomanToDecimal('X');
        Assert.assertEquals(i, 10);
    }

    @Test
    public void shouldTestRomanCharL(){
        int i = romanCharToDecimalConverter.convertRomanToDecimal('L');
        Assert.assertEquals(i, 50);
    }

    @Test
    public void shouldTestRomanCharC(){
        int i = romanCharToDecimalConverter.convertRomanToDecimal('C');
        Assert.assertEquals(i, 100);
    }

    @Test
    public void shouldTestRomanCharD(){
        int i = romanCharToDecimalConverter.convertRomanToDecimal('D');
        Assert.assertEquals(i, 500);
    }

    @Test
    public void shouldTestRomanCharM(){
        int i = romanCharToDecimalConverter.convertRomanToDecimal('M');
        Assert.assertEquals(i, 1000);
    }

    @Test
    public void shouldTestRomanCharInvalid(){
        int i = romanCharToDecimalConverter.convertRomanToDecimal('P');
        Assert.assertEquals(i, 0);
    }


}