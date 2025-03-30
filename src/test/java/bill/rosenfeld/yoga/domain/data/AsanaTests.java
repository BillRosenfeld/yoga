package bill.rosenfeld.yoga.domain.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class AsanaTests {

    private static final Logger LOG = LoggerFactory.getLogger(AsanaTests.class);
    private static final String CLASS_NAME = "AsanaTests";
    private List<Asana> asanas;
    private String jsonString;

    @BeforeAll
    static void beforeAll() {
        LOG.info("Starting " + CLASS_NAME);
    }

    @BeforeEach
    void setUp() {
        LOG.info("Set up before each test");
        this.asanas = new ArrayList<>();
        this.asanas.add(Asana.of("Sukhasana", "Easy Pose"));
        this.asanas.add(Asana.of("Savasana", "Corpse Pose"));
        this.jsonString = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";
    }

    @AfterEach
    void tearDown() {
        LOG.info("Tear down after each test");
        this.asanas = null;
        this.jsonString = null;
    }

    @AfterAll
    static void afterAll() {
        LOG.info("Ending " + CLASS_NAME);
    }

    @Test
    public void test1() throws JsonProcessingException {
        String s = new ObjectMapper().writeValueAsString(asanas);
        LOG.info(s);
        assertNotNull(1, "cannot be null");
    }

    @Test
    public void test2() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(jsonString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> map = new HashMap<>();
        addKeys(jsonNode, "", map);
        map.forEach((key, value) -> {
            LOG.info("Key: " + key);
            LOG.info("Value: " + value);
        });
        assertNotNull(1, "cannot be null");
    }

    private void addKeys(JsonNode jsonNode, String currentPath, Map<String, String> jsonMap) {
        if (jsonNode.isObject()) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Iterator<Map.Entry<String, JsonNode>> iter = objectNode.fields();
            String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";
            while (iter.hasNext()) {
                Map.Entry<String, JsonNode> entry = iter.next();
                addKeys(entry.getValue(), pathPrefix + entry.getKey(), jsonMap);
            }
        } else if (jsonNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) jsonNode;
            for (int i = 0; i < arrayNode.size(); i++) {
                addKeys(arrayNode.get(i), currentPath + "[" + i + "]", jsonMap);
            }
        } else if (jsonNode.isValueNode()) {
            ValueNode valueNode = (ValueNode) jsonNode;
            jsonMap.put(currentPath, valueNode.asText());
        }
    }

    @Test
    public void test3() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        LOG.info(set.toString());
        Collection<CompletableFuture<Void>> futures = new ArrayList<>(set.size());
        ExecutorService executorService = Executors.newFixedThreadPool(set.size());
        set.forEach((item) -> {
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(
                    () -> {
                        LOG.info(item);
                        LOG.info(item + " skip");
                        return null;
                    }, executorService);
            futures.add(future);
        });
        CompletableFuture<Void> completedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        try {
            completedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        assertEquals(3, set.size(), "Should be 3");
    }

    @Test
    public void test4() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        LOG.info(set.toString());
        Collection<CompletableFuture<Void>> futures = new ArrayList<>(set.size());
        ExecutorService executorService = Executors.newFixedThreadPool(set.size());
        set.forEach((item) -> {
            futures.add(CompletableFuture.supplyAsync(
                    () -> {
                        LOG.info(item);
                        LOG.info(item + " skip");
                        return null;
                    }, executorService));
        });
        CompletableFuture<Void> completedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        try {
            completedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        assertEquals(3, set.size(), "Should be 3");
    }

    @Test
    public void testConcurrencyWithParallelStream() {
        Set<Timestamp> set = new HashSet<>();
        set.add(Timestamp.valueOf("2023-01-01 12:00:00"));
        set.add(Timestamp.valueOf("2023-01-02 12:00:00"));
        set.add(Timestamp.valueOf("2023-01-03 12:00:00"));
        LOG.info(set.toString());
        set.parallelStream().forEach((item) -> {
            LOG.info(String.valueOf(item));
        });
        assertEquals(3, set.size(), "Should be 3");
    }

    @Test
    public void testEmptySet() {
        Set<String> set = new HashSet<>();
        assertNotNull(set, "Should not be null");
        assertEquals(0, set.size(), "Should be 0");
    }

}
