package priv.austin.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import priv.austin.entity.StudentPO;

import javax.annotation.Resource;

@SpringBootTest
class StudentServiceTest {
    @Resource
    StudentService studentService;

    @Test
    public void test(){
        StudentPO studentPO = new StudentPO();
        studentPO.setStudentName("小明");
        studentPO.setAge( 19L);
        studentService.save(studentPO);
    }
}