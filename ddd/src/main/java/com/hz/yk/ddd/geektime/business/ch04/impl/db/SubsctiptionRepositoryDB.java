package com.hz.yk.ddd.geektime.business.ch04.impl.db;

import com.google.common.collect.Lists;
import com.hz.yk.ddd.geektime.business.ch04.model.SubscriptionRepository;
import com.hz.yk.ddd.geektime.business.ch04.model.entity.Subscription;

import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2021/11/18
 */
public class SubsctiptionRepositoryDB implements SubscriptionRepository {

    @Override
    public List<Subscription> queryPage(long userId, int from, int to) {
        return Lists.newArrayList();
    }
}
