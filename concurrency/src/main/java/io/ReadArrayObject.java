package io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class ReadArrayObject {
    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("WriteArrayObject.txt");
            ObjectInputStream ois = new ObjectInputStream(in);

            int length = ois.readInt();
            Person[] people = new Person[length];
            for (int i = 0; i < length; i++) {
                people[i] = (Person) ois.readObject();
            }

            ois.close();
            System.out.println(Arrays.toString(people));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        readWholeArray();
    }

    public static void readWholeArray() {
        try {
            FileInputStream in = new FileInputStream("WriteWholeArrayObject.txt");
            ObjectInputStream ois = new ObjectInputStream(in);

            Person[] people = (Person[]) ois.readObject();
            ois.close();
            System.out.println(Arrays.toString(people));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
