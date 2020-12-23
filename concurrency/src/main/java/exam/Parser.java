package exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.concurrent.*;

public class Parser implements Runnable {
    private Map<Integer, JsonObject> result;
    private JsonObject jsonObject;
    private ExecutorService pool;

    public Parser(Map<Integer, JsonObject> result, JsonObject jsonObject, ExecutorService pool) {
        this.result = result;
        this.jsonObject = jsonObject;
        this.pool = pool;
    }

    @Override
    public void run() {
        Future<JsonObject> sourceDataUrlFuture = pool.submit(
                new SubParser(jsonObject.getString("sourceDataUrl")));

        Future<JsonObject> tokenDataUrlFuture = pool.submit(
                new SubParser(jsonObject.getString("tokenDataUrl")));

        JsonObject sourceDataUrl = null;
        JsonObject tokenDataUrl = null;

        try {
            sourceDataUrl = sourceDataUrlFuture.get();
            tokenDataUrl = tokenDataUrlFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (sourceDataUrl == null || tokenDataUrl == null) {
            System.out.println("Future returned null");
            Thread.currentThread().interrupt();
        }

        JsonObject resultEntry = Json.createObjectBuilder()
                .add("id", jsonObject.getInt("id"))
                .add("urlType", sourceDataUrl.getString("urlType"))
                .add("videoUrl", sourceDataUrl.getString("videoUrl"))
                .add("value", tokenDataUrl.getString("value"))
                .add("ttl", tokenDataUrl.getInt("ttl")).build();
        result.put(jsonObject.getInt("id"), resultEntry);
    }

    private class SubParser implements Callable<JsonObject> {
        private final String dataUrl;

        public SubParser(final String dataUrl) {
            this.dataUrl = dataUrl;
        }

        @Override
        public JsonObject call() throws Exception {
            Document doc = null;
            try {
                doc = Jsoup.connect(dataUrl).ignoreContentType(true).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (doc == null) {
                return null;
            }

            Element el = doc.body();
            String array = el.text();
            return Json.createReader(new StringReader(array)).readObject();
        }
    }
}
