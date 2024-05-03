package com.m99.userloginsystem.dao.profilepic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.m99.userloginsystem.entity.profilepic.ProfilePic;

@Repository
public interface ProfilePicDao extends JpaRepository<ProfilePic, Long>{
	public Optional<ProfilePic> findByImageName(String imageName);
	public Optional<ProfilePic> findByUserId(long userId);
}
