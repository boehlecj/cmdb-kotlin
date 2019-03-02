package com.boehle.cmdbkotlin.service

import org.springframework.data.repository.CrudRepository
import com.boehle.cmdbkotlin.model.Movie

interface MovieRepository : CrudRepository<Movie,Long> {}