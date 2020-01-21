package com.challenge.tipocambio.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.challenge.tipocambio.persistence.model.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @author dpena
 */
@Repository
public interface CurrencyTypeJpaRepository extends JpaRepository<CurrencyType, Long> {

    Optional<CurrencyType> findByIdAndEnabled(Long id,Boolean enabled);
    Optional<List<CurrencyType>> findByEnabled(Boolean enabled);


}
