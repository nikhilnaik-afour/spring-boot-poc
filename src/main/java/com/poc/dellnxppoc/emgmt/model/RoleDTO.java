/**
 * 
 */
package com.poc.dellnxppoc.emgmt.model;

import com.poc.dellnxppoc.emgmt.common.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

	private RoleEnum roleId;

	private String roleName;

}
