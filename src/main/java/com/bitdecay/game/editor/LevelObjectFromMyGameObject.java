package com.bitdecay.game.editor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bitdecay.game.component.DrawableComponent;
import com.bitdecay.game.component.NameComponent;
import com.bitdecay.game.component.SizeComponent;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.annotation.CantInspect;
import com.bitdecay.jump.gdx.level.RenderableLevelObject;
import com.bitdecay.jump.geom.BitRectangle;

public class LevelObjectFromMyGameObject extends RenderableLevelObject {

    @CantInspect
    final public String name;

    public MyGameObject obj;

    public LevelObjectFromMyGameObject(MyGameObject obj){
        super();
        this.obj = obj;

        this.name = obj.getComponent(NameComponent.class).map(Object::toString).orElseGet(null);
        rect = new BitRectangle(0, 0, 16, 16); // default values in case there is no size
        obj.getComponent(SizeComponent.class).ifPresent(size -> {
            rect.width = size.w;
            rect.height = size.h;
        });
    }

    @Override
    public TextureRegion texture() {
        return obj.getComponent(DrawableComponent.class).map(DrawableComponent::image).orElseGet(null);
    }

    @Override
    public BitBody buildBody() {
        return null; // I guess this stays null?
    }

    @Override
    public String name() {
        return name;
    }
}
