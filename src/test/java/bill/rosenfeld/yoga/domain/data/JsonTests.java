package bill.rosenfeld.yoga.domain.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class JsonTests {

    private static final Logger LOG = LoggerFactory.getLogger(JsonTests.class);
    private static final String CLASS_NAME = "JsonTests";
    private String json;

    @BeforeAll
    static void beforeAll() {
        LOG.info("Starting " + CLASS_NAME);
    }

    @BeforeEach
    void setUp() {
        LOG.info("Set up before each test");
        json = "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}";
    }

    @AfterEach
    void tearDown() {
        LOG.info("Tear down after each test");
        json = null;
    }

    @AfterAll
    static void afterAll() {
        LOG.info("Ending " + CLASS_NAME);
    }

    @Test
    public void jsonTest() {

        assertNotNull("", "cannot be null");
    }

}
