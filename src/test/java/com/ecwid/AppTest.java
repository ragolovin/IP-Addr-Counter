package com.ecwid;

import com.ecwid.counter.IpAddrCounter;
import com.ecwid.counter.IpAddrCounterEffective;
import com.ecwid.counter.IpAddrCounterNaive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    void test(){
        IpAddrCounter counterNaive = new IpAddrCounterNaive();
        IpAddrCounter counterEffective = new IpAddrCounterEffective();
        Stream.generate(IpAddressUtils::getRandomIp)
                .limit(1000000).forEach(line->{
                    counterNaive.add(line);
                    counterEffective.add(line);
                });
        Assertions.assertEquals(counterNaive.getUniqueAddrCount(),counterEffective.getUniqueAddrCount());
    }
}
