package com.naraikin.onlineturs.wraps;

import java.util.List;

/**
 * Created by dmitrii on 20.02.17.
 */
public interface WrapI <T> {

    List<T> getList();
    void setList(List<T> c);
    void append(T c);
}
