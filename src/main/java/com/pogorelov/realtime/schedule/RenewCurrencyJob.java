package com.pogorelov.realtime.schedule;

import com.pogorelov.realtime.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RenewCurrencyJob {

    private final CurrencyService currencyService;


    @Scheduled(cron = "*/10 * * * * *")
    public void renew() {
        currencyService.processNewCurrency();
    }

}
