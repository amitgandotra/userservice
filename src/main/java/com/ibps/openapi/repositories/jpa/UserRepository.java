package com.ibps.openapi.repositories.jpa;

/**
 * Created by amit on 4/26/17.
 */
import java.util.List;

import com.ibps.openapi.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, String> {

    List<User> findByLastName(@Param("name") String name);


}
