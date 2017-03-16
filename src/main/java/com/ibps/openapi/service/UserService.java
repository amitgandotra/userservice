package com.ibps.openapi.service;

import com.ibps.api.usersvcs.model.GetUsersResponse;
import com.ibps.api.usersvcs.model.User;
import com.ibps.api.usersvcs.model.UserPhone;
import com.ibps.openapi.exception.Errors;
import com.ibps.openapi.exception.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static final Log log = LogFactory.getLog(UserService.class);
    private Map<String,User> userMap= Collections.synchronizedMap(new HashMap<String,User>());

    public UserService(){
        userMap.put("john1",createUser("john1","John Smith"));
        userMap.put("amit1",createUser("amit1","Amit Sharma"));
        userMap.put("ashok1",createUser("ashok1","Ashok Verma"));
    }
   // @Transactional
    public void deleteUser(String userId) {
        log.info("delete user called for " + userId);
        if(userMap.containsKey(userId)){
            userMap.remove(userId);
        }
    }

   // @Transactional
    public User createUser(User user) {
        log.info("createUser user called for " + user!=null?user.getUserId():null);
        if(!userMap.containsKey(user.getUserId())){
            userMap.put(user.getUserId(),user);
            return user;
        }
        throw new ServiceException(Errors.USER_ALREADY_EXISTS);
    }

   // @Transactional
    public User updateUser(User user) {
        log.info("updateUser user called for " +  user!=null?user.getUserId():null);
        if(userMap.containsKey(user.getUserId())){
            userMap.put(user.getUserId(),user);
            return user;
        }
        throw new ServiceException(Errors.USER_NOT_FOUND);
    }

    public User getUser(String userId) {
        log.info("getUser user called for " + userId);
        if(userMap.containsKey(userId)){
            return userMap.get(userId);
        }
        throw new ServiceException(Errors.USER_NOT_FOUND);
    }
    public GetUsersResponse getUsers() {
        log.info("getUsers user called");
        GetUsersResponse response=new GetUsersResponse();
        response.setUsers(new ArrayList(userMap.values()));
        return response;
    }

   private User createUser(String userId,String username){
        User user=new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setEmail(userId + "@ibps.com");
        user.setFirstName(username);
        user.setLastName(username);
        user.setLocale("en_US");
        user.setPhones(new ArrayList<UserPhone>());

        UserPhone p1=new UserPhone();
       p1.setPhoneNo("111-222-3333");
       p1.setPhoneType("H");
        user.getPhones().add(p1);

       UserPhone p2=new UserPhone();
       p2.setPhoneNo("555-222-3333");
       p2.setPhoneType("M");
       user.getPhones().add(p2);
        return user;
   }

}
