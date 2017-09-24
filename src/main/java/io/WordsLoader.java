package io;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.io.*;
import java.util.Stack;

/**
 * Класс считывает слова из внешнего файла для дальнейшего перевода.
 */
public class WordsLoader {
    //public static ArrayList<String> srcWords = new ArrayList<String>();

    public static Stack<String> srcWords = new Stack<>();

    /**
     * Метод заружает в массив исходные слова.
     * @param path Путь к файлу с исходными словами
     * @throws FileNotFoundException
     */
    public static void loadWords(String path) throws FileNotFoundException {
        File source = new File(path);

        if (!source.exists()) {
            throw new FileNotFoundException();
        }

        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine == null) continue;

                srcWords.push(inputLine);
            }
        } catch (IOException e) {
            System.out.println("WordsLoader => IO exception.");
        }
    }

    /**
     * Синхронизированный метод для получения элементов из стека.
     * @return Исходное слово
     */
    public synchronized static String get() {
        return srcWords.pop();
    }
}
