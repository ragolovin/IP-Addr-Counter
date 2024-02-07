package com.ecwid.counter;

import java.util.Arrays;

public interface IpAddrCounter {
    void add(String addr);
    int getUniqueAddrCount();
}

