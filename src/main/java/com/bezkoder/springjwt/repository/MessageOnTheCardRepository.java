package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.springjwt.models.MessageOnTheCard;

public interface MessageOnTheCardRepository extends JpaRepository<MessageOnTheCard, Long>{
	
}
