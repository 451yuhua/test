package com.newpermission.pojo.criteria;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysDeptCriteria {

	@NotEmpty
	private String name;
	
	@NotEmpty
	private Integer parentId;
	
	private String seq;
	
	private String operateIp;
	
	private String remark;
	
}
