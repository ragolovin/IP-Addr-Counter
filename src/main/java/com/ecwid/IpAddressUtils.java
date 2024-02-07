package com.ecwid;

import java.util.Arrays;
import java.util.Random;

public class IpAddressUtils {
    private static final Random r = new Random();
    public static int fromIpStrToInt(String addrStr) {
        int[] addr = Arrays.stream(addrStr.split("\\.")).mapToInt(Integer::parseInt).toArray();
        if (addr.length !=4) throw new IllegalArgumentException("Wrong ip address format!");
        int result = 0;
        for (int part : addr) {
            result = result << 8;
            result |= part;
        }
        return result;
    }

    static String getRandomIp(){
        return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
    }
}
