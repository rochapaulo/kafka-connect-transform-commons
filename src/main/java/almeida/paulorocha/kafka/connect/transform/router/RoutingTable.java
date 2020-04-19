package almeida.paulorocha.kafka.connect.transform.router;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class RoutingTable {

    private Map<String, String> mapping;

    String getTarget(String source) {
        return mapping.get(source);
    }

    static RoutingTable parse(String file) {
        final ObjectMapper mapper = new ObjectMapper();
        final RoutingTable routingTable;
        try {
            List<Mapping> mappings = mapper.readValue(Paths.get(file).toFile(), new TypeReference<List<Mapping>>() {});
            routingTable = new RoutingTable(mappings.stream().collect(toMap(Mapping::getFrom, Mapping::getTo)));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return routingTable;
    }

}
