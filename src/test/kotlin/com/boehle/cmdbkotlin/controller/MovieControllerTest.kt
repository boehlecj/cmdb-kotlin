package com.boehle.cmdbkotlin.controller

import org.mockito.Mockito
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.web.servlet.MockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import com.boehle.cmdbkotlin.service.MovieService
import com.boehle.cmdbkotlin.model.Movie
import org.junit.Test
import org.springframework.http.MediaType
import java.util.Arrays

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {
	
	@Autowired
	lateinit var objectMapper: ObjectMapper
	@Autowired 
	lateinit var mvc: MockMvc
    @MockBean 
    lateinit var movieService: MovieService;
	
	@Test
    fun addMovieTest() {
        var movie: Movie = Movie(1, "Psycho", "horror", "1961", "Alfred Hitchcock", 5.0);
        var inputJson: String = objectMapper.writeValueAsString(movie);

        this.mvc.perform(post("/api/movie")
            .contentType(MediaType.APPLICATION_JSON)
            .content(inputJson))
            .andExpect(status().isOk());
    }
	
	@Test
    fun getAllMoviesTest() {
    	var movie1: Movie = Movie(1, "Psycho", "horror", "1961", "Alfred Hitchcock", 5.0);
    	var movie2: Movie = Movie(2, "Casino", "crime", "2000", "Martin Scorses", 5.0);
    	
    	Mockito.`when`(movieService.getAllMovies()).thenReturn(Arrays.asList(movie1, movie2));
    	
    	this.mvc.perform(get("/api/movie/list"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Psycho"))
        .andExpect(jsonPath("$[1].name").value("Casino"));
    }
    
    @Test
    fun getMovieTest() {
    	var movie: Movie = Movie(1, "Psycho", "horror", "1961", "Alfred Hitchcock", 5.0);	
    	Mockito.`when`(movieService.getMovieById(1)).thenReturn(movie);
    	
    	this.mvc.perform(get("/api/movie/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name").value("Psycho"));
    }
    
    @Test
    fun getMovieNotFoundTest() {
		var movie: Movie = Movie(-1, "Not Found", null, null, null, 0.0);	
		Mockito.`when`(movieService.getMovieById(2)).thenReturn(movie);   	
    	this.mvc.perform(get("/api/movie/2"))
        .andExpect(jsonPath("id").value("-1"));
    }
    
    @Test
    fun deleteMovieTest() {    	
    	this.mvc.perform(delete("/api/movie/2"))
        .andExpect(status().isOk());
    }
    
    @Test
    fun getCurrentTime() {
        this.mvc.perform(get("/api/time"))
            .andExpect(status().isOk());
    }

}