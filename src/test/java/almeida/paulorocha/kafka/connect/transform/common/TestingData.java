package almeida.paulorocha.kafka.connect.transform.common;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

public interface TestingData {

  String DELIMITER = "\\.";
  String COMPLEX_JSON =
      "{\n"
          + "  \"header\": {\n"
          + "     \"eventType\": \"WalkerEvent\",\n"
          + "     \"timestamp\": 10000000000000,\n"
          + "     \"meta\": {"
          + "         \"target\": \"kafka-topic\",\n"
          + "         \"foo\": \"bar\""
          + "     }\n"
          + "   },\n"
          + "  \"body\": {\n"
          + "     \"firstName\": \"Paulo\",\n"
          + "     \"surname\": \"Almeida\",\n"
          + "     \"dateOfBirth\": \"24-10-1990\"\n"
          + "  }\n"
          + "}";


  Schema META_SCHEMA =
      SchemaBuilder.struct()
          .field("target", Schema.STRING_SCHEMA)
          .field("foo", Schema.STRING_SCHEMA);

  Schema HEADER_SCHEMA =
      SchemaBuilder.struct()
          .field("eventType", Schema.STRING_SCHEMA)
          .field("timestamp", Schema.INT32_SCHEMA)
          .field("meta", META_SCHEMA);

  Schema BODY_SCHEMA =
      SchemaBuilder.struct()
          .field("firstName", Schema.STRING_SCHEMA)
          .field("surname", Schema.STRING_SCHEMA)
          .field("dateOfBirth", Schema.STRING_SCHEMA);

  Schema COMPLEX_JSON_SCHEMA =
      SchemaBuilder.struct()
          .field("header", HEADER_SCHEMA)
          .field("body", BODY_SCHEMA);


  Struct META_STRUCT = new Struct(META_SCHEMA)
      .put("target", "kafka-topic")
      .put("foo", "bar");

  Struct HEADER_STRUCT = new Struct(HEADER_SCHEMA)
      .put("eventType", "WalkerEvent")
      .put("timestamp", 1000000000)
      .put("meta", META_STRUCT);

  Struct BODY_STRUCT = new Struct(BODY_SCHEMA)
      .put("firstName", "Paulo")
      .put("surname", "Almeida")
      .put("dateOfBirth", "24-10-1990");


  Struct COMPLEX_STRUCT = new Struct(COMPLEX_JSON_SCHEMA)
      .put("header", HEADER_STRUCT)
      .put("body", BODY_STRUCT);

}
