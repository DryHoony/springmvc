package hello.springmvc.basic.request;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
//@RestController
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");

    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge){

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
        // @Controller 클래스에서 String 반환값 메서드 이므로 원래는 "ok" 라는 이름의 view 를 찾는다
        // But, 여기서는 @ResponseBody 로 인해 "ok" 라는 응답 메시지로 바로 반환 - @RestController 와 같은 효과
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, // "username" 키값 생략 가능, 단! 요청 파라미터명과 일치해야 한다.
                                 @RequestParam int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){ // String, int 등 단순 타입이면 @RequestParam 도 생략가능. 단! 요청 파라미터명과 일치해야 한다.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // required = true 이면 해당 값이 꼭 들어와야 한다. - 없으면 오류
            @RequestParam(required = false) Integer age){ // 스펙상 int 타입에 null이 들어갈 수 없으므로 Integer 타입으로 사용해야 한다. int 타입으로 실행했을 때 500 Error 남(서버 에러)

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username, // 넘어온 값이 없으면 defaultValue 로 설정한 값 할당
            @RequestParam(defaultValue = "-1") int age){ // 요건 int 가능하다

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap( @RequestParam Map<String, Object> paramMap){ // 모든 요청파라미터 다 받고싶다!!

        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        // @ModelAttribute 가 있으면
        // HelloData 객체를 생성하고, 객체의 프로퍼티를 찾아, setter를 호출하여 파라미터 의 값을 입력(바인딩)한다.

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData); // 요렇게도 된다. @ToString 적용되어 있으므로
        return "ok";
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){ // ModelAttribute 생략할 수 있다.

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);
        return "ok";
    }

    // 정리! - 스프링은 해당 생략시 다음과 같은 규칙을 적용
    // String, int, Integer 같은 단순 타입 >> @RequestParam
    // 나머지 >> @ModelAttribute
    // argument resolver 로 지정해둔 타입은 예외




















}
