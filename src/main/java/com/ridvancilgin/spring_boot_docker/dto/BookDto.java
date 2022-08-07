package com.ridvancilgin.spring_boot_docker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String name;

    private String author;

    private BigDecimal price;
}
