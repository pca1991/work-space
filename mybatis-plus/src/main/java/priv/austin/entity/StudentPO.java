package priv.austin.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import priv.austin.gender.GenderEnum;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
public class StudentPO implements Serializable {
    /**
     * 学生编号
     */
    @TableId(type = IdType.AUTO)
    private Integer studentId;

    /**
     * 姓名
     */
    private String studentName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    @EnumValue
    private GenderEnum gender;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}