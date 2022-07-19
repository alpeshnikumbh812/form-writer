package com.example.formwriter.repository;

import com.example.formwriter.dto.ProfileDetailsDTO;
import com.example.formwriter.entity.ProfileDetailsEntity;
import com.example.formwriter.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileDetailsRepository extends JpaRepository<ProfileDetailsEntity,Integer> {

    List<ProfileDetailsEntity> findByProfileId(int profileId);
    void deleteByProfileId(int profileId);
}
