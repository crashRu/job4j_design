package ru.job4j.io;

import java.io.ByteArrayOutputStream;

public class ByteArrayStream {

    public static void main(String[] args) {
        System.out.println();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        outStream.writeBytes(bytes2);
        System.out.println(outStream);
    }
}