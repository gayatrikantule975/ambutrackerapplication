package com.example.ambutrackapplication.comman;

public class POJOGetAllDetails {
    //Pojo=Plain old java object
    //used to get and set multiple data
    //
    String id,cImage,cName;

    public POJOGetAllDetails(String sid, String scImage, String scName)//Recived here sid=id,scimage=image,scname=name
     {
        this.id = sid;//id is passed to current id
        this.cImage = scImage;//image is passed to scimage
        this.cName = scName;//Name is passed to scname
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcImage() {
        return cImage;
    }

    public void setcImage(String cImage) {
        this.cImage = cImage;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}
