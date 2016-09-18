package com.bitdecay.game.component;

import com.bitdecay.game.gameobject.MyGameObject;

import java.util.function.Consumer;

public class TimerComponent extends AbstractComponent {
    public boolean done = false;
    public float seconds;
    private Consumer<MyGameObject> func;

    public TimerComponent(MyGameObject obj, float seconds, Consumer<MyGameObject> func) {
        super(obj);
        this.seconds = seconds;
        this.func = func;
    }

    public void execute(){
        func.accept(obj);
    }
}
