package com.bitdecay.game.gameobject;

import com.badlogic.gdx.graphics.Color;
import com.bitdecay.game.component.DebugCircleComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.demo.DemoMovementComponent;

public class MyGameObjectFactory {
    private MyGameObjectFactory(){}

    public static MyGameObject demoControllableObject(float x, float y){
        MyGameObject obj = new MyGameObject();
        obj.addComponent(new PositionComponent(obj, x, y));
        obj.addComponent(new DemoMovementComponent(obj));
        obj.addComponent(new DebugCircleComponent(obj, Color.ROYAL, 10));
        return obj;
    }
}
