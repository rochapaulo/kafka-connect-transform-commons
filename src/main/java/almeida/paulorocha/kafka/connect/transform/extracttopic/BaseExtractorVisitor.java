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

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
abstract class BaseExtractorVisitor<T> {

  private final String delimiter;
  private final VisitorFunction<T> visitorFunction;

  final <R> R extract(T startNode, String nestedNodePath) throws ClassCastException {
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

  abstract <R> R getValue(T startNode, String field) throws ClassCastException;

  @FunctionalInterface
  interface VisitorFunction<T> {
    T apply(T node, String field);
  }

}
