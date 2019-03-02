package com.boehle.cmdbkotlin.model

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Movie (
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	var id: Long?,
	
	@Column(unique=true)
	var name: String,
	
	var genre: String? = null,
	var year: String? = null,
	var director: String? = null,
	var rating: Double? = 0.0
) 