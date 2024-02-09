package com.ecwid.counter;

import com.ecwid.IpAddressUtils;

/*
    Effective for random addresses
 */
final public class UniqueIpAddrCounterEffective implements UniqueIpAddrCounter {
    private int[] key;
    private boolean[] used;
    private static final float f = 0.75f;
    private static final int INITIAL_CAPACITY = 16;
    private int hashTableSize;
    private int maxFill;
    private int mask;
    private int size;

    public UniqueIpAddrCounterEffective() {
        hashTableSize = arraySize(INITIAL_CAPACITY);
        mask = hashTableSize - 1;
        key = new int[hashTableSize];
        used = new boolean[hashTableSize];
    }

    @Override
    public void addAddress(final String addr) {
        int k = IpAddressUtils.fromIpStrToInt(addr);
        int pos = (murmurHash3((k))) & mask;
        while (used[pos]) {
            if (key[pos] == k) return;
            pos = (pos + 1) & mask;
        }
        used[pos] = true;
        key[pos] = k;
        if (++size >= maxFill) rehash(arraySize(size + 1));
    }

    @Override
    public long getUniqueAddrCount() {
        return size;
    }

    private void rehash(final int newN) {
        int i = 0, pos;
        final boolean[] used = this.used;
        final int[] key = this.key;
        final int newMask = newN - 1;
        final int[] newKey = new int[newN];
        final boolean[] newUsed = new boolean[newN];
        for (int j = size; j-- != 0; ) {
            while (!used[i]) i++;
            int k = key[i];
            pos = (murmurHash3((k))) & newMask;
            while (newUsed[pos]) pos = (pos + 1) & newMask;
            newUsed[pos] = true;
            newKey[pos] = k;
            i++;
        }
        hashTableSize = newN;
        mask = newMask;
        maxFill = maxFill(hashTableSize);
        this.key = newKey;
        this.used = newUsed;
    }

    private static int arraySize(final int expected) {
        final long s = Math.max(2, nextPowerOfTwo((long) Math.ceil(expected / f)));
        return (int) s;
    }

    private static long nextPowerOfTwo(long x) {
        return 1L << (64 - Long.numberOfLeadingZeros(x - 1));
    }

    private static int maxFill(final int n) {
        return Math.min((int) Math.ceil(n * f), n - 1);
    }

    private static int murmurHash3(int x) {
        x ^= x >>> 16;
        x *= 0x85ebca6b;
        x ^= x >>> 13;
        x *= 0xc2b2ae35;
        x ^= x >>> 16;
        return x;
    }
}
