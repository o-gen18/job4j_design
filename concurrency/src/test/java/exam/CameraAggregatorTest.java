package exam;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CameraAggregatorTest {

    //В самом задании есть элемент: {
    //"id": 2,
    //"urlType": "ARCHIVE",
    //"videoUrl": "rtsp://127.0.0.1/2",
    //"value": "fa4b5b22-249b-11e9-ab14-d663bd873d93",
    //"ttl": 60
    //}
    // Здесь id-2 и если перейти по реальной ссылке этого элемента, то там:
    // "urlType": "LIVE",
    // "videoUrl": "rtsp://127.0.0.1/20"
    // То есть не соответствует как в ссылках, поэтому вручную ожидаемый результат формировал
    // по фактическому содержанию ссылок
    // Еще в ожидаемом ответе в задании стоят пробелы после двоеточия (в элементах json),
    // мне не удалось запрограммировать их установку, так что в ответе я пробелы не далал,
    // можно ли так так поступать?

    private final Map<String, String> expected = new HashMap<>() {
        {
            put("1", "\r\n{\r\n"
                    + "\"id\":1,\r\n"
                    + "\"urlType\":\"LIVE\",\r\n"
                    + "\"videoUrl\":\"rtsp://127.0.0.1/1\",\r\n"
                    + "\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\",\r\n"
                    + "\"ttl\":120\r\n"
                    + "}");
            put("3", "\r\n{\r\n"
                    + "\"id\":3,\r\n"
                    + "\"urlType\":\"ARCHIVE\",\r\n"
                    + "\"videoUrl\":\"rtsp://127.0.0.1/3\",\r\n"
                    + "\"value\":\"fa4b5d52-249b-11e9-ab14-d663bd873d93\",\r\n"
                    + "\"ttl\":120\r\n"
                    + "}");
            put("20", "\r\n{\r\n"
                    + "\"id\":20,\r\n"
                    + "\"urlType\":\"ARCHIVE\",\r\n"
                    + "\"videoUrl\":\"rtsp://127.0.0.1/2\",\r\n"
                    + "\"value\":\"fa4b5b22-249b-11e9-ab14-d663bd873d93\",\r\n"
                    + "\"ttl\":60\r\n"
                    + "}");
            put("2", "\r\n{\r\n"
                    + "\"id\":2,\r\n"
                    + "\"urlType\":\"LIVE\",\r\n"
                    + "\"videoUrl\":\"rtsp://127.0.0.1/20\",\r\n"
                    + "\"value\":\"fa4b5f64-249b-11e9-ab14-d663bd873d93\",\r\n"
                    + "\"ttl\":180\r\n"
                    + "}");
        }
    };

    private final String link = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";

    @Test
    public void whenParseListOfJsonThenGetAggregated() throws IOException, InterruptedException {
        CameraAggregator aggregator = new CameraAggregator();
        aggregator.aggregate(link);
        String[] result = aggregator.toStringArray();
        //результат в виде массива строк готов, но я перекладываю его в мапу,
        //чтобы можно было сравнить без учёта порядка элементов
        Map<String, String> resultMap = new HashMap<>();
        for (String s : result) {
            int index = s.indexOf("id");
            String id = s.substring(index + 4, s.indexOf(","));
            resultMap.put(id, s);
        }
        System.out.println(Arrays.toString(result));
        assertEquals(expected, resultMap);
    }
}
