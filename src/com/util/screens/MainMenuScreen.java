package com.util.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.main.components.EngineGameStart;
import com.main.components.Utility;
import com.main.components.EngineGameStart.ScreenType;
import com.util.audio.AudioObserver;

public class MainMenuScreen extends GameScreen {

	private Stage _stage;
	private EngineGameStart _game;

	Image title; 
	TextButton newGameButton;
	TextButton loadGameButton; 
	TextButton watchIntroButton; 
	TextButton creditsButton;
	TextButton exitButton;
	Window menuWindow;
	Table bacgroundWindow;
	
	public MainMenuScreen(EngineGameStart game){
		_game = game;

		//creation
		_stage = new Stage();
		menuWindow = new Window(" ", Utility.STATUSUI_SKIN);
		//WindowStyle Style = new WindowStyle();
		bacgroundWindow =  new Table();
		bacgroundWindow.setFillParent(true);
		bacgroundWindow.toBack();
		//table.setResizable(true);
		
		menuWindow.setBounds(_stage.getWidth() / 2 -200/2 , _stage.getHeight() / 2 -300/2, 200, 300);
		
		Texture title1 = new Texture(Gdx.files.internal("sprites/Title720.jpg"));
		title = new Image(title1);
		newGameButton = new TextButton   ("  New Game  ", Utility.STATUSUI_SKIN);
		loadGameButton = new TextButton  (" Load Game ", Utility.STATUSUI_SKIN);
		watchIntroButton = new TextButton("Watch Intro ", Utility.STATUSUI_SKIN);
		creditsButton = new TextButton   ("    Credits    ", Utility.STATUSUI_SKIN);
		exitButton = new TextButton      ("       Exit       ",Utility.STATUSUI_SKIN);

		
		//Layout
		bacgroundWindow.add(title).expand();
		menuWindow.add(newGameButton).spaceBottom(10).row();
		menuWindow.add(loadGameButton).spaceBottom(10).row();
		menuWindow.add(watchIntroButton).spaceBottom(10).row();
		menuWindow.add(creditsButton).spaceBottom(10).row();
		menuWindow.add(exitButton).spaceBottom(10).row();

		_stage.addActor(bacgroundWindow);
		_stage.addActor(menuWindow);

		//Listeners
		newGameButton.addListener(new ClickListener() {
									  @Override
									  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
										  return true;
									  }

									  @Override
									  public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
										  _game.setScreen(_game.getScreenType(ScreenType.NewGame));
									  }
								  }
		);

		loadGameButton.addListener(new ClickListener() {

									   @Override
									   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
										   return true;
									   }

									   @Override
									   public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
										   _game.setScreen(_game.getScreenType(ScreenType.LoadGame));
									   }
								   }
		);

		exitButton.addListener(new ClickListener() {

								   @Override
								   public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
									   return true;
								   }

								   @Override
								   public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
									   Gdx.app.exit();
								   }

							   }
		);

		watchIntroButton.addListener(new ClickListener() {

										 @Override
										 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
											 return true;
										 }

										 @Override
										 public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
											 MainMenuScreen.this.notify(AudioObserver.AudioCommand.MUSIC_STOP, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
											 _game.setScreen(_game.getScreenType(ScreenType.WatchIntro));
										 }
									 }
		);

		creditsButton.addListener(new ClickListener() {

										 @Override
										 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
											 return true;
										 }

										 @Override
										 public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
											 _game.setScreen(_game.getScreenType(ScreenType.Credits));
										 }
									 }
		);

		notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		menuWindow.setScaleX(_stage.getWidth() / 1280);
		menuWindow.setScaleY(_stage.getHeight() / 720);
		
		
		title.setScaleX(_stage.getWidth() / 1280);
		title.setScaleY(_stage.getHeight() / 720);
		title.setPosition(0, 0);
		_stage.act(delta);
		_stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().setScreenSize(width, height);
	}

	@Override
	public void show() {
		notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
		Gdx.input.setInputProcessor(_stage);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		_stage.dispose();
	}

}



