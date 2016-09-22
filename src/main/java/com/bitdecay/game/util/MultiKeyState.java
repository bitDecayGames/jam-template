package com.bitdecay.game.util;

import com.bitdecay.jump.gdx.input.KeyState;

public class MultiKeyState extends KeyState {

    private int[] keys;

    public MultiKeyState(int... keys) {
        super(0);
        this.keys = keys;
    }

    @Override
    public boolean isJustPressed() {
        return InputHelper.isKeyJustPressed(keys);
    }

    @Override
    public boolean isPressed() {
        return InputHelper.isKeyPressed(keys);
    }
}
