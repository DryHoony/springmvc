package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController //
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); // @Slf4j 에노테이션으로 대체가능

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

//        System.out.println("name = " + name);
        // 출력문을 로그로 변경


        // 레벨 - application.properties 에서 출력할 로그 레벨 설정가능
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name); // 기본값
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}
