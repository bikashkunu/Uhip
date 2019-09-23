package com.nj.gov.uhip.ed.EligibilityDetailsRepository;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nj.gov.uhip.EligibilityDetailEntity.EligibilityDetailEntity;

@Repository("eligRepository")
public interface EligibilityDetailsRepository extends JpaRepository<EligibilityDetailEntity, Serializable> {

	/*
	 * @Query(value = "from EligibilityDetailsEntity where caseNum=:caseNo") public
	 * EligibilityDetailEntity findByCaseNum(@Param("caseNo") Long caseNo);
	 */
	@Query(value = "from EligibilityDetailEntity where caseNum=:caseNo")
	public EligibilityDetailEntity findByCaseNum(@Param("caseNo") Long caseNo);
}
