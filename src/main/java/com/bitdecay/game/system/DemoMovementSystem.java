package com.bitdecay.game.system;

import com.badlogic.gdx.Input;
import com.bitdecay.game.component.DemoMovementComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.util.InputHelper;

public class DemoMovementSystem extends AbstractForEachGobSystem {
    public DemoMovementSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(PositionComponent.class, DemoMovementComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(PositionComponent.class, position -> {
            if (InputHelper.isKeyPressed(Input.Keys.UP)) position.y += 1;
            else if (InputHelper.isKeyPressed(Input.Keys.DOWN)) position.y -= 1;
            if (InputHelper.isKeyPressed(Input.Keys.LEFT)) position.x -= 1;
            else if (InputHelper.isKeyPressed(Input.Keys.RIGHT)) position.x += 1;
        });
    }

}
