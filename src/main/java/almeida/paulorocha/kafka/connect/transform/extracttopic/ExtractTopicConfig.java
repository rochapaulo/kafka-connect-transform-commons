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
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

@Slf4j
class ExtractTopicConfig extends AbstractConfig {

  public static final String EXTRACT_VALUE_CONF = "value";
  public static final String EXTRACT_VALUE_DOC = "";

  public static final String DELIMITER_CONF = "delimiter";
  public static final String DELIMITER_DOC = "";

  @Getter
  private final String value;

  @Getter
  private final String delimiter;

  ExtractTopicConfig(Map<String, ?> settings) {
    super(config(), settings);
    value = getString(EXTRACT_VALUE_CONF);
    delimiter = getString(DELIMITER_CONF);
  }

  static ConfigDef config() {
    return new ConfigDef()
        .define(DELIMITER_CONF, Type.STRING, Importance.HIGH, DELIMITER_DOC)
        .define(EXTRACT_VALUE_CONF, Type.STRING, Importance.HIGH, EXTRACT_VALUE_DOC);
  }

}
