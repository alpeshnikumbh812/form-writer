package com.example.formwriter.entity;

import javax.swing.*;


public class Controls {

    private Integer index;
    private String type;
    private Integer x;
    private Integer y;
    private String lable;
    private String value;
    private Integer gap;
    private Integer fontSize;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getGap() {
        return gap;
    }

    public void setGap(Integer gap) {
        this.gap = gap;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public String toString() {
        return "Controls{" +
                "index=" + index +
                ", type='" + type + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", lable='" + lable + '\'' +
                ", value='" + value + '\'' +
                ", gap=" + gap +
                ", fontSize=" + fontSize +
                '}';
    }
}
