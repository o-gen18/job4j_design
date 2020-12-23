package exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.json.*;
import javax.json.stream.JsonParser;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CameraAggregator {

    private ExecutorService pool = Executors.newCachedThreadPool();

    private Map<Integer, JsonObject> result = new ConcurrentHashMap<>();

    private int expectedResults = 0;

    public Map<Integer, JsonObject> getResult() {
        return result;
    }

    public void aggregate(String url) throws IOException {
        Document doc = Jsoup.connect(url)
                .ignoreContentType(true).get();
        Element el = doc.body();
        String body = el.text();
        JsonParser jsonParser = Json.createParser(new StringReader(body));
        jsonParser.next();
        JsonArray jsonArray = jsonParser.getArray();
        //Использую один пул потоков на все задания, даже на внутренние
        jsonArray.forEach((json) -> {
            pool.execute(new Parser(result, json.asJsonObject(), pool));
            expectedResults++;
        });
    }

    //Переместить из результирующей мапы в массив,
    //ожидаемый в задании, попутно форматируя строки json-а (добавить разделители).

    public String[] toStringArray() throws InterruptedException {

        //пока аггрегация не закончена, тело этого метода не должно выполняться
        while (!isFinished()) {
            Thread.currentThread().sleep(100);
        }
        String[] array = new String[expectedResults];
        int index = 0;
        for (JsonObject json : result.values()) {
            String s = json.toString()
                    .replaceAll("\\{", System.lineSeparator() + "\\{" + System.lineSeparator())
                    .replaceAll("}", System.lineSeparator() + "\\}")
                    .replaceAll(",", "," + System.lineSeparator());
            array[index++] = s;
        }
        return array;
    }

    public boolean isFinished() {
        return result.size() == expectedResults;
    }
}

