package priv.austin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import priv.austin.entity.StudentPO;
import priv.austin.service.StudentService;
import priv.austin.service.TestService;

import javax.annotation.Resource;

/**
 * @author Austin
 * @description 测试
 * @date 2023/6/28 16:05
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    StudentService studentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void a() {
        LambdaUpdateWrapper<StudentPO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set( StudentPO::getStudentName, "a").eq( StudentPO::getStudentId, 1);
        studentService.update( updateWrapper);
        b();
    }

    public void b() {
        LambdaUpdateWrapper<StudentPO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set( StudentPO::getStudentName, "b").eq( StudentPO::getStudentId, 2);
        studentService.update( updateWrapper);
        c();
    }

    public void c() {
        LambdaUpdateWrapper<StudentPO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set( StudentPO::getStudentName, "c").eq( StudentPO::getStudentId, 3);
        studentService.update( updateWrapper);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
