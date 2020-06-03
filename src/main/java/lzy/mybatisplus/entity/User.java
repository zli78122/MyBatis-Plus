package lzy.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    // @TableId(type = IdType.ID_WORKER) // mp自带策略，生成19位值，数字类型使用这种策略，比如long
    // @TableId(type = IdType.ID_WORKER_STR) // mp自带策略，生成19位值，字符串类型使用这种策略
    private Long id;

    private String name;
    private Integer age;
    private String email;

    // create_time
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // update_time
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    // 版本号
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;
}
