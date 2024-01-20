package com.hz.yk.ddd.geektime.business.ch04.service;

import com.hz.yk.ddd.geektime.business.ch04.impl.db.SubsctiptionRepositoryDB;
import com.hz.yk.ddd.geektime.business.ch04.impl.db.UserRepostitoryDB;
import com.hz.yk.ddd.geektime.business.ch04.model.SubscriptionRepository;
import com.hz.yk.ddd.geektime.business.ch04.model.UserRepostitory;
import com.hz.yk.ddd.geektime.business.ch04.model.convert.UserConvert;
import com.hz.yk.ddd.geektime.business.ch04.model.domain.MySubscriptionsAssociation;
import com.hz.yk.ddd.geektime.business.ch04.model.domain.UserDomain;
import com.hz.yk.ddd.geektime.business.ch04.model.entity.User;

/**
 * @author wuzheng.yk
 * @date 2023/12/8
 */
public class UserDomainService {

    //框架注入
    private UserRepostitory userRepostitory = new UserRepostitoryDB();
    //框架注入
    private SubscriptionRepository subsctiptionRepositoryDB = new SubsctiptionRepositoryDB();

    public UserDomain getUserById(long id) {
        final User user = userRepostitory.findById(id);
        final UserDomain userDomain = UserConvert.convert(user);
        userDomain.setMySubscriptions(new MySubscriptionsAssociation(user, subsctiptionRepositoryDB));
        return userDomain;
    }

}
