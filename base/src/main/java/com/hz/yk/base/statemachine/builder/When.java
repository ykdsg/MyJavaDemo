package com.hz.yk.base.statemachine.builder;

import com.hz.yk.base.statemachine.Action;

/**
 * When
 *
 * @author Frank Zhang
 * @date 2020-02-07 9:33 PM
 */
public interface When<S, E, C> {

    /**
     * Define action to be performed during transition
     *
     * @param action performed action
     */
    void perform(Action<S, E, C> action);
}
