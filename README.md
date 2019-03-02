# Chuck Movie Database Kotlin
An in memory movie database written in Kotlin using Spring Boot

## Implementation Details
```
The service uses the Spring Data CrudRepository instead of implementing a DAO pattern.
From start to finish this took 2 hours as it was my first Kotlin project.
Includes Unit Tests
```

## Service Route
```
/api/timeOfDay	Returns the current local time
/api/movie[/*]	Create, update, delete movie entry
/api/movie/list	Returns list of movie entries
```

## Docker
```
There is a Dockerfile included in the project but due to my system resources issue rung the docker toolkit I could not test build an iso.
I spent a lot of time on this project just troubleshooting my own docker toolkit issues.
```
