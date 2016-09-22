package com.bitdecay.game.system;

import com.bitdecay.game.component.PhysicsComponent;
import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SizeComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.system.abstracted.AbstractForEachUpdatableSystem;

/**
 * This system is in charge of updating the position and size component with data from the physics component
 */
public class PhysicsSystem extends AbstractForEachUpdatableSystem {
    public PhysicsSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(PhysicsComponent.class);
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(PhysicsComponent.class, phy -> {
            gob.forEach(PositionComponent.class, pos -> {
                pos.x = phy.body().aabb.xy.x;
                pos.y = phy.body().aabb.xy.y;
            });
            gob.forEach(SizeComponent.class, size -> {
                size.w = phy.body().aabb.width;
                size.h = phy.body().aabb.height;
            });
        });
    }

}
