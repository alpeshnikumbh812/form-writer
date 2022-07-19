package com.example.formwriter.serviceImpl;

import com.example.formwriter.dto.ProfileDTO;
import com.example.formwriter.dto.ProfileDetailsDTO;
import com.example.formwriter.entity.ProfileDetailsEntity;
import com.example.formwriter.repository.ProfileDetailsRepository;
import com.example.formwriter.serviceIntf.ProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileDetailsServiceImpl implements ProfileDetailsService {

    @Autowired
    private ProfileDetailsRepository pdRepository;

    @Autowired
    private ModelMapper modelMapper;

    Logger LOG = LoggerFactory.getLogger(ProfileDetailsRepository.class);

    @Override
    @Transactional
    public void add(List<ProfileDetailsDTO> profileDetailsDTOs) {


        if(!profileDetailsDTOs.isEmpty()){
            int profileId = profileDetailsDTOs.get(0).getProfileId();
            pdRepository.deleteByProfileId(profileId);
        }

        List<ProfileDetailsEntity> pdEntites = new ArrayList<>();
        System.out.println("DTOs " + profileDetailsDTOs);
        for (ProfileDetailsDTO pdDTO : profileDetailsDTOs) {

            ProfileDetailsEntity pdEntity = modelMapper.map(pdDTO, ProfileDetailsEntity.class);
            pdEntites.add(pdEntity);
//            pdRepository.save(pdEntity);
        }

        System.out.println("profile Entities " + pdEntites);
        pdRepository.saveAll(pdEntites);
    }

    @Override
    public List<ProfileDetailsDTO> getList(int profileId) {

        List<ProfileDetailsEntity> profileDetailsEntities =  pdRepository.findByProfileId(profileId);
        System.out.println("Profile Entities " + profileDetailsEntities);
        List<ProfileDetailsDTO> profileDetailsDTOS = profileDetailsEntities
                .stream()
                .map(user -> modelMapper.map(user, ProfileDetailsDTO.class))
                .collect(Collectors.toList());

        return profileDetailsDTOS;
    }
}
