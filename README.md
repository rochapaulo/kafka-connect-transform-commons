# kafka-connect-transform-common
---

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