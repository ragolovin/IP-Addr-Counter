package com.ecwid;

import com.ecwid.counter.UniqueIpAddrCounter;
import com.ecwid.counter.UniqueIpAddrCounterEffective;
import com.ecwid.counter.UniqueIpAddrCounterHuge;
import com.ecwid.counter.UniqueIpAddrCounterNaive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.stream.Stream;


public class AppTest
{
    @Test
    void test(){
        UniqueIpAddrCounter counterNaive = new UniqueIpAddrCounterNaive();
        UniqueIpAddrCounter counterEffective = new UniqueIpAddrCounterEffective();
        UniqueIpAddrCounter counterHuge = new UniqueIpAddrCounterHuge();
        Stream.generate(IpAddressUtils::getRandomIp)
                .limit(30000).forEach(line -> {
                    counterNaive.addAddress(line);
                    counterEffective.addAddress(line);
                    counterHuge.addAddress(line);
                });
        System.out.println("counterNaive " + counterNaive.getUniqueAddrCount());
        System.out.println("counterHuge " + counterHuge.getUniqueAddrCount());
        System.out.println("counterEffective " + counterHuge.getUniqueAddrCount());
        Assertions.assertEquals(counterNaive.getUniqueAddrCount(), counterHuge.getUniqueAddrCount());
        Assertions.assertEquals(counterEffective.getUniqueAddrCount(), counterHuge.getUniqueAddrCount());
    }

    @Disabled
    @Test
    void testBenchmark() throws RunnerException {
        Options options = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .mode(Mode.AverageTime)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(0)
                .threads(1)
                .measurementIterations(6)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();
        new Runner(options).run();
    }

    @Test
    @Benchmark
    public void hugeCounter() {
        count(new UniqueIpAddrCounterHuge());
    }

    @Test
    @Benchmark
    public void effectiveCounter() {
        count(new UniqueIpAddrCounterEffective());
    }

    @Test
    @Benchmark
    public void naiveCounter() {
        count(new UniqueIpAddrCounterNaive());
    }

    private void count(UniqueIpAddrCounter counterPerfect) {
        Stream.generate(IpAddressUtils::getNextIp)
                .limit(1000).forEach(counterPerfect::addAddress);
    }

}
