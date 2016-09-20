package com.bitdecay.game.demo;

import com.bitdecay.game.component.AbstractComponent;
import com.bitdecay.game.gameobject.MyGameObject;

/**
 * If a component doesn't have any state, then it is really just a flag.  It needs to be here though because otherwise you couldn't tell which game object you were trying to control.
 */
public class DemoMovementComponent extends AbstractComponent {
    public DemoMovementComponent(MyGameObject obj) {
        super(obj);
    }
}
