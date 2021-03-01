package com.example.springmvc.config;

import com.example.springmvc.event.EventException;
import com.example.springmvc.event.EventValidator;
import com.example.springmvc.event.controller.EventApiController;
import com.example.springmvc.event.controller.EventController;
import com.example.springmvc.event.controller.EventRequestBodyController;
import com.example.springmvc.event.domain.Event;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring Web Mvc
 * ControllerAdvice, ExceptionHandler, InitBinder, ModelAttribute
 * 전역적으로 적용이 아닌, 범위를 정해주고 싶다면 범위를 지정해 줄 수 있다.
 * RestControllerAdvice 가 있는데 이는 Controller 와 같이 ResponseBody 가 컴포짓 어노테이션으로 만들어진 것 이다.
 * 사용 시 응답 본문. ResponseBody 로 만들어져 리턴이 된다.
 */
@ControllerAdvice(assignableTypes = {EventController.class, EventApiController.class, EventRequestBodyController.class})
public class WebMvcAdvice {

    /**
     * ExceptionHandler 를 정의해 놓으면 RuntimeException 을 던져도,
     * 가장 구체적인 에러가 매핑된다. (EventException)
     * n 개 이상을 받을 수 있지만 그럴 경우 상위 타입으로 받아야 한다.
     * 이 경우 EventException 의 상위 타입인 RuntimeException 을 설정했다.
     */
    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException exception, Model model) {
        model.addAttribute("message", "event error");
        return "error";
    }

    /**
     * 모든 메서드 전에 initBinder 를 호출 한다.
     * 정의한 타입이 스프링이 지원하지 않는 타입일 경우 커스텀 포매터를 만들 수 있다.
     * Java 객체로 InitBinder 에 addValidator 에 추가하여 사용하거나,
     * Component 로 Bean 등록을 하여 DI 한 후 필요한 곳에 명시적으로 사용할 수 있다.
     * InitBinder 에 값을 주면 해당 이름의 ModelAttribute 를 바인딩 받을 때에만 작동이 된다.
     */
    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id"); // 받고 싶지 않은 필드 값을 거를 수 있음.
        // webDataBinder.setAllowedFields("id"); // 입력 받고 싶은 필드들만 정의할 수 있다.
        webDataBinder.addValidators(new EventValidator());
    }

//    ModelAttributes 를 전역적으로 사용하는 방식은 2가지가 있다.
//    아래 메서드처럼 기본적으로 사용하는 방식과,
//    리턴타입을 리턴 하고 ModelAttribute 이름을 주는 방법이 있다.
//    @ModelAttribute
//    public void categories(Model model) {
//        model.addAttribute("categories", List.of("study", "seminar", "hobby", "social"));
//    }

    @ModelAttribute("categories")
    public List<String> categories(Model model) {
        return List.of("study", "seminar", "hobby", "social");
    }

    /**
     * 아래 eventsForName 을 복사 후 형식을 바꾼 메서드이다.
     * ModelAttributes 를 사용할 경우 RequestMapping 어노테이션 부에
     * ModelAttributes 어노테이션을 사용하면 Return 타입이 자동으로 Model 에 담기게 된다.
     * 그러면 Model 관련된 코드를 제거할 수 있어 좀 더 깔끔한 코드 작성이 가능해진다.
     * 그리고 ModelAttributes 어노테이션은 생략도 가능하다.
     * 자주 사용하는 방법은 아니다.
     */
    @GetMapping("/events/form/name1")
    @ModelAttribute
    public Event eventsModel() {
        return new Event(2L);
    }
}
