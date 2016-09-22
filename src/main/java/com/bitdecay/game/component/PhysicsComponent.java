package com.bitdecay.game.component;

import com.bitdecay.game.gameobject.MyGameObject;
import com.bitdecay.jump.BitBody;
import com.bitdecay.jump.BodyType;
import com.bitdecay.jump.JumperBody;
import com.bitdecay.jump.geom.BitRectangle;
import com.bitdecay.jump.properties.JumperProperties;
import com.bitdecay.jump.render.JumperRenderStateWatcher;
import com.typesafe.config.Config;

/**
 * The component in charge of tracking the BitBody of the object
 */
public class PhysicsComponent extends AbstractComponent {
    private BitBody body;

    public PhysicsComponent(MyGameObject obj, BitBody body){
        super(obj);
        this.body = body;
    }

    public PhysicsComponent(MyGameObject obj, float width, float height, int jumpStrength, int jumpCount, float jumpVariableHeightWindow, int deceleration, int acceleration, int airAcceleration, boolean jumpHittingHeadStopsJump){
        super(obj);
        JumperBody body = new JumperBody();
        body.props.deceleration = deceleration;
        body.props.acceleration = acceleration;
        body.props.airAcceleration = airAcceleration;
        body.jumperProps = new JumperProperties();
        body.jumperProps.jumpStrength = jumpStrength;
        body.jumperProps.jumpCount = jumpCount;
        body.jumperProps.jumpVariableHeightWindow = jumpVariableHeightWindow;
        body.bodyType = BodyType.DYNAMIC;
        body.aabb.set(new BitRectangle(0, 0, width, height));
        body.userObject = this;
        body.renderStateWatcher = new JumperRenderStateWatcher();
        body.jumperProps.jumpHittingHeadStopsJump = jumpHittingHeadStopsJump;
        this.body = body;
    }

    public PhysicsComponent(MyGameObject obj, Config conf) {
        this(obj,
                new Double(conf.getDouble("width")).floatValue(),
                new Double(conf.getDouble("height")).floatValue(),
                conf.getInt("jumpStrength"),
                conf.getInt("jumpCount"),
                new Double(conf.getDouble("jumpVariableHeightWindow")).floatValue(),
                conf.getInt("deceleration"),
                conf.getInt("acceleration"),
                conf.getInt("airAcceleration"),
                conf.getBoolean("jumpHittingHeadStopsJump"));
    }

    public BitBody body() {
        return body;
    }
}
