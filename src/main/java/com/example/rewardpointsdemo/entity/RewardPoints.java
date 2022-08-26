package com.example.rewardpointsdemo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "REWARD_POINTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardPoints {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int points;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
