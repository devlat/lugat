package core;

import net.YDictionary;
import core.Translation;
import io.WordsLoader;
import config.Config;

import java.io.FileNotFoundException;

public class App {
    private static final String defaultPath = "C:\\Users\\Gadel\\Desktop";

    public static void main(String args[]) {
        System.out.println("Start...");

        try {
            // Инициализируем конфигурационный файл
            Config.load();

            WordsLoader.loadWords("C:\\Users\\Gadel\\Desktop\\java_input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Thread thread1 = new Thread(new Runnable() {
            YDictionary dict = YDictionary.getInstance();

            public void run() {
                while (WordsLoader.srcWords.size() != 0) {
                    String word = WordsLoader.srcWords.pop();
                    Translation tr = this.dict.translate(word);
                    System.out.println("thread1:" + word + " - " + tr.getTranslation());
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            YDictionary dict = YDictionary.getInstance();

            public void run() {
                while (WordsLoader.srcWords.size() != 0) {
                    String word = WordsLoader.srcWords.pop();
                    Translation tr = this.dict.translate(word);
                    System.out.println("thread2:" + word + " - " + tr.getTranslation());
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
