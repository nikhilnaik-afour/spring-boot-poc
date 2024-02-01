/**
 * 
 */
package com.poc.dellnxppoc.emgmt.service;

import com.poc.dellnxppoc.emgmt.model.EsessionDTO;

import java.util.List;


/**
 * 
 */
public interface SessionService {

	List<EsessionDTO> findSessionEventByID(Integer eventId) ;

	EsessionDTO findSessionByID(Integer ID) ;

	EsessionDTO addSession(EsessionDTO dto) ;

	EsessionDTO updateSession(EsessionDTO dto) ;

	Boolean deleteSessionByID(Integer iD) ;

}
