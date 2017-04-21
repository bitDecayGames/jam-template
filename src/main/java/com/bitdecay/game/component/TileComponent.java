package com.bitdecay.game.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This calls out that an object is a tile
 */
public class TileComponent extends DrawableComponent {
    private TextureRegion image;
    public TileComponent(TextureRegion image) {
        this.image = image;
    }

    @Override
    public TextureRegion image() {
        return image;
    }
}
