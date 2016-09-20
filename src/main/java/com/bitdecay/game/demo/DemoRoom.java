package com.bitdecay.game.demo;


import com.badlogic.gdx.graphics.Color;
import com.bitdecay.game.component.CameraFollowComponent;
import com.bitdecay.game.component.DebugCircleComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.system.CameraUpdateSystem;
import com.bitdecay.game.system.DrawSystem;
import com.bitdecay.game.system.ShapeDrawSystem;
import com.bitdecay.game.system.TimerSystem;
import com.bitdecay.jump.level.Level;

/**
 * The demo room is just a super simple example of how to add systems and game objects to a room.
 */
public class DemoRoom extends AbstractRoom {

    public DemoRoom(GameScreen gameScreen, Level level) {
        super(gameScreen, level);

        // systems must be added before game objects
        new ShapeDrawSystem(this);
        new DemoMovementSystem(this);
        new TimerSystem(this);
        new CameraUpdateSystem(this);
        new DrawSystem(this);

        // game objects should be added last
        gobs.add(demoControllableObject(0, 0));
        gobs.add(demoObject(100, 100));
        gobs.add(demoObject(200, 200));
        gobs.add(demoObject(300, 300));
    }

    public static MyGameObject demoControllableObject(float x, float y){
        MyGameObject obj = demoObject(x, y);
        obj.addComponent(new DemoMovementComponent(obj));
        return obj;
    }

    public static MyGameObject demoObject(float x, float y){
        MyGameObject obj = new MyGameObject();

        obj.addComponent(new PositionComponent(obj, x, y));
        obj.addComponent(new CameraFollowComponent(obj));
        obj.addComponent(new DebugCircleComponent(obj, Color.ROYAL, 20));

        return obj;
    }
}
