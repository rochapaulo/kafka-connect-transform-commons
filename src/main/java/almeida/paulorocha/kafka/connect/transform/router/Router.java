package almeida.paulorocha.kafka.connect.transform.router;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.ConnectRecord;
import org.apache.kafka.connect.transforms.Transformation;

import java.util.Map;

@Slf4j
public class Router<R extends ConnectRecord<R>> implements Transformation<R> {

    private RoutingTable routingTable;

    @Override
    public R apply(R record) {
        final String topic = routingTable.getTarget(record.topic());
        log.debug("Routing record from {} to {}", record.topic(), topic);
        return record.newRecord(
                topic,
                record.kafkaPartition(),
                record.keySchema(),
                record.key(),
                record.valueSchema(),
                record.value(),
                record.timestamp());
    }

    @Override
    public ConfigDef config() {
        return RouterConfig.config();
    }

    @Override
    public void configure(Map<String, ?> settings) {
        RouterConfig routerConfig = new RouterConfig(settings);
        routingTable = RoutingTable.parse(routerConfig.getMappingFile());
    }

    @Override
    public void close() {

    }

}
