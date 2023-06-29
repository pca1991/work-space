package priv.austin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import priv.austin.entity.StudentPO;
import priv.austin.service.StudentService;
import priv.austin.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentPO>
implements StudentService{

}




