package almeida.paulorocha.kafka.connect.transform.common;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
abstract class BaseExtractorVisitor<T> {

  private final String delimiter;
  private final VisitorFunction<T> visitorFunction;

  final <R> R extract(T startNode, String nestedNodePath) {
    String[] arr = nestedNodePath.split(delimiter);
    log.info("Walking through: {}", Arrays.toString(arr));
    return getValue(visit(startNode, arr, 0, arr.length), arr[arr.length - 1]);
  }

  private T visit(T node, String[] fields, int iterator, int max) {
    if (max == 1) {
      return node;
    } else {
      return visit(visitorFunction.apply(node, fields[iterator]), fields, ++iterator, --max);
    }
  }

  abstract <R> R getValue(T startNode, String field);

  @FunctionalInterface
  interface VisitorFunction<T> {
    T apply(T node, String field);
  }

}
