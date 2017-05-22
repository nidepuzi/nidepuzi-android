package com.danlai.nidepuzi.entity;

import java.util.List;

/**
 * @author wisdom
 * @date 2017年05月22日 上午10:54
 */

public class DrawCashListBean {


    /**
     * count : 0
     * next : null
     * previous : null
     * results : []
     */

    private int count;
    private String next;
    private String previous;
    private List<DrawCashBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<DrawCashBean> getResults() {
        return results;
    }

    public void setResults(List<DrawCashBean> results) {
        this.results = results;
    }
}
