/**
 * 
 */
package com.poc.dellnxppoc.emgmt.common;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class  AppResponse{
	
	private String message;
	
	private Object body;
	
	private HttpStatus status;
}
