package isedu.toy.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApi {

    @GetMapping("/api/msg")
    public String testApi(){
        return "Hello RestTemplate!";
    }
}
