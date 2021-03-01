package com.example.springmvc.event;


import com.example.springmvc.event.domain.Event;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Spring Web MVC 활용
 * 데이터 바인더 @InitBinder
 * Java 객체로 InitBinder 에 addValidator 에 추가하여 사용하거나,
 * Component 로 Bean 등록을 하여 DI 한 후 필요한 곳에 명시적으로 사용할 수 있다.
 * 하지만 Bean 을 등록해서 사용하는 방식으로 하면 DataBinder 에 대한 학습이 되지 않으므로,
 * 직접 초기화 하여 주입하는 방식으로 사용한다.
 */
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;
        if (event.getName().equalsIgnoreCase("aaa")) {
            errors.rejectValue("name", "wrongValue", "the value is not allowed");
        }
    }
}
