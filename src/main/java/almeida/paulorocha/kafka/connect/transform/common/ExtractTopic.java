package almeida.paulorocha.kafka.connect.transform.common;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.transforms.Transformation;

@Slf4j
public class ExtractTopic<R extends ConnectRecord<R>> implements Transformation<R> {

  private MapValueBaseExtractor mapValueExtractor;
  private StructValueBaseExtractor structValueExtractor;
  private ExtractTopicConfig config;

  public R apply(R record) {

    final String topic = extractTopic(record);

    return record.newRecord(
        topic,
        record.kafkaPartition(),
        record.keySchema(),
        record.key(),
        record.valueSchema(),
        record.value(),
        record.timestamp());
  }

  @SuppressWarnings("unchecked")
  private String extractTopic(R record) {
    final Schema schema = record.valueSchema();
    switch (schema.type()) {
      case STRUCT:
        return structValueExtractor.extract((Struct) record.value(), config.getFieldName());
      case MAP:
        return mapValueExtractor.extract((Map<String, Object>) record.value(), config.getFieldName());
      default:
        throw new UnsupportedOperationException(schema.type() + " is not supported.");
    }
  }

  public ConfigDef config() {
    return ExtractTopicConfig.config();
  }

  public void configure(Map<String, ?> settings) {
    config = new ExtractTopicConfig(settings);
    mapValueExtractor = new MapValueBaseExtractor(config.getDelimiter());
    structValueExtractor = new StructValueBaseExtractor(config.getDelimiter());
  }

  public void close() {

  }

}
