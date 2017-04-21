package com.bitdecay.game.system;

import com.bitdecay.game.component.DespawnableComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.RemoveNowComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

/**
 * This system is in charge of updating the position of an object when the object goes out of the window and must respawn
 */
public class DespawnSystem extends AbstractForEachUpdatableSystem {
    private int minWidth;
    private int maxWidth;
    private int minHeight;
    private int maxHeight;


    public DespawnSystem(AbstractRoom room, int minWidth, int maxWidth, int minHeight, int maxHeight) {
        super(room);
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(DespawnableComponent.class, PositionComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(PositionComponent.class, pos -> {
            if (pos.x < minWidth || pos.x > maxWidth || pos.y < minHeight || pos.y > maxHeight) gob.addComponent(new RemoveNowComponent());
        });
    }

}
