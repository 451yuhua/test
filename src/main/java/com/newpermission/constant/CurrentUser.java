package com.newpermission.constant;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CurrentUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1953859484403703769L;

	private String token;
	
	private Integer id;
	
	/**
     * 用户名称
     */
    private String username;
    
    /**
     * 手机号
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String mail;
    
    /**
     * 用户所在部门的id
     */
    private Integer deptId;

    /**
     * 状态，1：正常，0：冻结状态，2：删除
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 最后一次更新时间
     */
    private Date operateTime;

    /**
     * 最后一次更新者的ip地址
     */
    private String operateIp;
    
    private Integer redisVersion;
    
    
}
