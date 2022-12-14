package ru.otus.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.springdata.domain.Email;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {

    @Query("Select e from Email e where e.address = :address")
    Optional<Email> findByAddress(@Param("address") String address);

    @Query("update Email e set e.address = :address where e.id = :id")
    @Modifying
    @Transactional
    void updateEmailById(@Param("id")Long id, @Param("address")String address);


}
