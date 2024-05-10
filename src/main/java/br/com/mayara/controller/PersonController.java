package br.com.mayara.controller;

import br.com.mayara.data.vo.PersonVO;
import br.com.mayara.services.PersonServices;
import br.com.mayara.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    @Autowired
    public PersonServices personServices;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
    @Operation(summary = "Finds a Person", description = "Finds a Person",
    tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content =
                    @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public PersonVO findById(@PathVariable(value = "id") Long id){
        return personServices.findById(id);
    }
    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML})
    @Operation(summary = "Finds All People", description = "Finds All People",
            tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonVO.class)))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public List<PersonVO> findAll(){
        return personServices.findAll();
    }
    @PostMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YAML},
                    consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
                            MediaType.APPLICATION_YAML})//APPLICATION_JSON_VALUE serve para fazer o swagger
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content =
            @Content(schema = @Schema(implementation = PersonVO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public PersonVO create(@RequestBody PersonVO person){
        return personServices.create(person);
    }

//    @PostMapping(value = "/v2", produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE)//APPLICATION_JSON_VALUE serve para fazer o swagger
//    public PersonVO2 createV2(@RequestBody PersonVO2 personVO2){
//        return personServices.createV2(personVO2);
//    }
   @PutMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML},
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
   @Operation(summary = "Updates a Person",
           description = "Updates a Person by passing in a JSON, XML or YML representation of the person",
           tags = {"People"}, responses = {
           @ApiResponse(description = "Updated", responseCode = "200", content =
           @Content(schema = @Schema(implementation = PersonVO.class))),
           @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
           @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
           @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
           @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
   })
    public PersonVO update(@RequestBody PersonVO person){

        return personServices.create(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person",
            description = "Deletes a Person by passing in a JSON, XML or YML representation of the person",
            tags = {"People"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
    })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        personServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}
