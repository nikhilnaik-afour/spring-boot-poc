/**
 * 
 */
package com.poc.dellnxppoc.emgmt.entity;

import java.time.LocalDateTime;
import java.util.Set;

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
@Table(name = "event", schema = "event_mgmt")
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="event_id")
	private Integer eventId;
	
	@Column(name="event_name", length=100, nullable=false, unique=false)
	private String eventName;
	
	@Column(name="start_at")
	private LocalDateTime startAt;
	
	@Column(name="end_at")
	private LocalDateTime endAt;
	
	@OneToOne(cascade= CascadeType.MERGE)
	@JoinColumn(name="owner")
	private User owner;
	
	@Column(name="is_closed", nullable=false)
	private boolean isClosed;
	
	@Column(name="location")
	private String location;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@OneToMany( mappedBy = "event",fetch = FetchType.EAGER,cascade= CascadeType.MERGE)
	private Set<Esession> sessions;
	
	@ManyToMany(mappedBy = "events",fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	private Set<User> visitors;

}
