package com.ecwid.counter;

import com.ecwid.IpAddressUtils;
import org.roaringbitmap.RoaringBitmap;

final public class UniqueIpAddrCounterBest implements UniqueIpAddrCounter {

    RoaringBitmap integers = new RoaringBitmap();
    long size = 0;

    @Override
    public void addAddress(final String addr) {
        int k = IpAddressUtils.fromIpStrToInt(addr);

        if (!integers.contains(k)) {
            integers.add(k);
            size++;
        }
    }

    @Override
    public long getUniqueAddrCount() {
        return size;
    }

}
