package com.bitdecay.game.demo;


import com.bitdecay.game.gameobject.MyGameObjectFactory;
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

        gobs.add(MyGameObjectFactory.objectFromConf(this, "DemoControllable", 0, 0));
        gobs.add(MyGameObjectFactory.objectFromConf(this, "DemoStatic", 50, 50));
        gobs.add(MyGameObjectFactory.objectFromConf(this, "DemoStatic", 100, 100));
    }
}
