//package br.com.mayara.mapper.custom;
//
//import br.com.mayara.data.vo.v2.PersonVO2;
//import br.com.mayara.model.Person;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class PersonMapper {
//
//    public PersonVO2 convertEntityToVo(Person person){
//        PersonVO2 personVO2 = new PersonVO2();
//        personVO2.setId(person.getId());
//        personVO2.setFirstName(person.getFirstName());
//        personVO2.setLastName(person.getLastName());
//        personVO2.setAddress(person.getAddress());
//        personVO2.setGender(person.getGender());
//        personVO2.setBirthDay(new Date());
//        return personVO2;
//    }
//
//    public Person convertToVoEntity(PersonVO2 personVO2){
//        Person entity = new Person();
//        entity.setId(personVO2.getId());
//        entity.setFirstName(personVO2.getFirstName());
//        entity.setLastName(personVO2.getLastName());
//        entity.setAddress(personVO2.getAddress());
//        entity.setGender(personVO2.getGender());
////        entity.setBirthDay(new Date());
//        return entity;
//    }
//}
