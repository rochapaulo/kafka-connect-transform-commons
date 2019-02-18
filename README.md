# kafka-connect-transform-common
---
[![Build Status](https://travis-ci.org/rochapaulo/kafka-connect-transform-common.svg?branch=master)](https://travis-ci.org/rochapaulo/kafka-connect-transform-common)



### Bytes2String (encoding: UTF-8)
```yaml
  CONNECTOR_TRANSFORMS: "bytes-to-string"
  CONNECTOR_TRANSFORMS_BYTES-TO-STRING_TYPE: "almeida.paulorocha.kafka.connect.transform.common.Bytes2String"
```

------

### ExtractTopic
- Sample configuration:
```yaml
  CONNECTOR_TRANSFORMS: "extract-topic-name"
  CONNECTOR_TRANSFORMS_EXTRACT-TOPIC-NAME_TYPE: "almeida.paulorocha.kafka.connect.transform.common.ExtractTopic"
  CONNECTOR_TRANSFORMS_EXTRACT-TOPIC-NAME_VALUE: "header.topic"
  CONNECTOR_TRANSFORMS_EXTRACT-TOPIC-NAME_DELIMITER: \\.
``` 

```json
{
    "header": {
        "topic": "target-topic-name"
    },
    "body": {
        "name": "Paulo",
        "surname": "Almeida",
        "dateOfBirth": "24-10-1990"
    }
}
```