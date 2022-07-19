package com.example.formwriter.serviceIntf;

import com.example.formwriter.dto.ProfileDTO;
import com.example.formwriter.dto.ProfileDetailsDTO;

import java.util.List;

public interface ProfileDetailsService {

   void add(List<ProfileDetailsDTO> profileDetailsDTO);

    List<ProfileDetailsDTO> getList(int profileId);
}
