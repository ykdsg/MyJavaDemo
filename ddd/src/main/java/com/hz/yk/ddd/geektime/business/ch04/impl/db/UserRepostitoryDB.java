package com.hz.yk.ddd.geektime.business.ch04.impl.db;

import com.hz.yk.ddd.geektime.business.ch04.model.UserRepostitory;
import com.hz.yk.ddd.geektime.business.ch04.model.entity.User;

/**
 * @author wuzheng.yk
 * @date 2021/11/17
 */
public class UserRepostitoryDB implements UserRepostitory {

    @Override
    public User findById(long id) {
        User user = new User();
        return user;
    }

}
