package com.bitdecay.game.system;

import com.bitdecay.game.component.TimerComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.room.AbstractRoom;

public class TimerSystem extends AbstractForEachGobSystem {
    public TimerSystem(AbstractRoom room) {
        super(room);
    }

    @Override
    protected boolean validateGob(MyGameObject gob) {
        return gob.hasComponents(TimerComponent.class) && ! gob.getComponent(TimerComponent.class).get().done;
    }

    @Override
    protected void forEach(float delta, MyGameObject gob) {
        gob.forEach(TimerComponent.class, timer -> {
            timer.seconds -= delta;
            if (timer.seconds <= 0){
                timer.done = true;
                timer.execute();
            }
        });
    }

}
