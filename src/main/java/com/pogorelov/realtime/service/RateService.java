package com.pogorelov.realtime.service;

import static java.math.RoundingMode.HALF_UP;

import com.pogorelov.realtime.persistence.entity.RateEntity;
import com.pogorelov.realtime.persistence.repository.RateRepository;
import com.pogorelov.realtime.web.RateApiClient;
import com.pogorelov.realtime.web.model.ExchangeCurrency;
import com.pogorelov.realtime.web.model.Rate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;


    public Mono<Rate> getRate() {
        final LocalDateTime start = LocalDate.now().atStartOfDay();
        final LocalDateTime end = start.plusDays(1);
        final Mono<BigDecimal> average = rateRepository.average(start, end).map(avg -> avg.setScale(2, HALF_UP));
        final Mono<RateEntity> rateMono = rateRepository.findTopByOrderByCreateTimeAsc();
        return Mono.zip(rateMono, average, Rate::of);
    }

}
