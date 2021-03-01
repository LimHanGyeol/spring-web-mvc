package com.example.springmvc.event.controller;

import com.example.springmvc.event.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Spring Web MVC 활용
 * RequestBody, HttpEntity
 * RequestBody 확인용 컨트롤러. 예제 이름 부족으로 RequestBody ClassName 사용
 * 원하는 HttpMessageConverter 가 없을 경우.. WebConfig - ExtendsMessageConverters 를 추가하여 사용할 수 있다.
 * configureMessageConverters 도 있지만 이 메서드를 사용하면 기존에 등록되어 있던 HttpMessageConverters 가 다 사용이 불가능해진다.
 *
 */
@RestController
@RequestMapping("/api/events")
public class EventRequestBodyController {

    /**
     * HttpEntity<T> 를 사용하면 RequestBody 어노테이션을 사용하지 않고도,
     * HttpMessageConverter 가 Body 를 Conversion 해줄 것이다.
     * RequestBody 어노테이션으로 가져온 타입과 다르게 HttpEntity 는 Header 도 가져올 수 있다.
     * HttpEntity<Event> request
     * MediaType contentType = request.getHeaders().getContentType();
     * return request.getBody();
     */
    @PostMapping("")
    public ResponseEntity<Event> create(@Valid @RequestBody Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        // db save
        return ResponseEntity.ok().body(event);
    }

    @ExceptionHandler
    public ResponseEntity<String> errorHandler() {
        return ResponseEntity.badRequest().body("can't create event as ...");
    }

}
