package com.m99.userloginsystem.dao.smtp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.m99.userloginsystem.entity.smtp.Smtp;

public interface SmtpDao extends JpaRepository<Smtp, Long>{

	@Query("SELECT s FROM Smtp s WHERE s.isSelected= true")
	public List<Smtp> getActiveSmtp();

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("UPDATE Smtp s set s.isSelected= false where s.isSelected= true")
	public void markAllUnSelected();

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query("UPDATE Smtp s set s.isSelected= true where s.id= ?1")
	public void markSelected(long id);

}
