package io;

import java.io.*;

public class ParseFile {
    private final File input; //сделал поля immutable для потокобезопасности
    private final File output;

    public ParseFile(File input, File output) {
        this.input = input;
        this.output = output;
    }

    private String getContent(String charset) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(input))) {
            byte[] array = new byte[8192]; //большой буффер для производительности
            while (in.read(array, 0, array.length) != -1) {
                output.append(new String(array, charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public synchronized String getContent() {
        return getContent("windows-1251"); //такая кодировка позволит считать кириллицу
    }

    public synchronized String getContentWithoutUnicode() {
        return getContent("ASCII"); //символы кодируются до 127
    }

    public synchronized void saveContent(String content) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(output)))) {
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
