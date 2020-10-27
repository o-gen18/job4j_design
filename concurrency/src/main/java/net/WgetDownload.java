package net;

import java.io.*;
import java.net.URL;

public class WgetDownload {

    public void download(String[] args) {
        if (args.length != 3) {
            throw new IllegalStateException(
                    "Please, specify the url, output file and the preferred downloading speed");
        }

        int speed = Integer.parseInt(args[2]) * 1000;

        try (BufferedInputStream in = new BufferedInputStream(new URL(args[0]).openStream());
             FileOutputStream out = new FileOutputStream(args[1])) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead = 0;

            while (bytesRead != -1) {
                long start = System.currentTimeMillis();
                bytesRead = in.read(dataBuffer, 0, 1024);
                long finish = System.currentTimeMillis();

                if (bytesRead <= 0) {
                    break;
                }

                double actualTime = finish - start;
                double expectedTime = (bytesRead / speed) * 1000;
                out.write(dataBuffer, 0, bytesRead);

                if (actualTime < expectedTime) {
                    Thread.sleep((long) (expectedTime - actualTime));
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WgetDownload test = new WgetDownload();
        String[] arguments =
            {"https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml",
                    "pom_temp.xml", "200"};
        test.download(arguments);
    }
}


