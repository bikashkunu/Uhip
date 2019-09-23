package com.nj.gov.uhip.corespondencrepository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nj.gov.uhip.corespondenceentity.CoTriggersEntity;

@Repository
public interface CoTriggerRepository extends JpaRepository<CoTriggersEntity, Serializable> {
	/*
	 * @Query(name = "from CoTriggersEntity where triggerStatus=:trgSw") public
	 * List<CoTriggersEntity> findAllPendingTriggers(String trgSw);
	 */
	@Query(value = "from CoTriggersEntity where triggerStatus=:trg")
	public List<CoTriggersEntity> findAllPendingTrigger(@Param("trg") String trg);

}

