package com.bitdecay.game.component;

import com.bitdecay.game.gameobject.MyGameObject;

public class SizeComponent extends AbstractComponent {
    public float w = 0;
    public float h = 0;

    public SizeComponent(MyGameObject obj){super(obj);}
    public SizeComponent(MyGameObject obj, float width, float height){
        super(obj);
        this.w = width;
        this.h = height;
    }

    public void set(float width, float height) {
        w = width;
        h = height;
    }
}
