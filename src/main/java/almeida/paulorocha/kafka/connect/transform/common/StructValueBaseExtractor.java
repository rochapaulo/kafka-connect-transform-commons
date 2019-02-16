package almeida.paulorocha.kafka.connect.transform.common;

import org.apache.kafka.connect.data.Struct;

@SuppressWarnings("unchecked")
class StructValueBaseExtractor extends BaseExtractorVisitor<Struct> {

  StructValueBaseExtractor(String delimiter) {
    super(delimiter, (node, field) -> (Struct) node.get(field));
  }

  @Override
  <T> T getValue(Struct node, String field) {
    return (T) node.get(field);
  }

}
