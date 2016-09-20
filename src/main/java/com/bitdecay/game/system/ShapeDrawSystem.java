package com.bitdecay.game.system;

import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;
import com.bitdecay.game.trait.IShapeDraw;

/**
 * This system is in charge of providing a position to the IShapeDrawComponents so that they are able to correctly draw themselves.
 */
public class ShapeDrawSystem extends AbstractSystem {
    public ShapeDrawSystem(AbstractRoom room) {
        super(room);
        room.shapeRenderer.setAutoShapeType(true);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(IShapeDraw.class, PositionComponent.class);
    }

    @Override
    public void process(float delta) {
        room.shapeRenderer.setProjectionMatrix(room.camera.combined);
        room.shapeRenderer.begin();
        gobs.forEach(gob ->
                gob.forEach(IShapeDraw.class, drawable ->
                        gob.forEach(PositionComponent.class, positionComponent ->
                                drawable.draw(room.shapeRenderer, positionComponent.toVector2()))));
        room.shapeRenderer.end();
    }
}
