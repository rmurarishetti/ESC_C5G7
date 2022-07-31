package com.example.ecs_c5g7.model;

import com.example.ecs_c5g7.R;

import java.io.Serializable;

public class PostModel implements Serializable {
    String docId;
    String postImage, pushKey;
    String postTime, catagory,country,fileName;

    Integer status,price;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String offerStatusString() {
        String value;
         switch (getStatus()) {
             case 0: {
                 value = "Pending";
                 break;
             }
             case 1: {
                 value = "Approved";
                 break;
             }
             default: {
                 value = "Declined";
             }

        }
        return value;
    }

    public Integer offerStatusColor() {
        int value;
         switch (getStatus()) {
            case 0: {
                value = R.color.yellow;
                break;
            }
            case 1: {
                value = R.color.green;
                break;
            }
            default: {
                value =R.color.red;
                break;
            }

        }
        return value;
    }


}
