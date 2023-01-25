package hello.springmvc.basic.request;

import hello.springmvc.basic.request.param.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    /**
     * 파라미터명과 변수명이 같으면 괄호안의 파라미터명 생략가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 파라미터명과 변수명이 같으면 @RequestParam 애노테이션도 생략가능
     * -> 생략이 과하다...
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            String username,
            int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * required 필수값 여부 설정 가능  (default : 필수)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 파라미터가 null 이거나 ''으로 들어 온 경우 defaultValue를 설정할 수 있음
     * required 설정값이 의미가 없어짐 (널로 들어오면 설정값으로 세팅이 되기 때문에)
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /**
     * 파라미터를 맵으로 한번에 받을수 있음 (사실상.. 잘쓰진않음)
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /**
     * 스프링mvc의 @ModelAttribute 실행
     * 1. HelloData 객체 생성
     * 2. 요청 파라미터의 이름으로 HelloData객체의 *프로퍼티를 찾는다.
     * 3. 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩)
     *
     * 프로퍼티란?
     * -> 객체에 getXxxx, setXxxx 메서드가 있으면, 이 객체는 xxxx라는 프로퍼티를 가지고 있다.
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략가능
     * -> 단순타입의 경우 @RequestParam, 나머지는 @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

}
