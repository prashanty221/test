package com.example.rewardpointsdemo.controller;

import com.example.rewardpointsdemo.exception.ApiError;
import com.example.rewardpointsdemo.model.*;
import com.example.rewardpointsdemo.service.RewardPointsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class RewardPointsController {

    @Autowired
    private RewardPointsService rewardPointsService;

    @GetMapping("/getRewardPoints")
    @Operation(
            summary = "Calculates Reward Points",
            description = "Given an amount in request this endpoint calculates the reward points",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = RewardPointsResponse.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    public ResponseEntity getRewardPoints(@Valid RewardPointsRequest request) {

        log.info("Get Reward Points Request {} - start", request);

        RewardPointsResponse response = rewardPointsService.getRewardPoints(request.getAmount());

        log.info("Get Reward Points Request {} - end", request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRewardPointsByMonth")
    @Operation(
            summary = "Get Reward Points per month for a customer for the last 3 months",
            description = "This endpoint returns reward points per month for the last 3 months of a customer. If no customer provided, this endpoint returns reward points per month for all customers",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = RewardPointsByMonthResponse.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    public ResponseEntity getRewardPointsByMonth(RewardPointsByMonthRequest request) {

        log.info("Get Reward Points By Month Request {} - start", request);

        RewardPointsByMonthResponse response = rewardPointsService.getRewardPointsByMonth(request);

        log.info("Get Reward Points By Month Request {} - end", request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRewardPointsSum")
    @Operation(
            summary = "Get Reward Points sum for a customer for the last 3 months",
            description = "This endpoint returns reward points sum for the last 3 months of a customer. If no customer provided, this endpoint returns reward points sum for all customers",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = RewardPointsSumResponse.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = ApiError.class))),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content(schema = @Schema(implementation = ApiError.class)))
            }
    )
    public ResponseEntity getRewardPointsSum(RewardPointsSumRequest request) {

        log.info("Get Reward Points Sum Request {} - start", request);

        RewardPointsSumResponse response = rewardPointsService.getRewardPointsSum(request);

        log.info("Get Reward Points Sum Request {} - end", request);

        return ResponseEntity.ok(response);
    }
}
