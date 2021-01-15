package com.grow.demo.dao;

import com.grow.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author liuxw
 * @date 2020/7/6
 * @since 1.0
 */
@Mapper
@Repository
public interface IUserDao extends BaseDao<User>{

    /**
     * 根据手机号或者邮箱获取用户信息
     * @param id
     * @param phone
     * @param email
     * @return
     */
    User getUser(@Param("id") Integer id,@Param("phone") String phone, @Param("email") String email);

    /**
     * 修改密码
     * @param id
     * @param password
     * @return
     */
    int updatePassword(@Param("id") Integer id,@Param("password") String password);

    /**
     * 更换头像
     * @param id
     * @param avatar
     * @return
     */
    int updateAvatar(@Param("id")Integer id, @Param("password")String avatar);


}
