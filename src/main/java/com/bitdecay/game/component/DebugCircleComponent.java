package com.bitdecay.game.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.game.trait.IShapeDraw;
import com.typesafe.config.Config;

/**
 * This component, when added to a game object, will draw a circle at the current position of the game object.
 */
public class DebugCircleComponent extends AbstractComponent implements IShapeDraw {
    public final Color color;
    public final float radius;

    public DebugCircleComponent(MyGameObject obj, Color color, float radius){
        super(obj);
        this.color = color.cpy();
        this.radius = radius;
    }

    public DebugCircleComponent(MyGameObject obj, Config conf) {
        super(obj, conf);
        this.radius = new Double(conf.getDouble("radius")).floatValue();
        Config colorConf = conf.getConfig("color");
        this.color = new Color(
                new Double(colorConf.getDouble("r")).floatValue(),
                new Double(colorConf.getDouble("g")).floatValue(),
                new Double(colorConf.getDouble("b")).floatValue(),
                new Double(colorConf.getDouble("a")).floatValue()
        );
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer, Vector2 pos) {
        shapeRenderer.setColor(color);
        shapeRenderer.circle(pos.x, pos.y, radius);
    }
}
