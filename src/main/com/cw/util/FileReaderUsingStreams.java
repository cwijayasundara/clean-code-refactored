package main.com.cw.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cwijayasundara on 02/03/2017.
 */

public class FileReaderUsingStreams {

    private String filePath;

    //constructor
    public FileReaderUsingStreams(String path){
        this.filePath=path;
    }

    public List<String> getFileContent(){
        List<String> outList;
        Stream<String> fileContentStream=null;
        try {
            fileContentStream = Files.lines(Paths.get(filePath));
            outList= fileContentStream.collect(Collectors.toList());
        }
        catch(FileNotFoundException ex){
            System.out.println("The file can not be found.." + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("file not found");
        }
        catch(IOException e)
            {
                System.out.println("IOException");
                e.printStackTrace();
                throw new RuntimeException("IOException");
            }
        finally{
                if(fileContentStream != null)
                    fileContentStream.close();
        }
        return outList;
    }
}
