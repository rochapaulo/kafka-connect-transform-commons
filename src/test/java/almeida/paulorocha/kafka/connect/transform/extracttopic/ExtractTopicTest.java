package almeida.paulorocha.kafka.connect.transform.extracttopic;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.connect.data.Schema.Type;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.source.SourceRecord;
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

    SourceRecord record = new SourceRecord(
        Collections.emptyMap(),
        Collections.emptyMap(),
        "topic",
        SchemaBuilder.type(Type.MAP).build(),
        map
    );

    ExtractTopic<SourceRecord> extractTopic = new ExtractTopic<>();
    extractTopic.configure(SETTINGS);

    SourceRecord transformedRecord = extractTopic.apply(record);
    Assert.assertEquals("kafka-topic", transformedRecord.topic());
  }

  @Test
  public void extractFromStructType() {

    SourceRecord record = new SourceRecord(
        Collections.emptyMap(),
        Collections.emptyMap(),
        "topic",
        TestingData.COMPLEX_JSON_SCHEMA,
        TestingData.COMPLEX_STRUCT
    );

    ExtractTopic<SourceRecord> extractTopic = new ExtractTopic<>();
    extractTopic.configure(SETTINGS);

    SourceRecord transformedRecord = extractTopic.apply(record);
    Assert.assertEquals("kafka-topic", transformedRecord.topic());
  }

}
