package ru.job4j.serialization.json;

import org.json.JSONObject;

public class Loader {
    public static void main(String[] args) throws Exception {
        Worker worker = new Worker(new String[]{"Достоинство 1", "Достоинство 2"}, Position.DIRECTOR, "Jon", 20, true);
        System.out.println(new JSONObject(worker));
    }
}
