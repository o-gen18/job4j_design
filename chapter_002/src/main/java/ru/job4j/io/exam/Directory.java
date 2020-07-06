package ru.job4j.io.exam;

import java.util.Stack;

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

        private Stack<String> root = new Stack<>();

        public Shell() {
            root.push("/");
        }

        public Shell cd(final String path) {
            if (path.equals("..")) {
                root.pop();
                root.pop();
                return this;
            } else if (path.startsWith("//")) {
                String normalized = path.replaceAll("/", "");
                root.removeAllElements();
                root.push("/");
                root.push(normalized);
                return this;
            } else if (!path.startsWith(".") && !path.endsWith(".") && !path.startsWith("/")) {
                if (!root.peek().equals("/")) {
                    root.push("/");
                }
                root.push(path);
                return this;
            }
            return this;
        }

        public String path() {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < root.size(); i++) {
                result.append(root.get(i));
            }
            return result.toString();
        }
    }
}
