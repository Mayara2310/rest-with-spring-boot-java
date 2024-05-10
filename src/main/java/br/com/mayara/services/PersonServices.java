package br.com.mayara.services;

import br.com.mayara.Exceptions.RequiredObjectIsNullException;
import br.com.mayara.Exceptions.ResourceNotFoundException;
import br.com.mayara.controller.PersonController;
import br.com.mayara.data.vo.PersonVO;
import br.com.mayara.DozerMapper;
import br.com.mayara.model.Person;
import br.com.mayara.repositories.PersonRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@Log4j2
@Service
public class PersonServices {

//    private final AtomicLong counter = new AtomicLong();//Mockar um id
//    private Logger logger = Logger.getLogger(PersonServices.class.getName()); @Sl4j

    @Autowired
    PersonRepository personRepository;

    public List<PersonVO> findAll() {
        log.info("Finding all people");

        var persons = DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class)
                        .findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id){
        log.info("Finding one person");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records founf for this ID"));
        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        log.info("Creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        PersonVO vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

//    public PersonVO2 createV2(PersonVO2 personVO2) {
//        log.info("Creatig one person with V2");
//        var entity2 = personMapper.convertToVoEntity(personVO2);
//        var vo = personMapper.convertEntityToVo(personRepository.save(entity2));
//        return vo;
//    }

    public PersonVO update(PersonVO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        log.info("Updating one person");
        var entity = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        log.info("Deleting one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }


}
