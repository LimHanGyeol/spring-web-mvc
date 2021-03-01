package com.example.springmvc.event.controller;

import com.example.springmvc.event.EventException;
import com.example.springmvc.event.EventValidator;
import com.example.springmvc.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring Web Mvc
 * 스프링 MVC 소개, Request Form Submit, SessionAttributes, SessionAttribute, Multi Form Submit
 * SessionAttributes 어노테이션을 사용하면 HttpSession 을 사용하고 값을 넣어주지 않아도,
 * 자동으로 세션에 값을 넣어준다.
 */
@RequiredArgsConstructor
@Controller
@SessionAttributes("event")
public class EventController {

//     private final EventService eventService;
//    private final EventValidator eventValidator;

//    @GetMapping("/events")
//    public String events(Model model) {
//        model.addAttribute("events", eventService.getEvents());
//        return "events";
//    }

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

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        throw new EventException();
//        Event event = new Event(2L);
//        model.addAttribute("event", event);

//        return "form-name";
    }

    /**
     * database save 를 가정하여 Event 를 Mocking 한다.
     * 해당 메서드에서 list 를 get 해오는 작업의 코드를 작성하면,
     * 새로고침할 경우 요청을 다시 보낼 것이냐는 경고성 alert 를 보여준다.
     * 이런 경우를 대비하여 "Post Redirect Get" 이라는 기법을 사용한다.
     * Post 요청을 보내면 redirect 로 list 로 보내고, list 에서 미리 생성한 목록을 출력한다.
     */
    @PostMapping("/events/form/name")
    public String create(@Valid @ModelAttribute Event event,
                         BindingResult bindingResult) {
        System.out.println(event.toString());
        if (bindingResult.hasErrors()) {
            return "form-name";
        }
        return "redirect:/events/form/limit";
    }

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "form-limit";
    }

    /**
     * RedirectAttributes 로 넘긴 값들은 URI 에 노출이 된다.
     * FlashAttributes 가 이를 막아준다.
     * FlashAttributes 을 사용하면 세션에 값이 전달이 되어 데이터가 URI 에 노출이 되지 않는다.
     * 그리고 임의의 객체를 저장할 수 있고, 전달한 데이터를 사용하면 삭제가 되어 일회성이라고 생각하면 된다.
     */
    @PostMapping("/events/form/limit")
    public String createFormLimit(@Valid @ModelAttribute Event event,
                                  BindingResult bindingResult,
                                  SessionStatus sessionStatus,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "form-limit";
        }
        sessionStatus.setComplete();
//        redirectAttributes.addAttribute("name", event.getName());
//        redirectAttributes.addAttribute("limitOfEnrollment", event.getLimitOfEnrollment());
        redirectAttributes.addFlashAttribute("newEvent", event);
        return "redirect:/events/list";
    }

    /**
     * 원래는 database 에서 select 해와야 한다.
     * 하지만 DB 설정을 하지 않아서 결과물 리스트를 수동으로 만들어 보여준다.
     *
     * RedirectAttributes 의 값은 @RequestParam 으로 받을 수 있다.
     * 이를 ModelAttributes 로도 받을 수 있는데, 이때 조심해야 한다.
     * SessionAttributes 에서 준 키값과 RedirectAttributes 모델을 같은 이름을 사용하면 안된다.
     * 우리는 createFormLimit 에서 session 을 Complete 하여 비웠는데,
     * RedirectAttributes 에서 같은 이름을 사용하면 해당 이름을 session 에서 찾는다.
     * 그래서 에러가 난다.
     * 이를 원활하게 사용하려면 SessionAttributes 에서 사용한 이름과 다른 이름을 부여해야 한다.
     *
     * FlashAttributes 로 보낸 데이터는 세션에 저장이 되어서,
     * ModelAttibutes 가 아닌 Model 로도 받을 수 있다.
     * @RequestParam String name,
     * @RequestParam int limitOfEnrollment,
     *
     * @ModelAttribute("newEvent") Event event,
     */
    @GetMapping("/events/list")
    public String getEvents(Model model, @SessionAttribute LocalDateTime visitTime) {
        System.out.println(visitTime);
        Event spring = new Event(1L, "spring", 10);

        Event event = (Event) model.asMap().get("newEvent");

        List<Event> events = new ArrayList<>();
        events.add(spring);
        events.add(event);

        model.addAttribute("events", events);

        return "/list";
    }


}
