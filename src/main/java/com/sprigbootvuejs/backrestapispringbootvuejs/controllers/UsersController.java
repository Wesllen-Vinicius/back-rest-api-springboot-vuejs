package com.sprigbootvuejs.backrestapispringbootvuejs.controllers;
import com.sprigbootvuejs.backrestapispringbootvuejs.models.User;
import com.sprigbootvuejs.backrestapispringbootvuejs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/get")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> Get() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> GetById(@PathVariable(value = "id") long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User Post(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "//user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> Put(@PathVariable(value = "id") long id, @Valid @RequestBody User newUser) {
        Optional<User> oldUser = userRepository.findById(id);
        if (oldUser.isPresent()){
            User user = oldUser.get();
            user.setUsername(newUser.getUsername());
            userRepository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}


