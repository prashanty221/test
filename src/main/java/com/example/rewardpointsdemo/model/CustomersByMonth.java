package com.example.rewardpointsdemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomersByMonth {

    private String customerName;
    private List<RewardPointsByMonth> rewardPointsByMonth;
}