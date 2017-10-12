package com.shuai.model.bean.face;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangyingjun on 2017/10/12.
 */

public class FaceDetect {


    private String image_id;

    private String request_id;

    private int time_used;

    private ArrayList<Faces> faces;



    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public ArrayList<Faces> getFaces() {
        return faces;
    }

    public void setFaces(ArrayList<Faces> faces) {
        this.faces = faces;
    }
}
