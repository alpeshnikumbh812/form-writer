package com.example.formwriter.repository;

import com.example.formwriter.dto.ProfileDTO;
import com.example.formwriter.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {

    ProfileEntity findByProfileId(int profileId);
}
