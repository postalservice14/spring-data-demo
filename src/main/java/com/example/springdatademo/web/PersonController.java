package com.example.springdatademo.web;

import com.example.springdatademo.data.entities.Person;
import com.example.springdatademo.data.repository.PersonRepository;
import java.util.Optional;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired private PersonRepository personRepository;

  @PostMapping
  public ResponseEntity<Person> create(@Validated @RequestBody Person person) {
    return ResponseEntity.ok(personRepository.save(person));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Person> findById(@PathVariable Long id) {
    return ResponseEntity.of(personRepository.findById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Person> patchUser(@PathVariable Long id, @RequestBody PersonUpdate personUpdate) {
    Optional<Person> personOpt = personRepository.findById(id);
    if(personOpt.isPresent()) {
      Person person = personOpt.get();
      if( StringUtils.hasText(personUpdate.getFirstName())) {
        person.setFirstName(personUpdate.getFirstName());
      }
      if(StringUtils.hasText(personUpdate.getLastName())) {
        person.setLastName(person.getLastName());
      }

      return ResponseEntity.ok(personRepository.save(person));
    }
    return ResponseEntity.notFound().build();
  }

  @GetMapping("/search/findByLastName")
  public ResponseEntity<Iterable<Person>> findByLastName(@RequestParam String lastName) {
    return ResponseEntity.ok(personRepository.findAllByLastName(lastName));
  }


  @GetMapping
  public ResponseEntity<Page<Person>> findAll(Pageable pageable) {
    return ResponseEntity.ok(personRepository.findAll(pageable));
  }

  @Data
  public static class PersonUpdate {
    @Length(max = 200, message = "Whoa, way too long")
    private String firstName;
    private String lastName;
  }

}
