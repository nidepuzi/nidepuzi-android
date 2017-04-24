package com.danlai.nidepuzi.entity;

import java.io.Serializable;

/**
 * Created by wisdom on 16/5/24.
 */
public class LogisticCompany implements Serializable{
    /**
     * code : POSTB
     * id : 200734
     * name : 邮政小包
     */

    private String code;
    private int id;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
