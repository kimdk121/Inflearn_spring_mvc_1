package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    // private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        // 로그 이렇게 남기면 안됨
        // System.out.println("name = " + name);
        // 아래는 사용하지 않아도 무조건 연산은 들어가기 때문에 쓸모없이 cpu와 메모리를 사용하게 됨
        // log.trace(" trace log = " + name);

        log.trace(" trace log = {}" , name);
        log.debug(" debug log = {}" , name);
        log.info(" info log = {}" , name);
        log.warn(" warn log = {}" , name);
        log.error(" error log = {}" , name);
        return "ok";
    }
}
