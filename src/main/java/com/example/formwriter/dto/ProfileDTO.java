package com.example.formwriter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfileDTO {

    private int profileId;
    private String profileName;
    private String profilePdfPath;
    private int pdfPages;
}
