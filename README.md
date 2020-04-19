# kafka-connect-transform-common
---
[![Build Status](https://travis-ci.org/rochapaulo/kafka-connect-transform-commons.svg?branch=master)](https://travis-ci.org/rochapaulo/kafka-connect-transform-commons)

### ExtractTopic
```yaml
  CONNECTOR_TRANSFORMS: "extract-topic-name"
  CONNECTOR_TRANSFORMS_EXTRACT-TOPIC-NAME_TYPE: almeida.paulorocha.kafka.connect.transform.extracttopic.ExtractTopic
  CONNECTOR_TRANSFORMS_EXTRACT-TOPIC-NAME_VALUE: "header.topic"
  CONNECTOR_TRANSFORMS_EXTRACT-TOPIC-NAME_DELIMITER: \\.
``` 

Sample payload
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

---

### Router
```yaml
  CONNECTOR_TRANSFORMS: "router"
  CONNECTOR_TRANSFORMS_ROUTER_TYPE: almeida.paulorocha.kafka.connect.transform.router.Router
  CONNECTOR_TRANSFORMS_ROUTER_MAPPINGS_FILE_PATH: "<path-to-mappings-json>"
``` 

Mappings File:
```json
[
  {
    "from": "in-1",
    "to": "out-1"
  },
  {
    "from": "in-2",
    "to": "out-2"
  }
]
```