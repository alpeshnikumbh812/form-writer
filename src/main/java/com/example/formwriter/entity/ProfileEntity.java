package com.example.formwriter.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="profile")
@Getter
@Setter
@ToString
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int profileId;
    private String profileName;
    private String profilePdfPath;
    private int pdfPages;
}
