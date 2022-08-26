package com.example.rewardpointsdemo;

import com.example.rewardpointsdemo.model.CustomersByMonth;
import com.example.rewardpointsdemo.model.CustomersBySum;
import com.example.rewardpointsdemo.model.RewardPointsByMonthResponse;
import com.example.rewardpointsdemo.model.RewardPointsSumResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardPointsDemoControllerTest {

    private static final String BASE_PATH = "/api/v1";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRewardPoints() throws Exception {
        String path = BASE_PATH + "/getRewardPoints?amount=100";

        MvcResult content = mockMvc.perform(get(path)).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = content.getResponse().getContentAsString();
        assertTrue(response.contains("50"));
    }

    @Test
    public void testGetRewardPointsInvalidAmount() throws Exception {
        String path = BASE_PATH + "/getRewardPoints";

        MvcResult content = mockMvc.perform(get(path)).andDo(print()).andExpect(status().isBadRequest()).andReturn();
        String response = content.getResponse().getContentAsString();
        assertTrue(response.contains("BAD_REQUEST"));
    }

    @Test
    public void testGetRewardPointsByMonth() throws Exception {
        String path = BASE_PATH + "/getRewardPointsByMonth?customerName=Customer 1";

        MvcResult content = mockMvc.perform(get(path)).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = content.getResponse().getContentAsString();
        RewardPointsByMonthResponse rewardPointsByMonthResponse = new ObjectMapper().readValue(response, RewardPointsByMonthResponse.class);
        assertEquals(rewardPointsByMonthResponse.getCustomers().size(), 1);

        CustomersByMonth customers = rewardPointsByMonthResponse.getCustomers().get(0);
        assertEquals(customers.getCustomerName(), "Customer 1");
        assertEquals(customers.getRewardPointsByMonth().size(), 3);
    }

    @Test
    public void testGetRewardPointsByMonthNoCustomer() throws Exception {
        String path = BASE_PATH + "/getRewardPointsByMonth";

        MvcResult content = mockMvc.perform(get(path)).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = content.getResponse().getContentAsString();
        RewardPointsByMonthResponse rewardPointsByMonthResponse = new ObjectMapper().readValue(response, RewardPointsByMonthResponse.class);
        assertEquals(rewardPointsByMonthResponse.getCustomers().size(), 3);

        CustomersByMonth customers = rewardPointsByMonthResponse.getCustomers().get(0);
        assertEquals(customers.getRewardPointsByMonth().size(), 3);
    }

    @Test
    public void testGetRewardPointsSum() throws Exception {
        String path = BASE_PATH + "/getRewardPointsSum?customerName=Customer 1";

        MvcResult content = mockMvc.perform(get(path)).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = content.getResponse().getContentAsString();
        RewardPointsSumResponse rewardPointsSumResponse = new ObjectMapper().readValue(response, RewardPointsSumResponse.class);
        assertEquals(rewardPointsSumResponse.getCustomers().size(), 1);

        CustomersBySum customers = rewardPointsSumResponse.getCustomers().get(0);
        assertEquals(customers.getCustomerName(), "Customer 1");
        assertEquals(customers.getRewardPoints(), 156);
    }

    @Test
    public void testGetRewardPointsSumNoCustomer() throws Exception {
        String path = BASE_PATH + "/getRewardPointsSum";

        MvcResult content = mockMvc.perform(get(path)).andDo(print()).andExpect(status().isOk()).andReturn();
        String response = content.getResponse().getContentAsString();
        RewardPointsSumResponse rewardPointsSumResponse = new ObjectMapper().readValue(response, RewardPointsSumResponse.class);
        assertEquals(rewardPointsSumResponse.getCustomers().size(), 3);
    }

}
