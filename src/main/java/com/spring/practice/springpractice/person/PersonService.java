package com.spring.practice.springpractice.person;

import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private static List<Person> people = new ArrayList<>();

    static {
        List<Job> jobs = new ArrayList<>();
        Job job1 = new Job("Perficient","Technical Consultant",1);
        Job job2 = new Job("Target","Cashier",3);
        Job job3 = new Job("GameStop","CEO",22);
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);
        Person person1 = new Person("Wyatt",60,jobs);
        people.add(person1);
    }
    public List<Person> getAllPeople(){return people;}
    public Optional<Person> getPerson(String name){
        return people.stream()
                .filter(p ->p.getName().equalsIgnoreCase(name))
                .findFirst();
    }
    public Optional<List<Job>> getAllJobs(String name){
        Optional<Person> optionalPerson = people.stream()
                .filter(p ->p.getName().equalsIgnoreCase(name))
                .findFirst();
        if(optionalPerson.isEmpty()) return Optional.empty();
        return Optional.ofNullable(optionalPerson.get().getJobs());
    }
    public Optional<Job> getJob(String name, String companyName){
        Optional<List<Job>> optionalJobs = getAllJobs(name);
        if(optionalJobs.isEmpty()) return Optional.empty();
        return optionalJobs.get()
                .stream()
                .filter(job -> job.getCompanyName().equalsIgnoreCase(companyName))
                .findFirst();
    }
    public String addNewJob(String name, Job newJob){
        Optional<List<Job>> optionalJobs = getAllJobs(name);
        if(optionalJobs.isEmpty()) return null;
        optionalJobs.get().add(newJob);
        return newJob.getCompanyName();
    }
    public String updateJob(String name,String companyName, Job jobToUpdate){
        Optional<List<Job>> optionalJobs = getAllJobs(name);
        if(optionalJobs.isEmpty()) return null;
        optionalJobs.get()
                .removeIf(job ->job.getCompanyName().equalsIgnoreCase(companyName));
        optionalJobs.get().add(jobToUpdate);
        return jobToUpdate.getCompanyName();
    }
    public String deleteJob(String name, String companyName){
        Optional<List<Job>> optionalJobs = getAllJobs(name);
        if(optionalJobs.isEmpty()) return null;
        boolean removed = optionalJobs.get()
                .removeIf(job ->job.getCompanyName().equalsIgnoreCase(companyName));
        if(!removed) return null;
        return companyName;
    }
}
