/**
 * 
 */
package com.poc.dellnxppoc.emgmt.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Entity
@Table(name = "esession", schema = "event_mgmt")
public class Esession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="esession_id")
	private Integer esessionId;
	
	@Column(name="esession_title", length=100, nullable=false)
	private String esessionTitle;
	
	@Column(name="start_at")
	private LocalDateTime startAt;
	
	@Column(name="end_at")
	private LocalDateTime endAt;
	
	@ManyToOne
	@JoinColumn(name="event_id")
	private Event event;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;

}
