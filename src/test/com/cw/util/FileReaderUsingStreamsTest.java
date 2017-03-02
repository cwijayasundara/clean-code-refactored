package com.cw.util;

import main.com.cw.util.FileReaderUsingStreams;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by cwijayasundara on 02/03/2017.
 */
public class FileReaderUsingStreamsTest {

    private FileReaderUsingStreams inputFileReader;

    private FileReaderUsingStreams outputFileReader;

    @Before
    public void setUp() throws Exception {
        inputFileReader = new FileReaderUsingStreams("src/test/com/cw/resources/input.txt");
        outputFileReader = new FileReaderUsingStreams("src/test/com/cw/resources/output.txt");
    }

    @Test
    public void shouldTestInputFileContent() throws Exception {
        List<String> fileOutput = inputFileReader.getFileContent();
        Assert.assertNotNull(fileOutput);
        Assert.assertEquals(fileOutput.size(),12);
        Assert.assertEquals(fileOutput.get(0), "glob is I");
        Assert.assertEquals(fileOutput.get(4), "glob glob Silver is 34 Credits");
        Assert.assertEquals(fileOutput.get(11), "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    }

    @Test
    public void shouldTestOutPutFileContent() throws Exception {
        List<String> fileOutput = outputFileReader.getFileContent();
        Assert.assertNotNull(fileOutput);
        Assert.assertEquals(fileOutput.size(),5);
        Assert.assertEquals(fileOutput.get(0), "pish tegj glob glob is 42");
        Assert.assertEquals(fileOutput.get(2), "glob prok Gold is 57800 Credits");
        Assert.assertEquals(fileOutput.get(4), "I have no idea what you are talking about");
    }

}