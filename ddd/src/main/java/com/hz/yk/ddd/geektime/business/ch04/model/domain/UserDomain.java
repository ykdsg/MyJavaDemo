package com.hz.yk.ddd.geektime.business.ch04.model.domain;

import lombok.Data;

/**
 * @author wuzheng.yk
 * @date 2023/12/8
 */
@Data
public class UserDomain {

    private long id;

    private MySubscriptionsAssociation mySubscriptions;

}
