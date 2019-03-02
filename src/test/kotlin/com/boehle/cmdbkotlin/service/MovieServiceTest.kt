package com.boehle.cmdbkotlin.service

import org.junit.runner.RunWith
import org.junit.Assert.assertTrue
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test
import com.boehle.cmdbkotlin.model.Movie
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@RunWith(SpringRunner::class)
@SpringBootTest
class MovieServiceTest {
	@Autowired
	lateinit var movieService : MovieService
	
	
	@Test
    fun getAllMoviesTest() {
		assertTrue(movieService.getAllMovies().size > 1);
	}
	
	@Test
    fun getMovieByIdTest() {
		assertTrue( movieService.getMovieById(1).name.equals("Goodfellas"))
	}
	
	@Test
	fun saveTest() {
		var movieName: String = "Iron Man"
		var movie : Movie = Movie(id = 100, name = movieName, genre = "Superhero", year = "2008", director = "John Faverau", rating = 4.0)
		movieService.saveOrUpdate(movie)

		assertTrue(movie.name.equals(movieName))
	}
	
	@Test
	fun updateTest() {
		var movie : Movie = movieService.getMovieById(3);
		var rating : Double? = movie.rating;
		movie.rating = 4.5;
		movieService.saveOrUpdate(movie);

		assertTrue(movie.rating != rating)
			
	}
	
	@Test
	fun deleteTest() {
		movieService.delete(1);
		assertTrue(movieService.getMovieById(1).id != 1L);
			
	}

}