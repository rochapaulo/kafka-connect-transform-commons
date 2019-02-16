package almeida.paulorocha.kafka.connect.transform.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
abstract class BaseExtractorVisitor<T> {

  private final String delimiter;
  private final VisitorFunction<T> visitorFunction;

  final <R> R extract(T startNode, String nestedNodePath) {
    String[] arr = nestedNodePath.split(delimiter);
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
