package edu.learning.tyh.pojo;

import java.io.Serializable;

public class ProductImg implements Serializable{
    private String pimgid;

    private String pid;

    private String pimgaddr;

    private Integer pimgindex;

    public String getPimgid() {
        return pimgid;
    }

    public void setPimgid(String pimgid) {
        this.pimgid = pimgid == null ? null : pimgid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPimgaddr() {
        return pimgaddr;
    }

    public void setPimgaddr(String pimgaddr) {
        this.pimgaddr = pimgaddr == null ? null : pimgaddr.trim();
    }

    public Integer getPimgindex() {
        return pimgindex;
    }

    public void setPimgindex(Integer pimgindex) {
        this.pimgindex = pimgindex;
    }
}