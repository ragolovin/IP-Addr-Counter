package com.ecwid.counter;

import static com.ecwid.IpAddressUtils.getAddrInt;

/*
    Effective for addresses from same subnets and huge amounts
 */
final public class UniqueIpAddrCounterHuge implements UniqueIpAddrCounter {

    private final boolean[][][][] addresses = new boolean[256][][][];
    int size = 0;

    @Override
    public long getUniqueAddrCount() {
        return size;
    }

    @Override
    public void addAddress(final String addr) {
        final int[] addrInt = getAddrInt(addr);
        final int a = addrInt[0];
        final int b = addrInt[1];
        final int c = addrInt[2];
        final int d = addrInt[3];
        if (addresses[a] == null) {
            addresses[a] = new boolean[256][][];
        } else {
            if (addresses[a].length == 0) {
                return;
            }
            if (isAllFilled(addresses[a])) {
                addresses[a] = new boolean[0][][];
            }
        }
        if (addresses[a][b] == null) {
            addresses[a][b] = new boolean[256][];
        } else {
            if (addresses[a][b].length == 0) {
                return;
            }
            if (isAllFilled(addresses[a][b])) {
                addresses[a][b] = new boolean[0][];
                return;
            }
        }
        if (addresses[a][b][c] == null) {
            addresses[a][b][c] = new boolean[256];
            addresses[a][b][c][d] = true;
            size++;
        } else {
            if (addresses[a][b][c].length != 0 && !addresses[a][b][c][d]) {
                addresses[a][b][c][d] = true;
                size++;
            } else {
                return;
            }
        }
        if (isAllFilled(addresses[a][b][c])) {
            addresses[a][b][c] = new boolean[0];
        }
    }

    private boolean isAllFilled(final boolean[][][] arr) {
        for (boolean[][] b : arr) {
            if (b == null || b.length == 256) return false;
        }
        return true;
    }

    private boolean isAllFilled(final boolean[][] arr) {
        for (boolean[] b : arr) {
            if (b == null || b.length == 256) return false;
        }
        return true;
    }

    private boolean isAllFilled(final boolean[] arr) {
        for (boolean b : arr) {
            if (!b) return false;
        }
        return true;
    }

}
