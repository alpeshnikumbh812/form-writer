package com.example.formwriter.controller;

import com.example.formwriter.dto.ProfileDTO;
import com.example.formwriter.serviceIntf.ProfileService;
import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/form-filling/profile")
@CrossOrigin
public class ProfileController {


    @Autowired
    private ProfileService profileService;

    Logger LOG = LoggerFactory.getLogger(ProfileController.class);

    @PostMapping(value = "/add",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Map<String,Object>> add(@RequestParam MultipartFile file,@RequestParam String profileName) throws IOException {

        LOG.info("profile-add");
        profileService.add(file,profileName);
        Map<String,Object> response = new HashMap<>();

        response.put("Success","Profile Save Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get-list")
    public ResponseEntity<List<ProfileDTO>> getList() {

        LOG.info("profile-get-list");
        return new ResponseEntity<>(profileService.getList(),HttpStatus.OK);
    }

    @GetMapping(value="/download-file/{profileId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable int profileId) throws IOException {


        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        Resource resource = profileService.downloadFile(profileId);

        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("contentType"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
