package com.example.rewardpointsdemo.listener;

import com.example.rewardpointsdemo.entity.RewardPoints;
import com.example.rewardpointsdemo.model.RewardPointsResponse;
import com.example.rewardpointsdemo.repository.RewardPointsRepository;
import com.example.rewardpointsdemo.repository.TransactionRepository;
import com.example.rewardpointsdemo.service.RewardPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RewardPointsTableUpdater {

    @Autowired
    private RewardPointsService rewardPointsService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RewardPointsRepository rewardPointsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void updateRewardPointsTable() {
        //Listener which calculates reward points based on transactions
        List<RewardPoints> rewardPointsList = transactionRepository.findAll().stream().map(transaction -> {
            RewardPointsResponse rewardPointsResponse = rewardPointsService.getRewardPoints(transaction.getAmount());
            return RewardPoints.builder().points(rewardPointsResponse.getRewardPoints()).transaction(transaction).build();
        }).collect(Collectors.toList());

        rewardPointsRepository.saveAll(rewardPointsList);
    }
}
