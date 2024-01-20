package com.hz.yk.ddd.geektime.business.ch04;

import com.hz.yk.ddd.geektime.business.ch04.model.domain.UserDomain;
import com.hz.yk.ddd.geektime.business.ch04.model.entity.Subscription;
import com.hz.yk.ddd.geektime.business.ch04.service.UserDomainService;

import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2021/11/17
 */
public class Main {

    public static void main(String[] urgs) {
        UserDomainService userFactory = new UserDomainService();
        final UserDomain user = userFactory.getUserById(11);
        final List<Subscription> subscriptions = user.getMySubscriptions().subList(0, 10);
        System.out.println(subscriptions);
    }

}
