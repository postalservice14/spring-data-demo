package com.example.springdatademo.data.repository;

import com.example.springdatademo.data.entities.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

  Iterable<Person> findAllByLastName(String lastName);


  @Query("select p.firstName from Person p where p.firstName = 'John' or p.firstName = :firstName")
  Iterable<Person> findEngineers(@Param("firstName") String firstName);
}
