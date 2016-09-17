package com.bitdecay.game.room;


import com.bitdecay.game.gameobject.MyGameObjectFactory;
import com.bitdecay.game.screen.GameScreen;
import com.bitdecay.game.system.DemoMovementSystem;
import com.bitdecay.game.system.ShapeDrawSystem;
import com.bitdecay.jump.level.Level;

public class DemoRoom extends AbstractRoom {

    public DemoRoom(GameScreen gameScreen, Level level) {
        super(gameScreen, level);

        // systems must be added before game objects
        new ShapeDrawSystem(this);
        new DemoMovementSystem(this);

        // game objects should be added last
        gobs.add(MyGameObjectFactory.demoControllableObject(0, 0));
        gobs.add(MyGameObjectFactory.demoControllableObject(100, 100));
        gobs.add(MyGameObjectFactory.demoControllableObject(200, 100));
    }
}
