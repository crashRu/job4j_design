package ru.job4j.serialization.json;


import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.Contact;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);
        final Person person = new Person(false, 30, new Contact(111111, "999999999"), new String[]{"Worker", "Married"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.isSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(person).toString());
    }
}