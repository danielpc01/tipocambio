package com.challenge.tipocambio.persistence.repository;

import java.util.Optional;

import com.challenge.tipocambio.persistence.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dpena
 */
@Repository
public interface ExchangeRateJpaRepository extends JpaRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findByCurrencyTypeIdAndCurrencyTypeTargetIdAndActiveAndEnabled(
            Long currencyTypeId,
            Long currencyTypeTargetId,
            Boolean activate,
            Boolean enabled);
}
