package com.bitdecay.game.demo;


import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.system.ShapeDrawSystem;
import com.bitdecay.game.system.TimerSystem;
import com.bitdecay.jump.level.Level;

public class DemoRoom extends AbstractRoom {

    public DemoRoom(GameScreen gameScreen, Level level) {
        super(gameScreen, level);

        // systems must be added before game objects
        new ShapeDrawSystem(this);
        new DemoMovementSystem(this);
        new TimerSystem(this);

        // game objects should be added last
        gobs.add(MyGameObjectFactory.demoControllableObject(200, 100));
    }
}
