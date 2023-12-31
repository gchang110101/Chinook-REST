package com.personal.Chinook.controllers;

import com.personal.Chinook.DTO.GenreDTO;
import com.personal.Chinook.models.Genre;
import com.personal.Chinook.services.entity_services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService service;

    @Autowired
    public GenreController(@Qualifier("GenreService") GenreService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllGenres() {
        List<Genre> responseList = service.getAll();

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/searchid/{genreId}")
    public ResponseEntity<?> getGenreById(@PathVariable("genreId") Integer id) {
        Optional<Genre> responseGenre = service.getById(id);

        return new ResponseEntity<>(responseGenre, HttpStatus.OK);
    }

    @GetMapping("/searchname/{genreName}")
    public ResponseEntity<?> getGenreByName(@PathVariable("genreName") String name) {
        List<Genre> responseList = service.getByName(name);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/allcount")
    public ResponseEntity<?> getGenreCount() {

        return new ResponseEntity<>(service.getAll().size(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGenre(@RequestBody GenreDTO genreDTO) {
        Genre responseGenre = service.persist(genreDTO);

        return new ResponseEntity<>(responseGenre, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGenre(@RequestBody GenreDTO genreDTO) {
        service.update(genreDTO);

        return new ResponseEntity<>("The Genre has been successfully updated!", HttpStatus.OK);
    }

    @DeleteMapping("/remove/{genreId}")
    public ResponseEntity<?> deleteGenre(@PathVariable("genreId") Integer id) {
        service.deleteById(id);

        return new ResponseEntity<>("The Genre was successfully deleted!", HttpStatus.NO_CONTENT);
    }
}
