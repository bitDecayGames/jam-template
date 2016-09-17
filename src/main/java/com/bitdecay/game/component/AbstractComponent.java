package com.bitdecay.game.component;

import com.bitdecay.game.gameobject.MyGameObject;

public abstract class AbstractComponent {
    protected final MyGameObject obj;
    public AbstractComponent(MyGameObject obj){
        this.obj = obj;
    }
}
