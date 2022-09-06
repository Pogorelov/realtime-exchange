package com.pogorelov.realtime.persistence.repository;

import com.pogorelov.realtime.persistence.entity.RateEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RateRepository extends ReactiveCrudRepository<RateEntity, Long> {

    @Query("select avg(r.price) from rate as r where r.create_time BETWEEN :startDate AND :endDate")
    Mono<BigDecimal> average(@Param("startDate") LocalDateTime startDate,@Param("endDate") LocalDateTime endDate);

    Mono<RateEntity> findTopByOrderByCreateTimeAsc();

}
