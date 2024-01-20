package com.hz.yk.ddd.geektime.business.ch04.model.convert;

import com.hz.yk.ddd.geektime.business.ch04.model.domain.UserDomain;
import com.hz.yk.ddd.geektime.business.ch04.model.entity.User;

/**
 * @author wuzheng.yk
 * @date 2023/12/8
 */
public class UserConvert {

    public static UserDomain convert(User user) {
        return new UserDomain();
    }
}
