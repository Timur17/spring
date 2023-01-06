package ru.otus.spring.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Person;
import ru.otus.spring.repostory.PersonRepository;
import ru.otus.spring.rest.dto.PersonDto;
import ru.otus.spring.rest.exeptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }


    @RequestMapping(path = "/persons", method = RequestMethod.GET)
    public List<PersonDto> getAllPersons(){
        return repository.findAll().stream().map(PersonDto::toDto).collect(Collectors.toList());
    }

    @RequestMapping(path = "/persons", method = RequestMethod.GET, params = "name") //http://localhost:8080/persons?name=Pushkin
    public PersonDto getPersonsByName(@RequestParam String name){
        Person person = repository.findByName(name).stream().findFirst().orElseThrow(NotFoundException::new);
        return PersonDto.toDto(person);
    }

    @GetMapping(path = "/persons/{id}") //http://localhost:8080/persons/1
    public PersonDto getPersonsById(@PathVariable long id){
        Person person = repository.findById(id).stream().findFirst().orElseThrow(NotFoundException::new);
        return PersonDto.toDto(person);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException e){
    return ResponseEntity.badRequest().body("Таких тут нет!");
    }
}
