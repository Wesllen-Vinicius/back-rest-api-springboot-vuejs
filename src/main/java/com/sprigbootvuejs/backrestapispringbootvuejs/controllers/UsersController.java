package com.sprigbootvuejs.backrestapispringbootvuejs.controllers;
import com.sprigbootvuejs.backrestapispringbootvuejs.models.User;
import com.sprigbootvuejs.backrestapispringbootvuejs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {
    UserRepository _userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> Get() {
        return _userRepository.findAll();
    }

    @RequestMapping(value = "/user/{id]", method = RequestMethod.GET)
    public ResponseEntity<User> GetById(@PathVariable(value = "id") long id) {
        Optional<User> user = _userRepository.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}


