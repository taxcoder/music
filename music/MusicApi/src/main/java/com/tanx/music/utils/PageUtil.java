package com.tanx.music.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/24 下午1:22
 */
public class PageUtil<T> {
    /** 当前页 */
    private int current = 0;
    /** 总数 */
    private int total = 0;
    /** 获取的数据 */
    private List<T> list = new ArrayList<>();
    /** 每页显示的数据 */
    private int length;
    /** 页码数 */
    private int pagination;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current <= 0){
            this.current = 1;
            return;
        }
        if(total != 0){
            this.current = Math.min(current,total);
            return;
        }

        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPagination() {
        if(current == 0 || total == 0){
            return 1;
        }

        return (int) Math.ceil((double) total / length);
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "current=" + current +
                ", total=" + total +
                ", list=" + list +
                ", length=" + length +
                ", pagination=" + pagination +
                '}';
    }
}
