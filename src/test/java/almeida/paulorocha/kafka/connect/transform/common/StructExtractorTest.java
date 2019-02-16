package almeida.paulorocha.kafka.connect.transform.common;

import org.junit.Assert;
import org.junit.Test;

public class StructExtractorTest {

  @Test
  public void extractStructInnerNode() {

    StructValueBaseExtractor structValueExtractor = new StructValueBaseExtractor(TestingData.DELIMITER);

    Assert.assertEquals(
        TestingData.HEADER_STRUCT,
        structValueExtractor.extract(TestingData.COMPLEX_STRUCT, "header")
    );

    Assert.assertEquals(
        TestingData.META_STRUCT,
        structValueExtractor.extract(TestingData.COMPLEX_STRUCT, "header.meta")
    );
  }


  @Test
  public void extractNestedValue() {

    StructValueBaseExtractor structValueExtractor = new StructValueBaseExtractor(TestingData.DELIMITER);

    Assert.assertEquals(
        "kafka-topic",
        structValueExtractor.extract(TestingData.COMPLEX_STRUCT, "header.meta.target")
    );
  }

}
