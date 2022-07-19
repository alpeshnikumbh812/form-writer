package com.example.formwriter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
public class ProfileDetailsDTO {

    private int detailId;
    private int id;
    private int indexNum;
    private String controlType;
    private int gap;
    private int x;
    private int y;
    private int fontSize;
    private String lable;
    private int pageNum;
    private String value;
    private int profileId;
}
