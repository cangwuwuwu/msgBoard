package com.cangwu.domain;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2020/23 17:01
 */
public class AllInfo<T> {
    private String number;
    private List<T> list;

    @Override
    public String toString() {
        return "AllInfo{" +
                "number='" + number + '\'' +
                ", list=" + list +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
