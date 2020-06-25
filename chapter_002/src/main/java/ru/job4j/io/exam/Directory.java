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

        private StringBuilder root = new StringBuilder("/");

        Shell cd(final String path) {
            if (path.equals("..")) {
                int lastSlash = root.lastIndexOf("/");
                root.delete(lastSlash, root.length());
            } else if (path.startsWith("//")) {
                String normalized = path.replaceAll("/", "");
                String newRoot = "/".concat(normalized);
                root.delete(0, root.length());
                root.append(newRoot);
            } else if (!path.startsWith(".") && !path.endsWith(".") && !path.startsWith("/")) {
                if (root.charAt(root.length() - 1) != '/') {
                    root.append("/");
                }
                root.append(path);
            }
            return this;
        }

        public String path() {
            return root.toString();
        }
    }
}
