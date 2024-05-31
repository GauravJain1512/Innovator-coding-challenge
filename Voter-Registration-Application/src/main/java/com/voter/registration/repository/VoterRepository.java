package com.voter.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voter.registration.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long> {

}
