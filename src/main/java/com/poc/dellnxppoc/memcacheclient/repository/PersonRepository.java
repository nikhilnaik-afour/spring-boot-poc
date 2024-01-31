package com.poc.dellnxppoc.memcacheclient.repository;
import com.poc.dellnxppoc.memcacheclient.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
    Person getPersonById(String id);
}
