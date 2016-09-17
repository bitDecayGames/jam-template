package com.bitdecay.game.gameobject;

import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SizeComponent;

public class MyGameObjectFactory {
    private MyGameObjectFactory(){}

    public MyGameObject player(){
        MyGameObject obj = new MyGameObject();
        obj.addComponent(new PositionComponent(obj));
        obj.addComponent(new SizeComponent(obj));
        return obj;
    }
}
