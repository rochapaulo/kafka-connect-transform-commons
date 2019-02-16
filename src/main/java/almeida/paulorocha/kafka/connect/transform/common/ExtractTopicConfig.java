package almeida.paulorocha.kafka.connect.transform.common;

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
  private final String fieldName;

  @Getter
  private final String delimiter;

  ExtractTopicConfig(Map<String, ?> settings) {
    super(config(), settings);
    fieldName = getString(EXTRACT_VALUE_CONF);
    delimiter = getString(DELIMITER_CONF);
  }

  static ConfigDef config() {
    return new ConfigDef()
        .define(DELIMITER_CONF, Type.STRING, Importance.HIGH, DELIMITER_DOC)
        .define(EXTRACT_VALUE_CONF, Type.STRING, Importance.HIGH, EXTRACT_VALUE_DOC);
  }

}
