package com.poc.dellnxppoc.memcacheclient.controller;

import java.util.Calendar;
import java.util.Date;

import com.poc.dellnxppoc.memcacheclient.entity.Person;
import com.poc.dellnxppoc.memcacheclient.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("person")
public class MyController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private PersonService personService;

    @GetMapping()
    public Person getPerson(@RequestParam String id) {

        Date startTime = Calendar.getInstance().getTime();
        Person person = personService.getPerson(id);
        Date endTime = Calendar.getInstance().getTime();
        LOGGER.info(
                "Time taken for the request: " + (endTime.getTime() - startTime.getTime()) + "ms");

        return person;
    }

}
