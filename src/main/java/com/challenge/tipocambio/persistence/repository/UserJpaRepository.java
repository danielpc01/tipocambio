package com.challenge.tipocambio.persistence.repository;

import com.challenge.tipocambio.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author dpena
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameAndKeyAndEnabled(String userName, String key, Boolean enabled);


}
