package edu.learning.tyh.pojo;

import java.io.Serializable;

public class ItemCat implements Serializable {
    private String catid;

    private String catname;

    private Integer catstate;

    private Integer catorder;

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid == null ? null : catid.trim();
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname == null ? null : catname.trim();
    }

    public Integer getCatstate() {
        return catstate;
    }

    public void setCatstate(Integer catstate) {
        this.catstate = catstate;
    }

    public Integer getCatorder() {
        return catorder;
    }

    public void setCatorder(Integer catorder) {
        this.catorder = catorder;
    }
}