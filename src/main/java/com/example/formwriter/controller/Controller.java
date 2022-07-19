package com.example.formwriter.controller;

import com.example.formwriter.dto.ProfileDetailsDTO;
import com.example.formwriter.entity.Controls;
import com.example.formwriter.serviceIntf.Service;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/form-writer")
public class Controller {

    @Autowired
    Service service;

    @PostMapping("/get-controls")
    public void getControls(@RequestBody ArrayList<ProfileDetailsDTO> controls) {
        service.getControls(controls);
    }

    @GetMapping( value = "/get-image-url/{profileId}/{pageNo}/{extra}" , produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImageUrl(@PathVariable int profileId,@PathVariable int pageNo) throws IOException {
        System.out.println(profileId + " " + pageNo);
//        service.getImageUrl(profileId,pageNo);
        return new ResponseEntity<>(service.getImageUrl(profileId,pageNo) , HttpStatus.OK);
    }
}
