package isedu.toy.api;

import isedu.toy.app.StudentCreateService;
import isedu.toy.app.StudentReq;
import isedu.toy.common.AdmApiResponse;
import isedu.toy.query.NoStudentExecption;
import isedu.toy.query.StudentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentApi {
    private final StudentQueryService studentQueryService;
    private final StudentCreateService studentCreateService;

    @GetMapping("/api/v1/students/{id}")
    public AdmApiResponse<? extends Object> findStudent(@PathVariable Long id){
        try {
            return AdmApiResponse.ok(studentQueryService.findStudent(id));
        } catch (NoStudentExecption noStudentExecption) {
            return AdmApiResponse.notFound();
        }
    }

    @GetMapping("/api/v1/students")
    public AdmApiResponse<? extends Object> findStudent(){
        return AdmApiResponse.ok(studentQueryService.findAll());
    }

    @PostMapping("/api/v1/students")
    public AdmApiResponse<?> saveStudent(@RequestBody StudentReq studentReq){
        studentCreateService.save(studentReq);
        return AdmApiResponse.ok();


    }

}
