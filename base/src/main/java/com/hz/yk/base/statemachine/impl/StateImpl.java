package com.hz.yk.base.statemachine.impl;

import com.hz.yk.base.statemachine.State;
import com.hz.yk.base.statemachine.Transition;
import com.hz.yk.base.statemachine.Visitor;

import java.util.Collection;
import java.util.Optional;

/**
 * @author wuzheng.yk
 * @date 2021/1/31
 */
public class StateImpl<S, E, C> implements State<S, E, C> {

    protected final S stateId;
    private final EventTransitions<S, E, C> eventTransitions = new EventTransitions<>();

    public StateImpl(S stateId) {
        this.stateId = stateId;
    }

    @Override
    public S getId() {
        return stateId;
    }

    @Override
    public Transition<S, E, C> addTransition(E event, State<S, E, C> target, TransitionType transitionType) {
        Transition<S, E, C> newTransition = new TransitionImpl<S, E, C>();
        newTransition.setSource(this);
        newTransition.setTarget(target);
        newTransition.setEvent(event);
        newTransition.setType(transitionType);

        eventTransitions.put(event, newTransition);
        return newTransition;
    }

    @Override
    public Optional<Transition<S, E, C>> getTransition(E event) {
        return Optional.empty();
    }

    @Override
    public Collection<Transition<S, E, C>> getAllTransitions() {
        return eventTransitions.allTransitions();
    }

    @Override
    public String accept(Visitor visitor) {
        String entry = visitor.visitOnEntry(this);
        String exit = visitor.visitOnExit(this);
        return entry + exit;
    }

}