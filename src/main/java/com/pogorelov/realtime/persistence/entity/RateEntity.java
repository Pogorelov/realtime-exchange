package com.pogorelov.realtime.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "rate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateEntity {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("price")
    private BigDecimal price;

    @Column("create_time")
    private LocalDateTime createTime;

}
