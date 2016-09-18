package com.bitdecay.game.demo;

import com.badlogic.gdx.Input;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.TimerComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.AbstractForEachGobSystem;
import com.bitdecay.game.util.InputHelper;

public class DemoMovementSystem extends AbstractForEachGobSystem {

    private float SPEED = 4;

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
            if (InputHelper.isKeyPressed(Input.Keys.UP)) position.y += SPEED;
            else if (InputHelper.isKeyPressed(Input.Keys.DOWN)) position.y -= SPEED;
            if (InputHelper.isKeyPressed(Input.Keys.LEFT)) position.x -= SPEED;
            else if (InputHelper.isKeyPressed(Input.Keys.RIGHT)) position.x += SPEED;

            if (InputHelper.isKeyJustPressed(Input.Keys.SPACE)){
                gob.addComponent(new TimerComponent(gob, 1, obj -> {
                    obj.removeComponent(TimerComponent.class);
                    System.out.println("YAY TIMER WORKS!!! ");
                }));
            }
        });
    }

}
