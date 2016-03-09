/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import entity.Person;
import java.util.List;

/**
 *
 * @author Iman H
 */
public class Converter {
    
    static Gson gson = new Gson();
    
    public static Person getPersonFromJson(String js) {
        Person p = gson.fromJson(js, Person.class);
        return p;
    }

    public static String getJSONFromPerson(Person p) {
        return gson.toJson(p);
    }

    public static String getJSONFromPersonList(List<Person> persons) {
        return gson.toJson(persons);
    }
    
    public static List<Person> getPersonListFromJson(String js) {
        List<Person> list = gson.fromJson(js, new TypeToken<List<Person>>(){}.getType());
        return list;
    }
}
