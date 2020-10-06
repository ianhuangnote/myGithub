package io;

import java.io.File;

public class FileSeparator {

    public static void main(String[] args) {
        System.out.println("1:" + File.separator);
        System.out.println("2:" + File.separatorChar);
        System.out.println("3:" + File.pathSeparator);
        System.out.println("4:" + File.pathSeparatorChar);
    }
}
