package almeida.paulorocha.kafka.connect.transform.router;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;

@Slf4j
public class RouterConfig extends AbstractConfig {

    static final  String MAPPING_CONFIG_FILE_CONF = "mappings-file-path";
    static final  String MAPPING_CONFIG_FILE_DOC= "";

    @Getter
    private final String mappingFile;

    RouterConfig(Map<String, ?> settings) {
        super(config(), settings);
        mappingFile = getString(MAPPING_CONFIG_FILE_CONF);
    }

    static ConfigDef config() {
        return new ConfigDef()
                .define(MAPPING_CONFIG_FILE_CONF, Type.STRING, Importance.HIGH, MAPPING_CONFIG_FILE_DOC);
    }

}
