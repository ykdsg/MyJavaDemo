package com.hz.yk.ddd.geektime.business.ch04.model;

import com.hz.yk.ddd.geektime.business.ch04.model.entity.User;

/**
 * @author wuzheng.yk
 * @date 2021/11/17
 */
public interface UserRepostitory {

    User findById(long id);

}
