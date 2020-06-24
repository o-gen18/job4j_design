package ru.job4j.io.exam;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Directory {

    public static void main(String[] args) {

        final Shell shell = new Shell();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");
    }

    static class Shell {

        private Path root = Paths.get("/");

        Shell cd(final String path) {
            if (path.equals("..")) {
                root = root.getParent();
            } else if (path.startsWith("//")) {
                String normalized = path.replaceAll("/", "");
                String newRoot = "/".concat(normalized);
                root = Paths.get(newRoot);
            } else if (!path.startsWith(".") && !path.endsWith(".")) {
                root = root.resolve(path);
            }
            return this;
        }

        public String path() {
            return root.toString().replaceAll("\\\\", "/");
        }
    }
}
