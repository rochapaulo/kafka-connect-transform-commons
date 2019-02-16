package almeida.paulorocha.kafka.connect.transform.common;

import java.util.Map;

@SuppressWarnings("unchecked")
class MapValueBaseExtractor extends BaseExtractorVisitor<Map<String, Object>> {

  MapValueBaseExtractor(String delimiter) {
    super(delimiter, (node, field) -> (Map<String, Object>) node.get(field));
  }

  @Override
  <T> T getValue(Map<String, Object> node, String field) {
    return (T) node.get(field);
  }

}
