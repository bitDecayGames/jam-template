package com.bitdecay.game.system;

import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.RespawnableComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

/**
 * This system is in charge of updating the position of an object when the object goes out of the window and must respawn
 */
public class RespawnSystem extends AbstractForEachUpdatableSystem {
    private int minWidth;
    private int maxWidth;
    private int minHeight;
    private int maxHeight;


    public RespawnSystem(AbstractRoom room, int minWidth, int maxWidth, int minHeight, int maxHeight) {
        super(room);
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(RespawnableComponent.class, PositionComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(RespawnableComponent.class, res ->
            gob.forEach(PositionComponent.class, pos -> {
                if (pos.x < minWidth || pos.x > maxWidth || pos.y < minHeight || pos.y > maxHeight) {
                    Vector2 resPos = res.toVector2();
                    pos.x = resPos.x;
                    pos.y = resPos.y;
                }
            })
        );
    }

}
