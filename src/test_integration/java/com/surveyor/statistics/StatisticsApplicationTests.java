package com.surveyor.statistics;

import com.surveyor.statistics.data.dto.ResponseDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticsApplicationTests {

    @Value("${spring.data.mongodb.collection}")
    private String collection;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void getStatistics() {
        Map<String, Object> map = new HashMap<>();
        map.put("question_id", "question1");
        map.put("answer_id1", 1);
        map.put("answer_id2", 2);
        writeData(map);

        ResponseDTO responseDTO =
                this.restTemplate.getForObject(
                        "http://localhost:" + port + "/question/question1",
                        ResponseDTO.class);
        Assert.assertTrue(responseDTO.isResult());
        Assert.assertNotNull(responseDTO.getResponse());
        Map<String, Object> got = (Map<String, Object>)responseDTO.getResponse();
        Assert.assertEquals(map.get("question_id"), got.get("question_id"));
        Assert.assertEquals(map.get("answer_id1"), got.get("answer_id1"));
        Assert.assertEquals(map.get("answer_id2"), got.get("answer_id2"));
    }

    private void writeData(Map object) {
        mongoTemplate.insert(object, collection);
    }
}
