package com.example.formwriter.serviceIntf;

import com.example.formwriter.dto.ProfileDTO;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {

    Integer add(MultipartFile filr,String profileName) throws IOException;
    List<ProfileDTO> getList();
    void uploadFile(MultipartFile file) throws IOException;
    ProfileDTO getProfile(int profileId);
    Resource downloadFile(int profileId);

}
