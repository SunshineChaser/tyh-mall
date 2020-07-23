package edu.learning.tyh.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Porduct implements Serializable {
    private String pid;

    private String catid;

    private String pname;

    private BigDecimal pprice;

    private BigDecimal psaleprice;

    private String pdig;

    private String pno;

    private String pkeywords;

    private Integer phot;

    private Integer pfirst;

    private BigDecimal psalenum;

    private BigDecimal pstock;

    private Integer pstate;

    private String pimg;

    private String pdes;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid == null ? null : catid.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public BigDecimal getPprice() {
        return pprice;
    }

    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    public BigDecimal getPsaleprice() {
        return psaleprice;
    }

    public void setPsaleprice(BigDecimal psaleprice) {
        this.psaleprice = psaleprice;
    }

    public String getPdig() {
        return pdig;
    }

    public void setPdig(String pdig) {
        this.pdig = pdig == null ? null : pdig.trim();
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno == null ? null : pno.trim();
    }

    public String getPkeywords() {
        return pkeywords;
    }

    public void setPkeywords(String pkeywords) {
        this.pkeywords = pkeywords == null ? null : pkeywords.trim();
    }

    public Integer getPhot() {
        return phot;
    }

    public void setPhot(Integer phot) {
        this.phot = phot;
    }

    public Integer getPfirst() {
        return pfirst;
    }

    public void setPfirst(Integer pfirst) {
        this.pfirst = pfirst;
    }

    public BigDecimal getPsalenum() {
        return psalenum;
    }

    public void setPsalenum(BigDecimal psalenum) {
        this.psalenum = psalenum;
    }

    public BigDecimal getPstock() {
        return pstock;
    }

    public void setPstock(BigDecimal pstock) {
        this.pstock = pstock;
    }

    public Integer getPstate() {
        return pstate;
    }

    public void setPstate(Integer pstate) {
        this.pstate = pstate;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg == null ? null : pimg.trim();
    }

    public String getPdes() {
        return pdes;
    }

    public void setPdes(String pdes) {
        this.pdes = pdes == null ? null : pdes.trim();
    }
}