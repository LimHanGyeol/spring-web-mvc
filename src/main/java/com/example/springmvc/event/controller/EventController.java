package com.example.springmvc.event.controller;

import com.example.springmvc.event.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    // private final EventService eventService;

//    @GetMapping("/events")
//    public String events(Model model) {
//        model.addAttribute("events", eventService.getEvents());
//        return "events";
//    }

    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        Event event = new Event(2L);
        model.addAttribute("event", event);

        return "form-name";
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
