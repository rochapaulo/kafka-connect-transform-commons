#!/usr/bin/env bash

/usr/local/bin/wait-for-it.sh "${KAFKA_BROKER}"
/usr/local/bin/wait-for-it.sh "${SCHEMA_REGISTRY_URL}"

for data_folder in /tmp/data/*; do
  kafka-avro-console-producer \
    --topic "$(basename "$data_folder")" \
    --broker-list "${KAFKA_BROKER}" \
    --property value.schema="$(cat "$data_folder"/schema.json)" \
    --property schema.registry.url="${SCHEMA_REGISTRY_URL}" \
    < "$data_folder/data.json"
done
