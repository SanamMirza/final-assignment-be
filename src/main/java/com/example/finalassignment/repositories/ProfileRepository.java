package com.example.finalassignment.repository;

import com.example.finalassignment.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
