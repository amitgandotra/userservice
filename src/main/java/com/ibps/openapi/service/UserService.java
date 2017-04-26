package com.ibps.openapi.service;

import com.ibps.api.usersvcs.model.GetUsersResponse;
import com.ibps.api.usersvcs.model.User;
import com.ibps.api.usersvcs.model.UserPhone;
import com.ibps.openapi.exception.Errors;
import com.ibps.openapi.exception.ServiceException;
import com.ibps.openapi.repositories.jpa.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    private static final Log log = LogFactory.getLog(UserService.class);

    @Transactional
    public void deleteUser(String userId) {
        log.info("delete user called for " + userId);
        userRepository.delete(userId);
    }

    @Transactional
    public User createUser(User user) {
        log.info("createUser user called for " + user != null ? user.getUserId() : null);
        com.ibps.openapi.domain.User mUser = getDomainUserFromUser(user);
        if (userRepository.exists(user.getUserId())) {
            throw new ServiceException(Errors.USER_ALREADY_EXISTS);
        }
        mUser = userRepository.save(mUser);
        return getUserFromDomainUser(mUser);
    }

    @Transactional
    public User updateUser(User user) {
        log.info("updateUser user called for " + user != null ? user.getUserId() : null);
        if (!userRepository.exists(user.getUserId())) {
            throw new ServiceException(Errors.USER_NOT_FOUND);
        }
        com.ibps.openapi.domain.User mUser = getDomainUserFromUser(user);
        mUser = userRepository.save(mUser);
        return getUserFromDomainUser(mUser);

    }

    public User getUser(String userId) {
        log.info("getUser user called for " + userId);
        if (!userRepository.exists(userId)) {
            throw new ServiceException(Errors.USER_NOT_FOUND);
        }
        com.ibps.openapi.domain.User mUser = userRepository.findOne(userId);
        return getUserFromDomainUser(mUser);
    }

    public GetUsersResponse getUsers() {
        log.info("getUsers user called");
        Iterable<com.ibps.openapi.domain.User> mUsers = userRepository.findAll();
        GetUsersResponse response = new GetUsersResponse();
        for (com.ibps.openapi.domain.User mUser : mUsers) {
            response.getUsers().add(getUserFromDomainUser(mUser));
        }
        return response;
    }

    //   private User createUser(String userId,String username){
//        User user=new User();
//        user.setUserId(userId);
//        user.setUsername(username);
//        user.setEmail(userId + "@ibps.com");
//        user.setFirstName(username);
//        user.setLastName(username);
//        user.setLocale("en_US");
//        user.setPhones(new ArrayList<UserPhone>());
//
//        UserPhone p1=new UserPhone();
//       p1.setPhoneNo("111-222-3333");
//       p1.setPhoneType("H");
//        user.getPhones().add(p1);
//
//       UserPhone p2=new UserPhone();
//       p2.setPhoneNo("555-222-3333");
//       p2.setPhoneType("M");
//       user.getPhones().add(p2);
//        return user;
//   }
    private com.ibps.openapi.domain.User getDomainUserFromUser(User user) {
        com.ibps.openapi.domain.User mUser = new com.ibps.openapi.domain.User();
        mUser.setId(user.getUserId());
        mUser.setFirstName(user.getFirstName());
        mUser.setLastName(user.getLastName());
        mUser.setLocale(user.getLocale());
        mUser.setEmail(user.getEmail());
        mUser.setUsername(user.getUsername());
        return mUser;
    }

    private User getUserFromDomainUser(com.ibps.openapi.domain.User mUser) {
        User user = new User();
        user.setUsername(mUser.getUsername());
        user.setLocale(mUser.getLocale());
        user.setEmail(mUser.getEmail());
        user.setFirstName(mUser.getFirstName());
        user.setLastName(mUser.getLastName());
        user.setUserId(mUser.getId());
        return user;

    }
}
