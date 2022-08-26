package com.example.rewardpointsdemo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardPointsRequest {

    @NotNull(message = "Amount cannot be Null")
    @Min(value = 1, message = "Amount should be greater than 0")
    @Schema(description = "The amount for which reward points need to be calcuated. Must not be null and > 0", example = "100", required = true)
    private Integer amount;
}
