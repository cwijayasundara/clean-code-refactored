package com.cw.decoder;

import main.com.cw.converter.GalacticCodeToDecimalConverter;
import main.com.cw.converter.NumberConverter;
import main.com.cw.decoder.GalacticQueryDecoder;
import main.com.cw.mapper.GalacticInputMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by cwijayasundara on 23/01/2017.
 */
public class GalacticQueryDecoderTest {

    private GalacticInputMapper galacticInputMapper = new GalacticInputMapper();
    private NumberConverter numberConverter = new GalacticCodeToDecimalConverter();
    private final GalacticQueryDecoder galacticQueryDecoder = new GalacticQueryDecoder(galacticInputMapper,numberConverter);

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tierDown() throws Exception{

    }

    @Test
    public void shouldTestGalacticQueryDecoder() throws  Exception{
        Assert.assertEquals(galacticQueryDecoder.decodeGalacticQueries(), true);
    }

}