package com.b2w.challengeBackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private String name;
    private String code;
    private LocalDateTime date;
    private DimensionItem dimension;
}