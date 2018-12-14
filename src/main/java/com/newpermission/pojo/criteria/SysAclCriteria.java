package com.newpermission.pojo.criteria;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SysAclCriteria {

	@NotEmpty
	private String name;
	
	@NotEmpty
	private Integer aclModelId;
	
	private String url;
	
	private Integer type;
	
	private Integer status;
	
	private Integer seq;
	
	private String ip;
}
