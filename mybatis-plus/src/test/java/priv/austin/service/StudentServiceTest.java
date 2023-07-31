package priv.austin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import priv.austin.entity.StudentPO;
import priv.austin.gender.GenderEnum;

import javax.annotation.Resource;
import java.util.Arrays;

@SpringBootTest
class StudentServiceTest {
    @Resource
    StudentService studentService;

    @Test
    public void test(){
        StudentPO studentPO = new StudentPO();
        studentPO.setStudentName("小红");
        studentPO.setAge( 20);
        studentPO.setGender(GenderEnum.GIRL);
        studentService.save(studentPO);
    }

    @Test
    public void get(){
        StudentPO studentPO = studentService.getById(1);
        System.out.println( studentPO.toString());
    }

    @Test
    public void page(){
        Page<StudentPO> page = new Page<>(1,10);
        page.addOrder( OrderItem.descs());

        IPage<StudentPO> students = studentService.page( page, new QueryWrapper<>());
        System.out.println( students);
    }
}