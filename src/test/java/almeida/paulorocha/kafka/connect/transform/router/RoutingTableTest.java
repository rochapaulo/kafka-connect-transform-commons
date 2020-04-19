package almeida.paulorocha.kafka.connect.transform.router;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

public class RoutingTableTest {

    private static final RoutingTable EXPECTED_TABLE;

    static {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("in-1", "out-1");
        mapping.put("in-2", "out-2");
        mapping.put("in-3", "out-3");
        EXPECTED_TABLE = new RoutingTable(mapping);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldLoadRoutingTable() {
        RoutingTable table = RoutingTable.parse("src/test/resources/router/success-mapping.json");
        Assert.assertEquals(EXPECTED_TABLE, table);
    }

    @Test
    public void shouldThrowExceptionIfDuplicatedKeyIsFound() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Duplicate key in-1 (attempted merging values out-1 and out-2)");
        RoutingTable.parse("src/test/resources/router/duplicate-key-mapping.json");
    }

    @Test
    public void shouldThrowExceptionIfCantDeserialize() {
        thrown.expect(RuntimeException.class);
        thrown.expectMessage("Cannot deserialize instance of `java.util.ArrayList` out of START_OBJECT token");
        RoutingTable.parse("src/test/resources/router/bad-mapping.json");
    }

}
