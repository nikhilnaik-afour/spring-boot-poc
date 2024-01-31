package com.poc.dellnxppoc.memcacheclient.service;
import java.util.Objects;

import com.poc.dellnxppoc.memcacheclient.entity.Person;
import com.poc.dellnxppoc.memcacheclient.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Cacheable("personCache")
    //@Cacheable(value = "personCache", key = "#id")
    public Person getPerson(String id) {
        System.out.println("Fetching data from the database for id: " + id);

        Person person = personRepository.getPersonById(id);
        if (Objects.nonNull(person)) {
            double salary = 5000.00;
            person.setSalary(salary);
        }
        return person;
    }
}