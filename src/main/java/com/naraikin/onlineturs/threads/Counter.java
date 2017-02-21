package com.naraikin.onlineturs.threads;

import java.util.HashSet;

/**
 * Created by dmitrii on 21.02.17.
 */
public class Counter {
    static HashSet<Object> hashSet = new HashSet<>();

    public void append(Object o) {
        hashSet.add(o);
        System.out.println("----------->");
    }

    public boolean isExist(Object o) {
        System.out.println("===========>");
        return hashSet.contains(o);
    }
    public void print() {
        System.out.println(hashSet);
    }
}
