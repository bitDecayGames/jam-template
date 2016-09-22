package com.bitdecay.game.demo;


import com.badlogic.gdx.graphics.Color;
import com.bitdecay.game.component.*;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.system.*;
import com.bitdecay.jump.level.Level;

/**
 * The demo room is just a super simple example of how to add systems and game objects to a room.
 */
public class DemoRoom extends AbstractRoom {

    public DemoRoom(GameScreen gameScreen, Level level) {
        super(gameScreen, level);

        // systems must be added before game objects
        new PhysicsSystem(this);
        new TimerSystem(this);
        new CameraUpdateSystem(this);
        new RespawnSystem(this, Integer.MIN_VALUE, Integer.MAX_VALUE, -1000, Integer.MAX_VALUE);
        new ShapeDrawSystem(this);
        new DrawSystem(this);

        // game objects should be added last
        gobs.add(demoControllableObject(0, 0, 13, 18));
        gobs.add(demoObject(100, 100, 20));
        gobs.add(demoObject(200, 200, 20));
        gobs.add(demoObject(300, 300, 20));
    }

    public MyGameObject demoControllableObject(float x, float y, float w, float h){
        MyGameObject obj = demoObject(x, y, w);
        obj.addComponent(new SizeComponent(obj, w, h));
        PhysicsComponent phy = new PhysicsComponent(obj, w, h, 300, 1, 0, 10000, 1000, 700, true);
        world.addBody(phy.body());
        obj.addComponent(phy);
        obj.addComponent(new KeyboardInputComponent(obj, phy.body()));
        obj.addComponent(new RespawnableComponent(obj));
        return obj;
    }

    public static MyGameObject demoObject(float x, float y, float r){
        MyGameObject obj = new MyGameObject();

        obj.addComponent(new PositionComponent(obj, x, y));
        obj.addComponent(new CameraFollowComponent(obj));
        obj.addComponent(new DebugCircleComponent(obj, Color.ROYAL, r));

        return obj;
    }
}
