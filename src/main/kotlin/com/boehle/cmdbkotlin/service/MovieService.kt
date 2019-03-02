package com.boehle.cmdbkotlin.service

import org.springframework.beans.factory.annotation.Autowired
import com.boehle.cmdbkotlin.model.Movie
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.Objects
import org.springframework.dao.EmptyResultDataAccessException
import org.hibernate.exception.ConstraintViolationException
import java.lang.IllegalArgumentException

@Service
class MovieService (
	@Autowired
	private val movieRepository : MovieRepository ){
	
	fun getAllMovies() : List<Movie> {
        return movieRepository.findAll().toMutableList()
	}
	
	fun getMovieById(id: Long) : Movie {
		var movie : Optional<Movie> = movieRepository.findById(id);
		if (movie.isPresent()) {
			return movie.get()
		} else {
			return Movie(id=-1L, name="NotFound")
		}
	}
	
	fun saveOrUpdate(movie : Movie) {
		Objects.requireNonNull(movie.name);
		movieRepository.save(movie)
		
	}
	
	fun delete(id : Long) {
    	try {
    		movieRepository.deleteById(id)
    	} catch (ex : EmptyResultDataAccessException) {
            //Movie id to delete doesn't exist. Data is idempotent, don't do anything except log
        }
    }
}