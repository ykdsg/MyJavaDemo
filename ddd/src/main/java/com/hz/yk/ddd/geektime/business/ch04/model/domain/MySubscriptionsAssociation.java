package com.hz.yk.ddd.geektime.business.ch04.model.domain;

import com.hz.yk.ddd.geektime.business.ch04.model.SubscriptionRepository;
import com.hz.yk.ddd.geektime.business.ch04.model.entity.Subscription;
import com.hz.yk.ddd.geektime.business.ch04.model.entity.User;

import java.util.List;

/**
 * 关联对象：代表用户已经购买的专栏<br>
 * 核心是可以持有持久层对象<br>
 * 想到的一个问题，目前这样设计可以应对单个模型的使用，但是批量模型的查询场量怎么解决<br>
 * 比如要对一系列用户进行订阅的延期，或者查询用户列表并显示每个用户的订阅数
 *
 * @author wuzheng.yk
 * @date 2023/12/7
 */
public class MySubscriptionsAssociation {

    private SubscriptionRepository subscriptionRepository;

    private User user;

    public MySubscriptionsAssociation(User user, SubscriptionRepository subscriptionRepository) {
        this.user = user;
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> subList(int from, int to) {
        final List<Subscription> subscriptions = subscriptionRepository.queryPage(user.getId(), from, to);
        return subscriptions;
    }

    public long getTotalSubscriptionFee() {
        return 0;
    }

    public int count() {
        return 0;
    }
}
