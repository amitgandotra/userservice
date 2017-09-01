package com.ibps.openapi;

import com.ibps.openapi.dao.UserDao;
import com.ibps.openapi.domain.User;
import com.ibps.openapi.repositories.jpa.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserDaoTest extends BaseTest{
   // @Autowired UserRepository userRepository;
    @Autowired UserDao userDao;

    @Test
    public void testHasUser() throws Exception{
        boolean userExists=userDao.hasUser("abc");
        userExists=userDao.hasUser("abc");
        userExists=userDao.hasUser("abc");
        System.out.println(userExists);

    }
    @Test
    public void testFindUserByName() throws Exception{
        User user=userDao.findUserByName("abc");
        user=userDao.findUserByName("abc");
        user=userDao.findUserByName("abc");
        System.out.println(user);

    }
}
