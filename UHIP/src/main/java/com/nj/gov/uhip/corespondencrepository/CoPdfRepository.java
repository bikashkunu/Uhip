package com.nj.gov.uhip.corespondencrepository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nj.gov.uhip.corespondenceentity.CoPdfEntity;

@Repository("coPdfRepository")
public interface CoPdfRepository extends JpaRepository<CoPdfEntity, Serializable> {

}
