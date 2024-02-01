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
@Table(name = "user", schema = "event_mgmt")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="user_name", length=100, nullable=false)
	private String userName;
	
	@Column(name="first_name", length=100, nullable=false)
	private String firstName;
	
	@Column(name="last_name", length=100)
	private String lastName;
	
	@Column(name="password", length=100, nullable=false)
	private String password;
	
	@Column(name="is_active", nullable=false)
	private boolean isActive;
	
	@OneToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinTable(
	  name = "user_event_map", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "event_id"))
	private Set<Event> events;
}
