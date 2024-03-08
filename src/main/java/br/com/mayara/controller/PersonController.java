package br.com.mayara.controller;

import br.com.mayara.data.vo.v1.PersonVO;
import br.com.mayara.data.vo.v2.PersonVO2;
import br.com.mayara.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    public PersonServices personServices;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)//APPLICATION_JSON_VALUE serve para fazer o swagger
    public PersonVO create(@RequestBody PersonVO person){
        return personServices.create(person);
    }

//    @PostMapping(value = "/v2", produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE)//APPLICATION_JSON_VALUE serve para fazer o swagger
//    public PersonVO2 createV2(@RequestBody PersonVO2 personVO2){
//        return personServices.createV2(personVO2);
//    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)//
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return personServices.findById(id);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll(){
        return personServices.findAll();
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update(@RequestBody PersonVO person){

        return personServices.create(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
