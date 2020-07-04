package ru.job4j.io.exam;

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

        //private StringBuilder root = new StringBuilder("/");
        private String root = "/";

        public Shell() {
        }

        public Shell(String path) {
            this.root = path;
        }

        Shell cd(final String path) {
            if (path.equals("..")) {
                int lastSlash = root.lastIndexOf("/");
                return new Shell(root.substring(0, lastSlash));
            } else if (path.startsWith("//")) {
                String normalized = path.replaceAll("/", "");
                return new Shell("/".concat(normalized));
            } else if (!path.startsWith(".") && !path.endsWith(".") && !path.startsWith("/")) {
                String result;
                if (root.endsWith("/")) {
                    result = root.concat(path);
                } else {
                    result = root.concat("/").concat(path);
                }
                return new Shell(result);
            }
            return this;
        }

        public String path() {
            return root;
        }
    }
}
