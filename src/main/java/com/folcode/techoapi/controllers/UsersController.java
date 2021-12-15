package com.folcode.techoapi.controllers;

import com.folcode.techoapi.models.entities.dto.SocioDto;
import com.folcode.techoapi.models.entities.dto.UserDto;
import com.folcode.techoapi.models.entities.repositories.UserRepository;
import com.folcode.techoapi.services.SociosService;
import com.folcode.techoapi.services.UsersService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
   private final SociosService sociosService;

    public UsersController(UserRepository userRepository, UsersService usersService, SociosService sociosService) {
        this.userRepository = userRepository;
        this.usersService = usersService;
        this.sociosService = sociosService;
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @RequestMapping("/setclaims/{uid}")
    public ResponseEntity<String> setClaims(@PathVariable("uid") String uid,@RequestBody Map<String, Object> claims) throws FirebaseAuthException {
        return ResponseEntity.ok(UsersService.setClaims(uid,claims));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(
                usersService.findAll());
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @DeleteMapping("/delete/{uid}")
    public ResponseEntity<String> deleteUser(@PathVariable("uid") String uid) {
        try {
            UsersService.deleteUser(uid);
            return ResponseEntity.ok("Se borro usuario");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @RequestMapping("/findbyId/{uid}")
    public ResponseEntity<String> getUserById(@PathVariable("uid") String uid) throws FirebaseAuthException {
        try {

            return ResponseEntity.ok(UsersService.getUserById(uid));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
    @RequestMapping("/authenticate/create/{uid}")
    public ResponseEntity<String> createCustomToken(@PathVariable("uid") String uid) throws FirebaseAuthException {
        return ResponseEntity.ok(UsersService.createCustomToken(uid));
    }
    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})

    @GetMapping("/autenticar/{idToken}")
    public ResponseEntity<String> verifyIdToken(@PathVariable(name="idToken") String idToken) throws FirebaseAuthException {
        return ResponseEntity.ok(UsersService.verifyIdToken(idToken));
    }

//    @CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST})
//    @GetMapping("/{dni}")
//    public ResponseEntity<SocioDto> getSocio(@PathVariable ("dni")String dni) {
//
//        return new ResponseEntity<>(sociosService.getSocio(dni), HttpStatus.OK);
//
//    }



}


