package net;

public class Test {
    public static void main(String[] args) {
        System.out.println(0xff);
        byte b = 30;
        byte c = (byte) 130;
        int i = getByte()[3];
        System.out.println(i);
        System.out.println(c);
        System.out.println(getByte()[1] & 0xff);
        System.out.println(18 & 11);
        System.out.println(0x712f);
        String[][] matrix = new String[6][7];
        for (String[] row : matrix) {
            for (String cell : row) {
                System.out.print("Cell ");
            }
            System.out.println();
        }
        System.out.println(0xff);
        System.out.println(matrix.length);
        System.out.println(matrix[0].length);
        System.out.println(matrix[1].length);
        System.out.println(matrix[2].length);
        System.out.println(matrix[3].length);
        System.out.println(matrix[4].length);
        System.out.println(matrix[5].length);

    }

    public static byte[] getByte() {
        byte[] b = {1, 2, 3, 4, 5, 7};
        return b;
    }
}
