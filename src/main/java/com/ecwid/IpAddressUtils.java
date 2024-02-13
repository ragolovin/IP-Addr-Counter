package com.ecwid;

import java.util.Arrays;
import java.util.Random;

public class IpAddressUtils {
    private static final Random r = new Random();
    static int a = 0, b = 0, c = 0, d = 0;
    public static int fromIpStrToInt(String addrStr) {
        int[] addr = getAddrInt(addrStr);
        if (addr.length !=4) throw new IllegalArgumentException("Wrong ip address format!");
        int result = 0;
        for (int part : addr) {
            result = result << 8;
            result |= part;
        }
        return result;
    }

    public static int[] getAddrInt(final String addrStr) {
        return Arrays.stream(addrStr.split("\\.")).mapToInt(Integer::parseInt).toArray();
    }

    public static String getRandomIp(){
        return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
    }

    public static String getNextIp() {
        String ip = a + "." + b + "." + c + "." + d;
        if (d == 255) {
            d = 0;
            if (c == 255) {
                c = 0;
                if (b == 255) {
                    b = 0;
                    if (a == 255) {
                        a = 0;
                    } else {
                        a++;
                    }
                } else {
                    b++;
                }
            } else {
                c++;
            }
        } else {
            d++;
        }
        return ip;
    }
}
