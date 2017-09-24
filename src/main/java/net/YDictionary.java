package net;

import java.io.*;
import java.net.*;
import java.util.Properties;
import core.Translation;
import config.Config;

/**
 * Клиент для доступа к Яндекс.Словарь.
 */
public class YDictionary {
    private static YDictionary instance;

    //private Properties config = new Properties();

    private YDictionary () {
        /*try (FileInputStream fis = new FileInputStream("src\\main\\resources\\config.properties")) {
            this.config.load(fis);
        } catch (FileNotFoundException e) {
            System.err.println("YDictionary => config.properties not found.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("YDictionary => IO error.");
            System.exit(1);
        }*/
    }

    /**
     * Получить экземпляр объекта.
     * @return YDictionary
     */
    public static synchronized YDictionary getInstance() {
        if (instance instanceof YDictionary) {
            return instance;
        }

        instance = new YDictionary();

        return instance;
    }

    /**
     * Возвращает перевод слова.
     * @param srcWord
     * @return Перевод
     */
    public Translation translate(String srcWord) {
        StringBuilder result = new StringBuilder();
        URL targetUrl = this.getUrl(srcWord);

        try {
            if (targetUrl != null) {
                URLConnection conn = targetUrl.openConnection();
                BufferedReader bin = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String inputLine;

                while ((inputLine = bin.readLine()) != null) {
                    result.append(inputLine);
                }
            }
        } catch (IOException e) {
            System.err.println("YDictionary => IO exception.");
        }

        return new Translation(result.toString());
    }

    /**
     * Возвращает ключ API.
     * @return Ключ API
     */
    private String getApiKey() {
        //return this.config.getProperty("api.key");
        return Config.get("api.key");
    }

    /**
     * Возвращает URL API.
     * @return URL API
     */
    private String getApiUrl() {
        //return this.config.getProperty("api.url");
        return Config.get("api.url");
    }

    /**
     * Возвращает сформированный URL.
     * @param srcWord Искомое словао
     * @return URL
     */
    private URL getUrl(String srcWord) {
        URL url = null;

        try {
            url = new URL(this.getApiUrl() + "?key=" + this.getApiKey() + "&lang=en-ru&text=" + srcWord);
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL!");
        }

        return url;
    }
}
