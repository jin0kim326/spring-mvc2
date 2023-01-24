package hello.springmvc;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring...";

        log.trace("trace log = {} ", name);
        log.trace("trace log =" + name); // 더하기 연산이 일어나기때문에 출력이 되지않아도 리소스 낭비가 일어남


        log.debug("debug log = {} ", name);
        log.info("info log = {} ", name);
        log.warn("warn log = {} ", name);
        log.error("error log = {} ", name);

        /**
         * level별로 로그를 남길수 있음, default는 info (logging.lever.[package] : trace/debug 로 설정가능)
         * sout콘솔은 무조건 다 나오기 때문에 지양
         */
        return "ok";
    }
}
