package com.restTemplate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restTemplate.Entity.StudentRest;
import com.restTemplate.dto.StudentDto;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
public class RestControler {

    @Autowired
    private RestTemplate restTemplate;

// give vales from mapping into DTO
    @RequestMapping("/demo/{id}")
    public StudentDto getValues(@PathVariable("id") String id) {
        StringBuilder builder = new StringBuilder("http://localhost:8080/Byid/" + id);
        URI uri = URI.create(builder.toString());
        StudentDto forObject = restTemplate.getForObject(uri, StudentDto.class);


        return forObject;
    }



    // Give All values without DTO
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() throws JsonProcessingException {

//    public void test() throws JsonProcessingException {
//        final Logger lOGGER = LoggerFactory.getLogger(RestControler.class);
//
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/Byid/";
        ResponseEntity<String> response  = restTemplate.getForEntity(fooResourceUrl + "/1", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
//

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("name");
        Assertions.assertNotNull(name.asText());

//        lOGGER.info(String.valueOf(response));

        return response;

    }












    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public StudentRest processRequest(HttpServletRequest request, HttpSession session, @RequestBody StudentDto dto) {

        RestTemplate restTemplate = new RestTemplate();

        StudentRest user = new StudentRest();

        user.setCountry(dto.getCountry());

        user.setName(dto.getName());

        user.setSubject(dto.getSubject());

        restTemplate.postForObject("http://localhost:8080/data", user, StudentRest.class);

//  TILL ABOVE IS WORKING----------------------------------------------------------------------------------------------------------------------------------------

//        StringBuilder builder = new StringBuilder("http://localhost:8080/Byid/2");
//
//        URI uri = URI.create(builder.toString());
//
//        StudentDto updatedUsers = restTemplate.getForObject(uri, StudentDto.class);
//
//        session = request.getSession(false);
//
//        ModelAndView model = new ModelAndView();
//
//        if (session != null) {
//            model.setViewName("welcome");
//            model.addObject("users", updatedUsers);
//
//            model.addObject("user", (StudentRest) session.getAttribute("user"));
////            model.addObject("sessiontracker", sessiontracker.getList());
////
////            model.addObject("sumValidSessions", sessiontracker.getSizeSessionList());
//
//
//            return model;
//
//        }
//
//        return new ModelAndView("login");

        return user;
    }



}



