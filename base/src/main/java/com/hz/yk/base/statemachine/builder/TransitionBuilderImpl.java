package com.hz.yk.base.statemachine.builder;

import com.hz.yk.base.statemachine.Action;
import com.hz.yk.base.statemachine.Condition;
import com.hz.yk.base.statemachine.State;
import com.hz.yk.base.statemachine.Transition;
import com.hz.yk.base.statemachine.impl.StateHelper;
import com.hz.yk.base.statemachine.impl.TransitionType;

import java.util.Map;

/**
 * @author wuzheng.yk
 * @date 2021/2/1
 */
public class TransitionBuilderImpl<S, E, C>
        implements ExternalTransitionBuilder<S, E, C>, From<S, E, C>, On<S, E, C>, To<S, E, C> {

    final Map<S, State<S, E, C>> stateMap;

    private State<S, E, C> source;

    protected State<S, E, C> target;

    private Transition<S, E, C> transition;

    final TransitionType transitionType;

    public TransitionBuilderImpl(Map<S, State<S, E, C>> stateMap, TransitionType transitionType) {
        this.stateMap = stateMap;
        this.transitionType = transitionType;
    }

    @Override
    public From<S, E, C> from(S stateId) {
        source = StateHelper.getState(stateMap, stateId);
        return this;
    }

    @Override
    public To<S, E, C> to(S stateId) {
        target = StateHelper.getState(stateMap, stateId);
        return this;
    }

    @Override
    public When<S, E, C> when(Condition<C> condition) {
        transition.setCondition(condition);
        return this;
    }

    @Override
    public On<S, E, C> on(E event) {
        transition = source.addTransition(event, target, transitionType);
        return this;
    }

    @Override
    public void perform(Action<S, E, C> action) {

    }
}
