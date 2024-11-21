package com.yetanalytics;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yetanalytics.xapi.model.Statement;

public class App 
{
    public static void main( String[] args )
    {
        String filepath = args[0];
        if (!filepath.isEmpty()){
            Path p = Paths.get(filepath);
            if (Files.isReadable(p)) {
                //ObjectMapper mapper = Mapper.getMapper();
                ObjectMapper mapper = new ObjectMapper();
                mapper.findAndRegisterModules();
                try {
                    Statement stmt = mapper.readValue(p.toFile(), Statement.class);
                    System.out.println(stmt.toString());
                } catch (IOException e) {
                    System.out.println("Caught IOException");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Filepath Unreadable");  
            }
        } else {
            System.out.println("Bad Arg");
        }
    }
}
