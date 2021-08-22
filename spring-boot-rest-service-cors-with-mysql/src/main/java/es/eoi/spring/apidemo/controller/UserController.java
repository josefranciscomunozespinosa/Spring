package es.eoi.spring.apidemo.controller;

import es.eoi.spring.apidemo.model.User;
import es.eoi.spring.apidemo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( "*" )
@RestController
@RequestMapping("user")
public class UserController
{
    private final UserService userService;

    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers( )
    {
        return userService.getAllUsers();
    }

    @GetMapping( "/{id}" )
    public User getUser(@PathVariable String id )
    {
        return userService.getUser( id );
    }

    @PostMapping
    public User newUser( @RequestBody User user ) {
        return userService.save( user );
    }

    @PutMapping
    public User updateUser( @RequestBody User user ) {
        return userService.save( user );
    }

    @DeleteMapping( "/{id}")
        public void deleteUser( @PathVariable String id  ){
        userService.delete( id );
    }

}
