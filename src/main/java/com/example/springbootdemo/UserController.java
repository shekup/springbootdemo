package com.example.springbootdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

// If we don't put @RestController, then Spring Boot will a valid & meaningful return JSON response

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository repository;

    @RequestMapping("/")
    public String index() {
//        List<User> users = new ArrayList<>();
//        repository.findAll().iterator().forEachRemaining(users::add);
//        for(User user: users){
//         user.add(linkTo(UserController.class).slash(user.getUserId()).withSelfRel());
//         }
        System.out.println("Hello Users");
        return "Hello Users";
    }

    @RequestMapping("/users")
    public List<User> users() {
        List<User> users = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    @RequestMapping(value="/users/{id}", method= RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = (User)repository.findOne(id);
        if(user == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/users/", method= RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        // later add code for validating id otherwise POST and PUT are same for existing users
        repository.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("users/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/users/{id}", method= RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        User userFromRepo = (User)repository.findOne(id);
        if(userFromRepo == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        repository.save(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value="/users/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        User userFromRepo = (User) repository.findOne(id);
        if(userFromRepo == null)
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        repository.delete(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }

}
