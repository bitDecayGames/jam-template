package com.bitdecay.game.component;

import com.badlogic.gdx.Input;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.util.MultiKeyState;
import com.bitdecay.jump.control.PlayerAction;
import com.bitdecay.jump.gdx.input.GDXControls;

import java.util.stream.Collectors;

/**
 * This component is to add keyboard controls to a physical body in Jump
 */
public class KeyboardInputComponent extends InputComponent {

    private GDXControls keyboard;

    public KeyboardInputComponent(MyGameObject obj) {
        super(obj);
        keyboard = new GDXControls();
        setControls(PlayerAction.JUMP);
        setControls(PlayerAction.UP);
        setControls(PlayerAction.DOWN);
        setControls(PlayerAction.LEFT);
        setControls(PlayerAction.RIGHT);
    }

    private void setControls(PlayerAction action){
        keyboard.set(action, new MultiKeyState(Launcher.conf.getConfig("controls").getConfig("keyboard").getStringList(action.name().toLowerCase()).stream().map(Input.Keys::valueOf).filter(i -> i >= 0).collect(Collectors.toList())));
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
