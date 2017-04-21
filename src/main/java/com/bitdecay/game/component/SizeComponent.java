package com.bitdecay.game.component;

import com.typesafe.config.Config;

/**
 * Currently this is not used for anything.
 */
public class SizeComponent extends AbstractComponent {
    public float w = 0;
    public float h = 0;

    public SizeComponent(Config conf) {
        this((float) conf.getDouble("w"), (float) conf.getDouble("h"));
    }

    public SizeComponent(float width, float height){
        this.w = width;
        this.h = height;
    }

    public void set(float width, float height) {
        w = width;
        h = height;
    }
}
