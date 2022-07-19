package com.example.formwriter.controller;

import com.example.formwriter.dto.ProfileDetailsDTO;
import com.example.formwriter.serviceIntf.ProfileDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/profile-details")
public class ProfileDetailsController {

    @Autowired
    private ProfileDetailsService pdService;

    Logger LOG = LoggerFactory.getLogger(ProfileDetailsController.class);

    @PostMapping(value = "/add", produces = "Application/Json", consumes = "Application/Json")
    public ResponseEntity<Map<String, Object>> add(@RequestBody List<ProfileDetailsDTO> pdDTO) {
        LOG.info("profile-details-add");
        pdService.add(pdDTO);
        Map<String, Object> response = new HashMap<>();
        response.put("Success", "Profile Save Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/get-list/{profileId}", produces = "Application/Json")
    public ResponseEntity<List<ProfileDetailsDTO>> get(@PathVariable int profileId) {
        LOG.info("profile-details-add");
        return new ResponseEntity<>(pdService.getList(profileId), HttpStatus.OK);
    }

}
