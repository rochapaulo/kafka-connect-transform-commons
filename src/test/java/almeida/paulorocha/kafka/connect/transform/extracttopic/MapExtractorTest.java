package almeida.paulorocha.kafka.connect.transform.extracttopic;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("unchecked")
public class MapExtractorTest {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private static final Map<String, Object> EXPECTED_NESTED_MAP;

  static {
    EXPECTED_NESTED_MAP = new HashMap<>();
    EXPECTED_NESTED_MAP.put("target", "kafka-topic");
    EXPECTED_NESTED_MAP.put("foo", "bar");
  }

  @Test
  public void extractMapInnerNode() throws Exception {

    Map<String, Object> jsonMap = OBJECT_MAPPER.readValue(TestingData.COMPLEX_JSON, HashMap.class);

    MapValueBaseExtractor mapExtractorVisitor = new MapValueBaseExtractor(TestingData.DELIMITER);

    Map<String, Object> innerNode = mapExtractorVisitor.extract(jsonMap, "header.meta");
    Assert.assertEquals(EXPECTED_NESTED_MAP, innerNode);
  }

  @Test
  public void extractNestedValue() throws Exception {

    Map<String, Object> jsonMap = OBJECT_MAPPER.readValue(TestingData.COMPLEX_JSON, HashMap.class);

    MapValueBaseExtractor mapExtractorVisitor = new MapValueBaseExtractor(TestingData.DELIMITER);

    String value = mapExtractorVisitor.extract(jsonMap, "header.meta.target");
    Assert.assertEquals("kafka-topic", value);
  }

}
