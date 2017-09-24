package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс хранит конфигурацию.
 */
public final class Config {
    private static final String path = "src\\main\\resources\\config.properties";

    private static final Properties config = new Properties();

    /**
     * Загрузить файл конфигурации.
     * @throws FileNotFoundException
     */
    public static void load() throws FileNotFoundException {
        File propFile = new File(path);

        if (!propFile.exists()) {
            throw new FileNotFoundException();
        }

        try (FileInputStream fis = new FileInputStream(propFile)) {
            config.load(fis);
        } catch (IOException e) {
            System.err.println("Config => IO exception.");
            System.exit(1);
        }
    }

    /**
     * Возвращает свойство.
     * @param key Ключ свойства
     * @return
     */
    public static String get(String key) {
        return config.getProperty(key);
    }
}
