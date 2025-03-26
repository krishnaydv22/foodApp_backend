package com.main.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image;
	 
	private String name;
	
	private String location;
	
   private String startedAt; // Event start time
	
    private String endsAt; // Event end time
    
    @ManyToOne
	@JsonIgnore
    private Restaurant restaurant;
	
	
	

}
