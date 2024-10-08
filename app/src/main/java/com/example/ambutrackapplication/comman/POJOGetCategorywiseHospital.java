package com.example.ambutrackapplication.comman;

public class POJOGetCategorywiseHospital {
    String strid,strhospitalcategoryname,strhospitalname,strhospitalimage,strhospitaladdress,strhospitalcityname
            ,strhospitalcontactno,strhospitalrating,strhospitalfeedback;

    public POJOGetCategorywiseHospital(String strid, String strhospitalimage, String strhospitalcategoryname, String strhospitalname, String strhospitaladdress,  String strhospitalcityname,String strhospitalcontactno, String strhospitalrating, String strhospitalfeedback) {
        this.strid = strid;
        this.strhospitalcategoryname = strhospitalcategoryname;
        this.strhospitalimage = strhospitalimage;
        this.strhospitalname = strhospitalname;
        this.strhospitaladdress = strhospitaladdress;
        this.strhospitalcityname=strhospitalcityname;
        this.strhospitalcontactno = strhospitalcontactno;
        this.strhospitalrating = strhospitalrating;
        this.strhospitalfeedback = strhospitalfeedback;
    }

    public String getStrid() {
        return strid;
    }

    public void setStrid(String strid) {
        this.strid = strid;
    }

    public String getStrhospitalcategoryname() {
        return strhospitalcategoryname;
    }

    public void setStrhospitalcategoryname(String strhospitalcategoryname) {
        this.strhospitalcategoryname = strhospitalcategoryname;
    }

    public String getStrhospitalname() {
        return strhospitalname;
    }

    public void setStrhospitalname(String strhospitalname) {
        this.strhospitalname = strhospitalname;
    }

    public String getStrhospitalimage() {
        return strhospitalimage;
    }

    public void setStrhospitalimage(String strhospitalimage) {
        this.strhospitalimage = strhospitalimage;
    }

    public String getStrhospitaladdress() {
        return strhospitaladdress;
    }

    public void setStrhospitaladdress(String strhospitaladdress) {
        this.strhospitaladdress = strhospitaladdress;
    }

    public String getStrhospitalcityname() {
        return strhospitalcityname;
    }

    public void setStrhospitalcityname(String strhospitalcityname) {
        this.strhospitalcityname = strhospitalcityname;
    }

    public String getStrhospitalcontactno() {
        return strhospitalcontactno;
    }

    public void setStrhospitalcontactno(String strhospitalcontactno) {
        this.strhospitalcontactno = strhospitalcontactno;
    }

    public String getStrhospitalrating() {
        return strhospitalrating;
    }

    public void setStrhospitalrating(String strhospitalrating) {
        this.strhospitalrating = strhospitalrating;
    }

    public String getStrhospitalfeedback() {
        return strhospitalfeedback;
    }

    public void setStrhospitalfeedback(String strhospitalfeedback) {
        this.strhospitalfeedback = strhospitalfeedback;
    }
}
