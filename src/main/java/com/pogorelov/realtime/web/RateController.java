package com.pogorelov.realtime.web;

import static java.time.Duration.ofSeconds;

import com.pogorelov.realtime.web.model.Rate;
import com.pogorelov.realtime.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;


    @GetMapping(value = "/rates", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ServerSentEvent<Rate>> liveStockChanges() {
        return rateService.getRate()
            .map(this::serverSentEvent)
            .delayElement(ofSeconds(1L));
    }

    private ServerSentEvent<Rate> serverSentEvent(Rate rate) {
        return ServerSentEvent.<Rate>builder()
            .event("rates-changed")
            .data(rate)
            .build();
    }

}