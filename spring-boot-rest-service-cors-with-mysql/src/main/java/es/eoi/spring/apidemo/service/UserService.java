package es.eoi.spring.apidemo.service;

import es.eoi.spring.apidemo.model.User;

import java.util.List;

public interface UserService
{

    List<User> getAllUsers();

    User getUser(String id );

    User save( User user );

    void delete( String id );

}
