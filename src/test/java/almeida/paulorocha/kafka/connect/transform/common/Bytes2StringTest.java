package almeida.paulorocha.kafka.connect.transform.common;

import java.util.Collections;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.Assert;
import org.junit.Test;

public class Bytes2StringTest {

  @Test
  public void decodeSimpleContent() {

    byte[] eventBytes = "test".getBytes();

    SourceRecord record = new SourceRecord(
        Collections.emptyMap(),
        Collections.emptyMap(),
        "topic",
        Schema.BYTES_SCHEMA,
        eventBytes
    );

    Bytes2String<SourceRecord> transform = new Bytes2String<>();
    SourceRecord transformedRecord = transform.apply(record);

    Assert.assertEquals(Schema.STRING_SCHEMA, transformedRecord.valueSchema());
    Assert.assertEquals("test", transformedRecord.value());
  }


  @Test
  public void decodeComplexContent() {

    byte[] eventBytes = TestingData.COMPLEX_JSON.getBytes();

    SourceRecord record = new SourceRecord(
        Collections.emptyMap(),
        Collections.emptyMap(),
        "topic",
        Schema.BYTES_SCHEMA,
        eventBytes
    );

    Bytes2String<SourceRecord> transform = new Bytes2String<>();
    SourceRecord transformedRecord = transform.apply(record);

    Assert.assertEquals(Schema.STRING_SCHEMA, transformedRecord.valueSchema());
    Assert.assertEquals(TestingData.COMPLEX_JSON, transformedRecord.value());
  }

}
