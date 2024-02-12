package com.ecwid;

import com.ecwid.counter.UniqueIpAddrCounter;
import com.ecwid.counter.UniqueIpAddrCounterBest;
import com.ecwid.counter.UniqueIpAddrCounterEffective;
import com.ecwid.counter.UniqueIpAddrCounterHuge;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class App 
{
    public static void main(String[] args) throws IOException {
        if (args.length == 0){
            throw new IllegalArgumentException("Parameters are not provided!");
        }
        Path path = Paths.get(args[0]);
        UniqueIpAddrCounter counter;
        switch (args[1]){
            case "h"-> counter = new UniqueIpAddrCounterHuge();
            case "e"-> counter = new UniqueIpAddrCounterEffective();
            case "b" -> counter = new UniqueIpAddrCounterBest();
            default -> counter = new UniqueIpAddrCounterBest();
        }
        try(BufferedReader reader = Files.newBufferedReader(path);) {
            reader.lines().forEach(counter::addAddress);
        }
        System.out.println("Unique ip addresses count is " + counter.getUniqueAddrCount());
    }

}
