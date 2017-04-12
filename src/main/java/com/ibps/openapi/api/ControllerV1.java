package com.ibps.openapi.api;


import com.ibps.api.usersvcs.api.V1Api;
import com.ibps.api.usersvcs.model.CreateUserRequest;
import com.ibps.api.usersvcs.model.GetUsersResponse;
import com.ibps.api.usersvcs.model.UpdateUserRequest;
import com.ibps.api.usersvcs.model.User;
import com.ibps.openapi.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ControllerV1 implements V1Api {
    private static final Log log = LogFactory.getLog(ControllerV1.class);

    @Autowired private UserService userService;

    @Override
    @RequestMapping(value = "/v1/users", method = RequestMethod.POST)
    public User createUser(@Valid @RequestBody CreateUserRequest request) {return userService.createUser(request.getUser());}

    @Override
    @RequestMapping(value = "/v1/users/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("userId") @Valid String userId) {userService.deleteUser(userId);}

    @Override
    @RequestMapping(value = "/v1/users/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") @Valid String userId) {return userService.getUser(userId);}

    @Override
    @RequestMapping(value = "/v1/users", method = RequestMethod.PUT)
    public User updateUser(@RequestBody @Valid UpdateUserRequest request) { return userService.updateUser(request.getUser());}

    @Override
    @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
    public GetUsersResponse getUsers() {return userService.getUsers();}
}
