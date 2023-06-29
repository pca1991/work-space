package priv.austin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 
 * @author qxu
 * @TableName STUDENT
 */
@TableName(value ="STUDENT")
@Data
@KeySequence(value = "SEQ_STUDENT_ID")
public class StudentPO implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.INPUT)
    private Long studentId;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 年龄
     */
    private long age;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}