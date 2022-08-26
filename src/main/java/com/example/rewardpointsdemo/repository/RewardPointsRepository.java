package com.example.rewardpointsdemo.repository;

import com.example.rewardpointsdemo.entity.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardPointsRepository extends JpaRepository<RewardPoints, Integer> {
}
