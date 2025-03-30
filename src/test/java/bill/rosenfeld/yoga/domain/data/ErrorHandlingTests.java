package bill.rosenfeld.yoga.domain.data;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ErrorHandlingTests {

    private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlingTests.class);
    private static final String CLASS_NAME = "ErrorHandlingTests";

    @BeforeAll
    static void beforeAll() {
        LOG.info("Starting " + CLASS_NAME);
    }

    @BeforeEach
    void setUp() {
        LOG.info("Set up before each test");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Tear down after each test");
    }

    @AfterAll
    static void afterAll() {
        LOG.info("Ending " + CLASS_NAME);
    }

    private String testMethod(boolean flag) {
        if (flag) {
            LOG.info("I am dumping");
            throw new RuntimeException("Just wanted to");
        } else {
            LOG.info("I am not dumping");
        }
        return "I shall return!";
    }

    @Test
    public void errorTest() {
        LOG.info("Value: " + testMethod(true));
        assertNotNull(testMethod(false), "cannot be null");
    }

    private Optional<String> optionalMethod(boolean flag) {
        if (flag) {
            return Optional.ofNullable("value");
        } else {
            return Optional.empty();
        }
    }

    @Test
    public void optionalTest() {
        optionalMethod(true).ifPresent(s -> {
            LOG.info(s);
            LOG.info(s.toUpperCase());
        });
        assertTrue(optionalMethod(true).isPresent(), "must be true");
        assertTrue(optionalMethod(false).isEmpty(), "must be true");
        assertEquals("value", optionalMethod(true).get(), "must be equal");
        assertNull(optionalMethod(false).orElse(null), "must be equal");
    }

}
