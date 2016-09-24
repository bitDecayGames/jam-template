package com.bitdecay.game.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.game.MyGame;
import com.bitdecay.game.gameobject.MyGameObject;

/**
 * This component is for drawing an icon in the editor
 */
public class IconComponent extends AbstractComponent {

    private TextureRegion icon;

    public IconComponent(MyGameObject obj, String imagePath) {
        super(obj);
        icon = MyGame.ATLAS.findRegion(imagePath);
    }

    public TextureRegion icon(){ return icon; }
}
