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

import org.apache.kafka.connect.data.Struct;

@SuppressWarnings("unchecked")
class StructValueBaseExtractor extends BaseExtractorVisitor<Struct> {

  StructValueBaseExtractor(String delimiter) {
    super(delimiter, (node, field) -> (Struct) node.get(field));
  }

  @Override
  <T> T getValue(Struct node, String field) throws ClassCastException {
    return (T) node.get(field);
  }

}
