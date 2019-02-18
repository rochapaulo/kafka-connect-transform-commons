package almeida.paulorocha.kafka.connect.transform.common;

import java.util.Collections;
import javax.xml.bind.DatatypeConverter;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.Assert;
import org.junit.Test;

public class DecodeBase64Test {

  @Test
  public void decodeSimpleContent() {

    String encodedBytes = DatatypeConverter.printBase64Binary("test".getBytes());

    SourceRecord record = new SourceRecord(
        Collections.emptyMap(),
        Collections.emptyMap(),
        "topic",
        Schema.STRING_SCHEMA,
        encodedBytes
    );

    DecodeBase64<SourceRecord> transform = new DecodeBase64<>();
    SourceRecord transformedRecord = transform.apply(record);

    Assert.assertEquals(Schema.STRING_SCHEMA, transformedRecord.valueSchema());
    Assert.assertEquals("test", transformedRecord.value());
  }


  @Test
  public void decodeComplexContent() {

    String encodedBytes = DatatypeConverter.printBase64Binary(TestingData.COMPLEX_JSON.getBytes());

    SourceRecord record = new SourceRecord(
        Collections.emptyMap(),
        Collections.emptyMap(),
        "topic",
        Schema.STRING_SCHEMA,
        encodedBytes
    );

    DecodeBase64<SourceRecord> transform = new DecodeBase64<>();
    SourceRecord transformedRecord = transform.apply(record);

    Assert.assertEquals(Schema.STRING_SCHEMA, transformedRecord.valueSchema());
    Assert.assertEquals(TestingData.COMPLEX_JSON, transformedRecord.value());
  }

}
