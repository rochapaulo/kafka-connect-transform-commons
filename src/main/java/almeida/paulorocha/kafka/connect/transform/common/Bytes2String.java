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
package almeida.paulorocha.kafka.connect.transform.common;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.transforms.Transformation;

public class Bytes2String<R extends ConnectRecord<R>> implements Transformation<R> {

  @Override
  public R apply(R record) {

    if (!Schema.BYTES_SCHEMA.equals(record.valueSchema())) {
      throw new UnsupportedOperationException();
    }

    return record.newRecord(
        record.topic(),
        record.kafkaPartition(),
        record.keySchema(),
        record.key(),
        Schema.STRING_SCHEMA,
        new String((byte[]) record.value(), StandardCharsets.UTF_8),
        record.timestamp());
  }

  @Override
  public ConfigDef config() {
    return new ConfigDef();
  }

  @Override
  public void close() {
    // NOOP
  }

  @Override
  public void configure(Map<String, ?> settings) {
    // NOOP
  }

}
