package ru.job4j.serialization.json;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Object {
    private int number = 3;

    private boolean cond = true;

    private String str = "privet";

    private SimpleObject simpleObject = new SimpleObject(str);
    private int[] numbers = {1, 2, 3};

    public Object() {
    }

    public SimpleObject getSimpleObject() {
        return simpleObject;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getNumber() {
        return number;
    }

    public boolean isCond() {
        return cond;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Object{");
        sb.append("number=").append(number);
        sb.append(", cond=").append(cond);
        sb.append(", str='").append(str).append('\'');
        sb.append(", simpleObject=").append(simpleObject);
        sb.append(", numbers=").append(Arrays.toString(numbers));
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Object object = new Object();
        JSONObject jsonSimpleObject = new JSONObject("{\"str\" : \"privet\"}");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        JSONArray jsonNum = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", object.getNumber());
        jsonObject.put("cond", object.isCond());
        jsonObject.put("str", object.getStr());
        jsonObject.put("simpleObject", jsonSimpleObject);
        jsonObject.put("numbers", jsonNum);

        System.out.println(jsonObject.toString());
        JSONObject abc = new JSONObject(object);
        Gson gson = new Gson();
        Object aa = gson.fromJson(abc.toString(), Object.class);
        System.out.println(aa.cond);
    }
}
