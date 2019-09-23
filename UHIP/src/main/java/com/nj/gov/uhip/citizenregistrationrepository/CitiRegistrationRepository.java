package com.nj.gov.uhip.citizenregistrationrepository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nj.gov.uhip.citizenregistrationentity.CitizenRegistrationEntity;

@Repository
public interface CitiRegistrationRepository extends JpaRepository<CitizenRegistrationEntity, Serializable> {

}
