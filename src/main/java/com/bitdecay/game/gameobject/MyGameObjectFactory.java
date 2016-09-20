package com.bitdecay.game.gameobject;

import com.bitdecay.game.component.PositionComponent;
import com.bitdecay.game.component.SizeComponent;
import com.bitdecay.game.component.TileComponent;
import com.bitdecay.game.util.Tilesets;
import com.bitdecay.jump.level.TileObject;

/**
 * Currently unused, but the idea here is to provide a single place for you to add your game objects.  You know that the "Player" game object will have a PositionComponent, a SizeComponent, and a CameraFollowComponenet.  So in a static method (maybe called buildPlayer) you want to create a generic MyGameObject and populate it with the correct components.
 */
public final class MyGameObjectFactory {
    private MyGameObjectFactory(){}

    public static MyGameObject tile(TileObject tile){
        Tilesets.Tileset tileset = Tilesets.tilesetForMaterial(tile.material);
        MyGameObject t = new MyGameObject();
        t.addComponent(new PositionComponent(t, tile.rect.xy.x, tile.rect.xy.y));
        t.addComponent(new SizeComponent(t, tile.rect.width, tile.rect.height));
        t.addComponent(new TileComponent(t, tileset.regions().get(tile.renderNValue)));
        return t;
    }
}
