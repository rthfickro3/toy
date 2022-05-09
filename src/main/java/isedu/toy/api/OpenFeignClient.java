package isedu.toy.api;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "test", url = "http://localhost:7500")
public interface OpenFeignClient {

    @GetMapping("/feign/msg")
    String getMessage(@RequestParam("param") String val);

/*    @RequestLine("GET /feign/msg")
    String getMessage(@RequestParam("param") String val);*/
}
