package com.danlai.nidepuzi.entity;

/**
 * Created by wisdom on 16/10/11.
 */

public class SaveTimeBean {

    /**
     * id : 123
     * save_times : 222
     * share_times : 0
     */

    private int id;
    private int save_times;
    private int share_times;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSave_times() {
        return save_times;
    }

    public void setSave_times(int save_times) {
        this.save_times = save_times;
    }

    public int getShare_times() {
        return share_times;
    }

    public void setShare_times(int share_times) {
        this.share_times = share_times;
    }
}
