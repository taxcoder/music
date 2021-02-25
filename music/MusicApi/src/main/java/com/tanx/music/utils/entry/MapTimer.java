package com.tanx.music.utils.entry;

import java.util.Date;

/**
 * @author tanxiang
 * @version 1.0
 * @date 2020/11/19 上午8:10
 */
public class MapTimer implements Cloneable{

    /** 超时时间 */
    private int timeout;
    /** 存储时间 */
    private Date store;
    /** 存储数据 */
    private Object data;

    public MapTimer(int timeout, Date store, Object data) {
        this.timeout = timeout;
        this.store = store;
        this.data = data;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Date getStore() {
        return store;
    }

    public void setStore(Date store) {
        this.store = store;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Object cloneTarget() throws CloneNotSupportedException {
        return clone();
    }
}
