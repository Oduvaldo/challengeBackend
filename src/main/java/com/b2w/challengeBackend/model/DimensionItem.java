package com.b2w.challengeBackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DimensionItem {
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
}
