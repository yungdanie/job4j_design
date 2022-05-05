package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Object {
    private final int number = 3;
    private final boolean cond = true;
    private final String str = "privet";
    private final SimpleObject simpleObject = new SimpleObject(str);
    private final int[] numbers = {1, 2, 3};

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

    public String toGson(Object object) {
        Gson gson = new GsonBuilder().create();
        String gsonObject = gson.toJson(object);
        System.out.println(gsonObject);
        return gsonObject;
    }

    public Object fromGson(String gsonStr) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(gsonStr, Object.class);
    }

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.fromGson(object.toGson(object)));
    }
}
