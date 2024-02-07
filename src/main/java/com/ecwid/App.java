package com.ecwid;

import com.ecwid.counter.IpAddrCounter;
import com.ecwid.counter.IpAddrCounterEffective;
import com.ecwid.counter.IpAddrCounterNaive;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException {
        if (args.length == 0){
            throw new IllegalArgumentException("File path is not provided");
        }
        Path path = Paths.get(args[0]);
        IpAddrCounter counterAccurate = new IpAddrCounterEffective();
        try(BufferedReader reader = Files.newBufferedReader(path);) {
            reader.lines().forEach(counterAccurate::add);
        }
        System.out.println("Unique ip addresses count is " + counterAccurate.getUniqueAddrCount());
    }
}
