package isedu.toy.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import isedu.toy.app.StudentReq;
import isedu.toy.common.AdmApiResponse;
import isedu.toy.domain.Student;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class RestTemplateTest {
    private final String GET_STUDENT = "http://localhost:7500/api/v1/students/1";
    private final String POST_STUDENT = "http://localhost:7500/api/v1/students";
    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateTest.class);

    @Test
    public void saveStudentRestApiTest(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AdmApiResponse> response = restTemplate.postForEntity(POST_STUDENT,
                StudentReq.builder().name("test").build(),
                AdmApiResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void findStudentRestApiTest() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AdmApiResponse> response = restTemplate.getForEntity(GET_STUDENT, AdmApiResponse.class);

        ObjectMapper mapper = new ObjectMapper();

        HashMap<String, Object> tmpMap = new HashMap<>();
        tmpMap = (HashMap<String, Object>) response.getBody().getData();

        StudentReq s = mapper.convertValue(tmpMap.get("name"), StudentReq.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getMsg(){
        try{
            URI uri = UriComponentsBuilder.
                    fromUriString("http://localhost:7500")
                    .path("/api/msg")
                    .build()
                    .toUri();

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

            LOGGER.info("API Response Status Code : " + response.getStatusCode().toString());
            LOGGER.info("API Response Message : " + response.getBody().toString());

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isEqualTo("Hello RestTemplate!");
        }catch (Exception e)
        {
            if(e.getCause() instanceof ConnectException){
                LOGGER.info("API Server Not Connect!");
            }else{
                LOGGER.info(e.getCause().toString());
            }
        }
    }
}
