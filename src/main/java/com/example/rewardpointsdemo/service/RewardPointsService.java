package com.example.rewardpointsdemo.service;

import com.example.rewardpointsdemo.configuration.ApplicationProperties;
import com.example.rewardpointsdemo.entity.Customer;
import com.example.rewardpointsdemo.entity.Transaction;
import com.example.rewardpointsdemo.model.*;
import com.example.rewardpointsdemo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RewardPointsService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private CustomerRepository customerRepository;

    public RewardPointsResponse getRewardPoints(int amount) {
        log.info("Service Processing Get Reward Points with amount {} - start",  amount);

        int rewardPoints = 0;
        int amountOne = applicationProperties.getAmounts().getAmountOne();
        int amountTwo = applicationProperties.getAmounts().getAmountTwo();

        if(amount > amountOne) {
            rewardPoints += (amount - amountOne);
        }

        if(amount > amountTwo) {
            rewardPoints += (amount - amountTwo);
        }

        log.info("Service Processing Get Reward Points with amount {} - end",  amount);

        return RewardPointsResponse.builder().rewardPoints(rewardPoints).build();
    }

    public RewardPointsByMonthResponse getRewardPointsByMonth(RewardPointsByMonthRequest request) {
        log.info("Service Processing Get Reward Points By Month with request {} - start",  request);

        RewardPointsByMonthResponse rewardPointsByMonthResponse = new RewardPointsByMonthResponse();
        List<Customer> customers = new ArrayList();

        if(StringUtils.isNotBlank(request.getCustomerName())) {
            customers.add(customerRepository.findByName(request.getCustomerName()));
        }else {
            customers.addAll(customerRepository.findAll());
        }

        //Retains only 3 months records
        removeTransactions(customers);

        //Creating a map grouping by customer name and internally another map of reward points grouped by month
        Map<String, Map<Month, List<Integer>>> customerByMonthMap = new HashMap<>();
        for(Customer customer : customers) {

            Map<Month, List<Integer>> pointsByMonthMap = new HashMap<>();
            for(Transaction transaction : customer.getTransactions()) {
                Month month = transaction.getCreatedDate().getMonth();
                int points = transaction.getRewardPoints().getPoints();
                if(pointsByMonthMap.containsKey(month)) {
                    pointsByMonthMap.get(month).add(points);
                }else {
                    List<Integer> pointsList = new ArrayList();
                    pointsList.add(points);
                    pointsByMonthMap.put(month, pointsList);
                }
            }
            customerByMonthMap.put(customer.getName(), pointsByMonthMap);
        }

        //Calculating per month reward points per customer
        List<CustomersByMonth> customersList = new ArrayList();
        for(String name: customerByMonthMap.keySet()) {
            CustomersByMonth customersObj = new CustomersByMonth();
            customersObj.setCustomerName(name);
            List<RewardPointsByMonth> rewardPointsByMonthList = new ArrayList();
            Map<Month, List<Integer>> pointsByMonthMap = customerByMonthMap.get(name);
            for(Month month: pointsByMonthMap.keySet()) {
                RewardPointsByMonth rewardPointsByMonth = new RewardPointsByMonth();
                Integer sum = pointsByMonthMap.get(month).stream()
                        .collect(Collectors.summingInt(Integer::intValue));

                rewardPointsByMonth.setMonth(month);
                rewardPointsByMonth.setAmount(sum);
                rewardPointsByMonthList.add(rewardPointsByMonth);
            }
            customersObj.setRewardPointsByMonth(rewardPointsByMonthList);
            customersList.add(customersObj);
        }

        rewardPointsByMonthResponse.setCustomers(customersList);

        log.info("Service Processing Get Reward Points By Month with request {} - end",  request);

        return rewardPointsByMonthResponse;
    }

    public RewardPointsSumResponse getRewardPointsSum(RewardPointsSumRequest request) {
        log.info("Service Processing Get Reward Points Sum with request {} - start",  request);

        RewardPointsSumResponse rewardPointsByMonthResponse = new RewardPointsSumResponse();
        List<CustomersBySum> customersBySumList = new ArrayList();
        List<Customer> customers = new ArrayList();

        if(StringUtils.isNotBlank(request.getCustomerName())) {
            customers.add(customerRepository.findByName(request.getCustomerName()));
        }else {
            customers.addAll(customerRepository.findAll());
        }

        //Retains only 3 months records
        removeTransactions(customers);

        //calculating sum of all reward points per customer
        for(Customer customer : customers) {
            CustomersBySum customersBySum = new CustomersBySum();
            customersBySum.setCustomerName(customer.getName());

            Integer sum = customer.getTransactions().stream().map(transaction -> transaction.getRewardPoints().getPoints())
                    .collect(Collectors.summingInt(Integer::intValue));
            customersBySum.setRewardPoints(sum);
            customersBySumList.add(customersBySum);
        }

        rewardPointsByMonthResponse.setCustomers(customersBySumList);

        log.info("Service Processing Get Reward Points Sum with request {} - end",  request);

        return rewardPointsByMonthResponse;
    }

    private void removeTransactions(List<Customer> customers) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime previous = now.minusMonths(3);

        customers.forEach(customer -> {
            customer.getTransactions()
                    .removeIf(transaction -> transaction.getCreatedDate().isBefore(previous) || transaction.getCreatedDate().isAfter(now));
        });
    }

}
