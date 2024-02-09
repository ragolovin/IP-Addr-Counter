package com.ecwid.counter;

import com.ecwid.IpAddressUtils;

import java.util.HashSet;

final public class UniqueIpAddrCounterNaive implements UniqueIpAddrCounter {
    private final HashSet<Integer> uniqueNumbers = new HashSet<>();
    @Override
    public void addAddress(String addr) {
        uniqueNumbers.add(IpAddressUtils.fromIpStrToInt(addr));
    }

    @Override
    public long getUniqueAddrCount() {
        return uniqueNumbers.size();
    }
}
