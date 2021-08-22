package es.eoi.spring.apidemo.repository;

import es.eoi.spring.apidemo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>
{

}
