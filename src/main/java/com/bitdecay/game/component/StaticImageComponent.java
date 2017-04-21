package com.bitdecay.game.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.game.MyGame;
import com.typesafe.config.Config;

/**
 * This component extends the drawable component and it only draws a single static image.
 */
public class StaticImageComponent extends DrawableComponent {

    private TextureRegion image;

    public StaticImageComponent(Config conf) {
        image = MyGame.ATLAS.findRegion(conf.getString("path"));
    }

    @Override
    public TextureRegion image() {
        return image;
    }
}
