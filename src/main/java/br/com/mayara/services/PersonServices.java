package br.com.mayara.services;

import br.com.mayara.Exceptions.ResourceNotFoundException;
import br.com.mayara.data.vo.v1.PersonVO;
import br.com.mayara.mapper.DozerMapper;
import br.com.mayara.model.Person;
import br.com.mayara.repositories.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PersonServices {

//    private final AtomicLong counter = new AtomicLong();//Mockar um id
//    private Logger logger = Logger.getLogger(PersonServices.class.getName()); @Sl4j

    @Autowired
    PersonRepository personRepository;

    public List<PersonVO> findAll() {
        log.info("Finding all people");

        return DozerMapper.parseListObjects(personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){
        log.info("Finding one person");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records founf for this ID"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        log.info("Creating one person");
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

//    public PersonVO2 createV2(PersonVO2 personVO2) {
//        log.info("Creatig one person with V2");
//        var entity2 = personMapper.convertToVoEntity(personVO2);
//        var vo = personMapper.convertEntityToVo(personRepository.save(entity2));
//        return vo;
//    }

    public PersonVO update(PersonVO person) {
        log.info("Updating one person");
        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo = DozerMapper.parseObject(personRepository.save(entity), PersonVO.class);

        return vo;
    }

    public void delete(Long id) {
        log.info("Deleting one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        personRepository.delete(entity);
    }


}
