package com.nj.gov.uhip.corespondencrepository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nj.gov.uhip.corespondenceentity.CorespondenceRunDetailsEntity;

@Repository("CorespondenceRunDetailsRepository ")
public interface CorespondenceRunDetailsRepository  extends JpaRepository<CorespondenceRunDetailsEntity, Serializable>{

}
