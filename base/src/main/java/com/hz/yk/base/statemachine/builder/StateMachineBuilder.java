package com.hz.yk.base.statemachine.builder;

import com.hz.yk.base.statemachine.StateMachine;

/**
 * @author wuzheng.yk
 * @date 2021/2/1
 */
public interface StateMachineBuilder<S, E, C> {

    ExternalTransitionBuilder<S, E, C> externalTransition();

    StateMachine<S, E, C> build(String machineId);
}
