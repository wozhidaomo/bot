package com.zhuoyao.model;

import java.io.Serializable;

public class Yaoling implements Serializable {
    private static final long serialVersionUID = 1L;
   private String gentime;
    private String latitude;
    private String lifetime;
    private String longtitude;
    private int sprite_id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getGentime() {
        return gentime;
    }

    public void setGentime(String gentime) {
        this.gentime = gentime;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public int getSprite_id() {
        return sprite_id;
    }

    public void setSprite_id(int sprite_id) {
        this.sprite_id = sprite_id;
    }
}
