package com.grow.demo.service;

import com.grow.demo.dao.IUserDao;
import com.grow.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liuxw
 * @since 1.0
 */
@Service
public class UserService extends AbstractBaseService<User>{

    @Autowired
    private IUserDao iUserDao;

    public User getUserById(Integer id){

        return iUserDao.getUser(id,"","");
    }

    public int updatePassword(Integer id,String password){
        return iUserDao.updatePassword(id,password);
    }

}
