package bill.rosenfeld.yoga.domain.data;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class GeneralTests {

    private static final Logger LOG = LoggerFactory.getLogger(GeneralTests.class);
    private static final String CLASS_NAME = "GeneralTests";

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

    @Test
    public void eclipseCollectionsTest() {
        MutableList<String> strings = FastList.newList();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        strings.add("four");
        strings.add("five");
        RichIterable<RichIterable<String>> chunks = strings.chunk(3);
        for (RichIterable<String> chunk : chunks) {
            String result = String.join(",", chunk);
            LOG.info(result);
        }
        String result = String.join(",", strings);
        LOG.info(result);
        assertNotNull(result, "cannot be null");
    }

    @Test
    public void guidTest() {
        MutableList<String> strings = FastList.newList();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        strings.add("four");
        strings.add("five");
        strings.parallelStream().forEach(e -> LOG.info(e + " " + UUID.randomUUID()));
        assertNotNull(1, "cannot be null");
    }

}
