package com.bitdecay.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bitdecay.game.Launcher;
import com.bitdecay.game.MyGame;
import com.bitdecay.game.util.SoundLibrary;
import com.bitdecay.jump.collision.BitWorld;
import com.bitdecay.jump.gdx.level.EditorIdentifierObject;
import com.bitdecay.jump.gdx.level.RenderableLevelObject;
import com.bitdecay.jump.level.Level;
import com.bitdecay.jump.leveleditor.EditorHook;

import java.util.List;

public class GameScreen implements Screen, EditorHook {

    private MyGame game;

    public GameScreen(MyGame game){
        this.game = game;
    }

    @Override
    public void show() {
        SoundLibrary.stopMusic(Launcher.conf.getString("splash.music"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
    }

    // ////////////////////////////////////
    // Level editor hook methods
    // ////////////////////////////////////

    @Override
    public void update(float v) {

    }

    @Override
    public void render(OrthographicCamera orthographicCamera) {

    }

    @Override
    public BitWorld getWorld() {
        return null;
    }

    @Override
    public List<EditorIdentifierObject> getTilesets() {
        return null;
    }

    @Override
    public List<EditorIdentifierObject> getThemes() {
        return null;
    }

    @Override
    public List<RenderableLevelObject> getCustomObjects() {
        return null;
    }

    @Override
    public void levelChanged(Level level) {

    }
}
