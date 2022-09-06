package com.pogorelov.realtime.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.pogorelov.realtime.web.model.ExchangeCurrency;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "RateApiClient", url = "${bank.api.url}" )
public interface RateApiClient {

    @RequestMapping(method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<List<ExchangeCurrency>> getCurrency();

}
