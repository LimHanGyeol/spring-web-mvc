package com.example.springmvc.person;

import com.example.springmvc.person.domain.Person;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Spring Web Mvc
 * WebMvcConfigurer 1부 Formatter
 */
// @Component
public class PersonFormatter implements Formatter<Person> {

    @Override
    public Person parse(String text, Locale locale) throws ParseException {
        return new Person(text);
    }

    @Override
    public String print(Person object, Locale locale) {
        return object.toString();
    }
}
