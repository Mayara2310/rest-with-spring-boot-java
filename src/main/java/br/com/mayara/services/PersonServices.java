package br.com.mayara.services;

import br.com.mayara.model.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@Log4j2
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();//Mockar um id
//    private Logger logger = Logger.getLogger(PersonServices.class.getName()); @Sl4j

    public List<Person> findAll(){
        log.info("Finding all people");

        List<Person> persons = new ArrayList<>();
        for (int i : IntStream.range(0, 8).toArray()){
            persons.add(mockPersons(i));
        }
        return persons;
    }

    public Person create(Person person){
        log.info("Creating one person");
        return person;
    }

    public Person update(Person person){
        log.info("Updating one person");
        return person;
    }

    public void delete(String id){
        log.info("Deleting one person");

    }
    public Person findById(String id){
        log.info("Finding one person");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Mayara");
        person.setLastName("Villa");
        person.setAddress("Ipiranga");
        person.setGender("Male");
        return person;
    }

    private Person mockPersons(int i) {
        log.info("Finding all people");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("FirstName");
        person.setLastName("LastName");
        person.setAddress("Brasil");
        person.setGender("Male");
        return person;
    }

}
