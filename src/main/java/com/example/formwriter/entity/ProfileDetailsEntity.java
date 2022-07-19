package com.example.formwriter.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="profile_details")
@Getter
@Setter
@ToString
public class ProfileDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
