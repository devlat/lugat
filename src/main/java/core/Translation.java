package core;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Класс для хранения перевода.
 */
public class Translation {
    private JSONObject translate;

    /**
     * Инициализация объекта JSON
     * @param jsonSrc
     */
    public Translation (String jsonSrc) {
        JSONParser parser = new JSONParser();

        try {
            this.translate = (JSONObject) parser.parse(jsonSrc);
        } catch (ParseException e) {
            System.err.println("Translation => Parse exception.");
            System.exit(1);
        }
    }

    /**
     * Получить перевод.
     * @return Перевод
     *
     * @todo Если слово не правильное, то выбрасывается java.lang.IndexOutOfBoundsException
     */
    public String getTranslation() {
        JSONObject wordDef = (JSONObject) ((JSONArray) this.translate.get("def")).get(0);

        JSONArray translate = (JSONArray) wordDef.get("tr");

        String result = (String) ((JSONObject) translate.get(0)).get("text");

        return result;
    }
}
