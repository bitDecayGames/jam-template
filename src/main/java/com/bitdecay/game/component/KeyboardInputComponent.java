package com.bitdecay.game.component;

import com.badlogic.gdx.Input;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.util.MultiKeyState;
import com.bitdecay.jump.control.PlayerAction;
import com.bitdecay.jump.gdx.input.GDXControls;

/**
 * This component is to add keyboard controls to a physical body in Jump
 */
public class KeyboardInputComponent extends InputComponent {

    private GDXControls keyboard;

    public KeyboardInputComponent(MyGameObject obj) {
        super(obj);
        keyboard = new GDXControls();
        // TODO: this should come from a conf file
        keyboard.set(PlayerAction.JUMP, new MultiKeyState(Input.Keys.UP, Input.Keys.W));
        keyboard.set(PlayerAction.UP, new MultiKeyState(Input.Keys.UP, Input.Keys.W));
        keyboard.set(PlayerAction.DOWN, new MultiKeyState(Input.Keys.DOWN, Input.Keys.S));
        keyboard.set(PlayerAction.LEFT, new MultiKeyState(Input.Keys.LEFT, Input.Keys.A));
        keyboard.set(PlayerAction.RIGHT, new MultiKeyState(Input.Keys.RIGHT, Input.Keys.D));
    }

    @Override
    public boolean isJustPressed(PlayerAction playerAction) {
        return isEnabled() && keyboard.isJustPressed(playerAction);
    }

    @Override
    public boolean isPressed(PlayerAction playerAction) {
        return isEnabled() && keyboard.isPressed(playerAction);
    }
}
