package com.bitdecay.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.bitdecay.game.MyGame;

public class CreditsScreen implements Screen {

    private static String SPACE_AFTER_TITLE = "\n\n\n";
    private static String SPACE_AFTER_NAME = "\n\n";

    private MyGame game;
    private Stage stage = new Stage();

    private Image background;
    private Label lblTitle;
    private Label lblBack;
    private Label lblCredits;


    private int menuSelection;
    private boolean downIsPressed;
    private boolean upIsPressed;
    private boolean escWasPressed;


    boolean active = true;

    public CreditsScreen(MyGame game) {
        this.game = game;

        menuSelection = 0;
        downIsPressed = false;
        upIsPressed = false;
        escWasPressed = false;

        Skin skin = new Skin(Gdx.files.classpath("skin/skin.json"));

        background = new Image(MyGame.atlas.findRegion("bg/space"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        lblTitle = new Label(" Credits", skin);
        lblTitle.setFontScale(10);
        lblTitle.setFillParent(true);
        lblTitle.setAlignment(Align.top, Align.left);
        lblTitle.setColor(Color.SKY);

        lblBack = new Label("Press esc/B to go back", skin);
        lblBack.setFontScale(5);
        lblBack.setFillParent(true);
        lblBack.setAlignment(Align.bottom, Align.right);
        lblBack.setColor(Color.GRAY);

        lblCredits = new Label("Oculomancers (Art): " + SPACE_AFTER_TITLE +
                "Erik \"Groovemaster\"\n Meredith" + SPACE_AFTER_NAME +
                "Logan \"New York is too\nfar away\" Moore" + SPACE_AFTER_TITLE +
                "Bitwizards (Dev):" + SPACE_AFTER_TITLE +
                "Mike \"Font Destroyer\"\n Wingfield" + SPACE_AFTER_NAME +
                "Jake \"Donuts & Whiskey\"\n Kabob-Thompski" + SPACE_AFTER_NAME +
                "Jake \"Luke\" Fisher" + SPACE_AFTER_NAME +
                "Luke \"Jake\" Fisher" + SPACE_AFTER_NAME +
                "Tristan \"El Hombre Nuevo\"\n Havelick" + SPACE_AFTER_TITLE +
                "Soundscapes:" + SPACE_AFTER_TITLE +
                "Logan \n \"Yes, he's two people\"\n Moore",
                skin);
        lblCredits.setFontScale(6);
        lblCredits.setFillParent(true);
        lblCredits.setAlignment(Align.center);
        lblCredits.setColor(Color.WHITE);


        stage.addActor(background);
        stage.addActor(lblTitle);
        stage.addActor(lblBack);
        stage.addActor(lblCredits);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
//         animate the main menu when entering
        lblCredits.addAction(Actions.sequence(
                Actions.moveBy(0, -(Gdx.graphics.getHeight()*1.5f)),
                Actions.moveBy(0, Gdx.graphics.getHeight() * 4, 30),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        nextScreen();
                    }
                })

        ));
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) nextScreen();
    }

    public void nextScreen(){
        game.setScreen(new MainMenuScreen(game));
    }


    public void update(float delta){

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
        stage.dispose();
    }
}
