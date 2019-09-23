package com.nj.gov.uhip.uhiprepository;

import java.io.Serializable;
import java.lang.annotation.Native;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nj.gov.uhip.useraccountentity.UserAccountEntity;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Serializable> {
	@Query(value = "select count(email) from UserAccountEntity where email=:email")
	public Integer findByEmail(@Param(value = "email") String email);

	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "update UserAccountEntity ae set ae.active= :N where ae.userAccId=:id")
	 * public Integer udateStatus(@Param(value = "id") int id, @Param(value = "N")
	 * String N);
	 */
	/*
	 * @Query(
	 * value="update UserAccountEntity set active=:active where userAccId=:id ")
	 * public int statusupdate(@Param(value = "active") String active, @Param(value
	 * = "id") int id);
	 */
	@Transactional
	@Modifying
	@Query(value = "update UserAccountEntity ua set ua.active=:active where ua.userAccId=:id")
	public int statusupdate(@Param("active") String active, @Param("id") int id);

	@Query(value = "SELECT * FROM Account_Entity WHERE email=?1", nativeQuery = true)
	public UserAccountEntity findByEmailforVerification(@Param(value = "email") String email);

	@Transactional
	@Modifying
	@Query(value = "update UserAccountEntity ua set ua.active=:active where ua.userAccId=:id")
	public int statusactivate(@Param("active") String active, @Param("id") int id);

}
