package isedu.toy.api;

import feign.RequestLine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenFeignApi {

    @GetMapping("/feign/msg")
    public String getMessage(@RequestParam("param") String val){
        return "Response Message is : " + val;
    }
}
