package com.pogorelov.realtime.web.model;

import static lombok.AccessLevel.PRIVATE;

import com.pogorelov.realtime.persistence.entity.RateEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(access = PRIVATE)
public class Rate {

    String name;
    BigDecimal currentPrice;
    BigDecimal averagePrice;
    LocalDateTime lastUpdated;


    public static Rate of(RateEntity rateEntity, BigDecimal averagePrice) {
        return Rate.builder()
            .name(rateEntity.getName())
            .currentPrice(rateEntity.getPrice())
            .averagePrice(averagePrice)
            .lastUpdated(rateEntity.getCreateTime())
            .build();
    }
}
