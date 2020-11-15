package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteArrayObject {
    public static void main(String[] args) {
        Person first = new Person(3, "Sam");
        Person second = new Person(4, "Tom");
        Person[] array = {first, second, new Person(5, "Nuck")};

        try {
            FileOutputStream fos = new FileOutputStream("WriteArrayObject.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeInt(array.length); //сначала пишется длина массива

            for (Person p : array) { //поочерёдно каждый элеент массива пишется в файл
                oos.writeObject(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        writeTheWholeArray();
    }

    public static void writeTheWholeArray() {
        Person first = new Person(3, "Sam");
        Person second = new Person(4, "Tom");
        Person[] array = {first, second, new Person(5, "Nuck")};

        try {
            FileOutputStream fos = new FileOutputStream("WriteWholeArrayObject.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(array); // пишем массив как объект
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
