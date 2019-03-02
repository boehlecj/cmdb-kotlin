package com.boehle.cmdbkotlin.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import com.boehle.cmdbkotlin.model.Movie
import com.boehle.cmdbkotlin.service.MovieService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import java.util.Optional
import org.springframework.web.bind.annotation.ExceptionHandler
import org.hibernate.exception.ConstraintViolationException
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.WebRequest

@RestController
@RequestMapping("api/")
class MovieController (
	@Autowired
	val movieService : MovieService
	) {
	
	@GetMapping("time")
    fun getCurrentTime():String {
		val current = LocalDateTime.now()
		val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
		val formatted = current.format(formatter)
		return formatted
    }
	
	@GetMapping("movie/list")
    fun getAllMovies() : List<Movie>  {
        return movieService.getAllMovies();
    }

    @GetMapping("movie/{id}")
    fun getMovie(@PathVariable("id") id : Long) :  Movie {
		return movieService.getMovieById(id);
    }

    @DeleteMapping("movie/{id}")
    fun deleteMovie(@PathVariable("id") id : Long) {
        movieService.delete(id)
    }

    @PostMapping("movie")
    fun saveOrUpdateMovie(@RequestBody movie : Movie) : Long? {
        movieService.saveOrUpdate(movie);
        return movie.id;
    }
	
	@ExceptionHandler(ConstraintViolationException::class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	fun handleConstraintViolation(ex: ConstraintViolationException, request: WebRequest):ResponseEntity<String> {
		return ResponseEntity<String>("Invalid movie data. This movie already exists.", HttpStatus.BAD_REQUEST);
	}

}