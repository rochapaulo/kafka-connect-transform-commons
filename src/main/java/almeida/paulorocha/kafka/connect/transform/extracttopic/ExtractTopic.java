/**
 * Copyright © 2019 Paulo Almeida (almeida.paulorocha@outlook.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package almeida.paulorocha.kafka.connect.transform.extracttopic;

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
    log.info("Processing {} type - path=[{}] delimiter=[{}]", schema.type(), config.getValue(), config.getDelimiter());
    switch (schema.type()) {
      case STRUCT:
        return structValueExtractor.extract((Struct) record.value(), config.getValue());
      case MAP:
        return mapValueExtractor.extract((Map<String, Object>) record.value(), config.getValue());
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
