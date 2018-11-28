package com.newpermission.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* Created by Mybatis Generator on 2018/11/28
*/
@Table(name = "sys_user_acl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SysUserAcl {
    @Id
    private Integer id;

    /**
     * 部门id
     */
    @Column(name = "dept_id")
    private Integer deptId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 权限id
     */
    @Column(name = "acl_id")
    private Integer aclId;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 操作时间
     */
    @Column(name = "operate_time")
    private Date operateTime;

    /**
     * 操作ip
     */
    @Column(name = "operate_ip")
    private String operateIp;
}