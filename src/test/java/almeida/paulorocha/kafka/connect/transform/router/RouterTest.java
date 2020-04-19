package almeida.paulorocha.kafka.connect.transform.router;


import almeida.paulorocha.kafka.connect.transform.extracttopic.TestingData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@SuppressWarnings("unchecked")
public class RouterTest {

    private static final String IN_TOPIC = "in-1";
    private static final String OUT_TOPIC = "out-1";
    private static final Map<String, Object> SETTINGS = new HashMap<>();
    static {
        SETTINGS.put(RouterConfig.MAPPING_CONFIG_FILE_CONF, "src/test/resources/router/success-mapping.json");
    }

    @Test
    @SneakyThrows
    public void shouldRouteRecord() {

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> map = objectMapper.readValue(TestingData.COMPLEX_JSON, HashMap.class);

        SourceRecord record = new SourceRecord(
                Collections.emptyMap(),
                Collections.emptyMap(),
                IN_TOPIC,
                SchemaBuilder.type(Schema.Type.MAP).build(),
                map
        );

        Router<SourceRecord> router = new Router<>();
        router.configure(SETTINGS);

        SourceRecord transformedRecord = router.apply(record);
        Assert.assertEquals(OUT_TOPIC, transformedRecord.topic());
    }

}
