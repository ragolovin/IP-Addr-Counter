package com.ecwid.counter;

import com.ecwid.IpAddressUtils;

import java.util.HashSet;

final public class IpAddrCounterNaive implements IpAddrCounter{
    private final HashSet<Integer> uniqueNumbers = new HashSet<>();
    @Override
    public void add(String addr) {
        uniqueNumbers.add(IpAddressUtils.fromIpStrToInt(addr));
    }

    @Override
    public int getUniqueAddrCount() {
        return uniqueNumbers.size();
    }
}
