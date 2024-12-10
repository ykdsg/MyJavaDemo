package com.hz.yk.base.statemachine;

import com.hz.yk.base.statemachine.impl.TransitionType;

import java.util.Collection;
import java.util.Optional;

/**
 * @author wuzheng.yk
 * @date 2021/1/31
 */
public interface State<S, E, C> extends Visitable {

    /**
     * 获取状态标识
     *
     * @return
     */
    S getId();

    /**
     * 添加状态转换
     *
     * @param event
     * @param target
     * @param transitionType
     * @return
     */
    Transition<S, E, C> addTransition(E event, State<S, E, C> target, TransitionType transitionType);

    Optional<Transition<S, E, C>> getTransition(E event);

    Collection<Transition<S, E, C>> getAllTransitions();

}
