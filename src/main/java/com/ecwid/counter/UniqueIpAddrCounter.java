package com.ecwid.counter;

public interface UniqueIpAddrCounter {
    void addAddress(String addr);
    long getUniqueAddrCount();
}

