package com.pogorelov.realtime.service;

import com.pogorelov.realtime.persistence.entity.RateEntity;
import com.pogorelov.realtime.persistence.repository.RateRepository;
import com.pogorelov.realtime.web.RateApiClient;
import com.pogorelov.realtime.web.model.ExchangeCurrency;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private static final Integer ISO_CODE_USD = 840;
    private static final Integer ISO_CODE_UAH = 980;
    private static final String USD_UAH = "USD/UAH";

    private final RateApiClient rateApiClient;
    private final RateRepository rateRepository;


    public void processNewCurrency() {
        final Mono<List<ExchangeCurrency>> currencyMono = rateApiClient.getCurrency();

        currencyMono.map(list -> list.stream()
            .filter(currency -> ISO_CODE_USD.equals(currency.getCurrencyCodeA())
                && ISO_CODE_UAH.equals(currency.getCurrencyCodeB()))
            .findFirst()
            .map(this::saveNewCurrency));
    }

    public Mono<Void> saveNewCurrency(ExchangeCurrency exchangeCurrency) {
        final RateEntity rateEntity = RateEntity.builder()
            .name(USD_UAH)
            .price(BigDecimal.valueOf(exchangeCurrency.getRateSell()))
            .build();
        return rateRepository.save(rateEntity).then();
    }

}
