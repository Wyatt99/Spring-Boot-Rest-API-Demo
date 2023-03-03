package com.spring.practice.springpractice.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/people")
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @RequestMapping("/people/{name}")
    public Person getPerson(@PathVariable String name) {
        Optional<Person> personOptional = personService.getPerson(name);
        if (personOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return personOptional.get();
    }

    @RequestMapping("/people/{name}/jobs")
    public List<Job> getAllJobs(@PathVariable String name) {
        Optional<List<Job>> jobsOptional = personService.getAllJobs(name);
        if (jobsOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return jobsOptional.get();
    }

    @RequestMapping("/people/{name}/jobs/{companyName}")
    public Job getJob(@PathVariable String name, @PathVariable String companyName) {
        Optional<Job> jobOptional = personService.getJob(name, companyName);
        if (jobOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return jobOptional.get();
    }

    @RequestMapping(value = "/people/{name}/jobs", method = RequestMethod.POST)
    public ResponseEntity<Object> addNewJob(@PathVariable String name, @RequestBody Job job) {
        String companyName = personService.addNewJob(name, job);
        if (companyName.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{companyName}")
                .buildAndExpand(companyName)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/people/{name}/jobs/{companyName}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateJob(@PathVariable String name,
                                            @PathVariable String companyName, @RequestBody Job job){
        String returnName = personService.updateJob(name,companyName, job);
        if (returnName.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/people/{name}/jobs/{companyName}")
    public ResponseEntity<Object> deleteJob(@PathVariable String name,
                                            @PathVariable String companyName){
        String returnName = personService.deleteJob(name,companyName);
        if (returnName.isEmpty()) ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }
}

