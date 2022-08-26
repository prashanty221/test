package com.example.rewardpointsdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardPointsByMonthRequest {

    @Schema(description = "The Name of the customer to calculate reward points by month. Can be null", example = "Customer 1")
    private String customerName;
}
