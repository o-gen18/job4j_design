package net;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class TestIO {

    public static void scanRead() throws IOException {
        String separator = File.separator; //берём системный разделитель папок (слеш)
        String way = "C:\\Users\\Олег\\Desktop\\programming\\source for training\\Hey, ReadMe.txt";

        File file = new File(way); // файл - абстракция, символизирующая файл в компе

        Scanner scanner = new Scanner(file, "windows-1251");
        // исключение выкидывает если файл не находит

        while (scanner.hasNextLine()) {  //до тех пор пока в файле есть строки цикл работает
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }

    public static void main(String[] args) throws IOException {
        scanRead();
    }
}
