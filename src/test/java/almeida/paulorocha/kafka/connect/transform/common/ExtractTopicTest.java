package almeida.paulorocha.kafka.connect.transform.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.connect.data.Schema.Type;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class ExtractTopicTest {

  private static final Map<String, Object> SETTINGS = new HashMap<>();
  static {
    SETTINGS.put(ExtractTopicConfig.DELIMITER_CONF, "\\.");
    SETTINGS.put(ExtractTopicConfig.EXTRACT_VALUE_CONF, "header.meta.target");
  }

  @Test
  public void extractFromMapType() throws Exception {

    ObjectMapper objectMapper = new ObjectMapper();
    HashMap<String, Object> map = objectMapper.readValue(TestingData.COMPLEX_JSON, HashMap.class);

    SinkRecord record = new SinkRecord(
        "topic",
        1,
        null,
        null,
        SchemaBuilder.type(Type.MAP).build(),
        map,
        1L
    );

    ExtractTopic<SinkRecord> extractTopic = new ExtractTopic<>();
    extractTopic.configure(SETTINGS);

    SinkRecord transformedRecord = extractTopic.apply(record);
    Assert.assertEquals("kafka-topic", transformedRecord.topic());
  }

  @Test
  public void extractFromStructType() {

    SinkRecord record = new SinkRecord(
        "topic",
        1,
        null,
        null,
        TestingData.COMPLEX_JSON_SCHEMA,
        TestingData.COMPLEX_STRUCT,
        1L
    );

    ExtractTopic<SinkRecord> extractTopic = new ExtractTopic<>();
    extractTopic.configure(SETTINGS);

    SinkRecord transformedRecord = extractTopic.apply(record);
    Assert.assertEquals("kafka-topic", transformedRecord.topic());
  }

}
