package com.hz.yk.ddd.geektime.business.ch04.model;

import com.hz.yk.ddd.geektime.business.ch04.model.entity.Subscription;

import java.util.List;

/**
 * @author wuzheng.yk
 * @date 2021/11/18
 */
public interface SubscriptionRepository {

    /**
     * 分页
     *
     * @param from
     * @param to
     * @return
     */
    List<Subscription> queryPage(long userId, int from, int to);

}
