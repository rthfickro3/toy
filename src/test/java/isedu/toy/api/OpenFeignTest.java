package isedu.toy.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class OpenFeignTest {
    @Autowired
    private OpenFeignClient openFeignClient;

    @Test
    public void GetMsgTest(){
        String param = "Success!";
        String response = openFeignClient.getMessage(param);

        System.out.println("openFeignClient.getMessage() Result : " + response);
        assertThat(response).isEqualTo("Response Message is : " + param);
    }
}
