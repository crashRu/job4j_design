package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 25;
        char feMail = 'm';
        double amount = 346.2;
        long ram = 68903;
        boolean smart = false;
        byte children = 2;
        short shortTest = 12;
        float floatTest = 11.1F;

        LOG.warn("age: {}\nfeMail: {}\namount: {}\nram: {}\nsmart: {}\nchildren {}\nshortTest: {}\nfloatTest: {}",
                age, feMail, amount, ram, smart, children, shortTest, floatTest);
    }
}