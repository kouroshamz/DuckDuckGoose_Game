package com.BowesGaming.Engine;

import com.BowesGaming.Components.Buttons.Button;
import com.BowesGaming.Components.*;
import com.BowesGaming.Components.Buttons.Sign;
import com.BowesGaming.Events.*;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Scenes.Scene;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.VideoMode;

import java.util.ArrayList;
import java.util.List;

public final class Engine extends RenderWindow implements ScreenUpdateEvent, NoMoreLivesEvent, ViewMoveEvent {

    private static final Engine engineInstance = new Engine();
    public Scene currentScene;
    public Boolean isGameOver = false;
    public List<RefreshEvent> actionsToAdd = new ArrayList<>();
    public List<RefreshEvent> actionsToRemove = new ArrayList<>();

    private Engine() {
    }

    public static Engine get() {
        return engineInstance;
    }

    public void create() {
        create(new VideoMode(Cnst.resX, Cnst.resY), "Game2D");
        setFramerateLimit(30);
        setIcon( StaticHelper.getImageFromPath("UserInterface/8BitDuck.png") );
        currentScene = new Scene();
    }

    //Entity adders

    public void add(Entity entity) {
        currentScene.entityList.add(entity);
    }
    public void add(List<Entity> entities) {
        currentScene.entityList.addAll(entities);
    }

    public void addActive(Entity entity) {
        currentScene.activeEntitiesList.add(entity);
    }

    public void add(Button button) {
        currentScene.buttonList.add(button);
        currentScene.sceneTextList.add(button.getText());
    }

    //Entity removers

    public void remove(Entity entity) {
        currentScene.entityList.remove(entity);
    }
    public void remove(Button button) {
        currentScene.buttonList.remove(button);
    }

    public void removeActive(Entity entity) {
        currentScene.activeEntitiesList.remove(entity);
    }

    //Scene getters

    public List<Entity> getEntities() {
        return currentScene.entityList;
    }
    public Entity getEntity(int i) {
        return currentScene.entityList.get(i);
    }

    public List<Sign> getButtons() {
        return currentScene.buttonList;
    }

    public List<Entity> getActives() {
        return currentScene.activeEntitiesList;
    }

    public Entity getActive(int i) {
        return currentScene.activeEntitiesList.get(i);
    }

    //    public Background getBackground() {
//        return new Background(currentScene.background);
//    }

    public Boolean getDrawPlayer() {
        return currentScene.drawPlayer;
    }
    public Boolean getDrawPlatform() {
        return currentScene.drawPlatform;
    }

    //Size getters

    public int
    getEntityListSize() {
        return currentScene.entityList.size();
    }
    public int getActiveEntityListSize() {
        return currentScene.activeEntitiesList.size();
    }

    public int getButtonListSize() {
        return currentScene.buttonList.size();
    }

    public List<Entity> getPlayerCollidables() {
        return currentScene.playerCollidables;
    }

    //Scene swapper

    public void setScene(Scene scene) {
        if (scene.music != null) {
            if (currentScene.music != null) {
                currentScene.music.pause();
            }
            scene.music.setLoop(true);
            scene.music.play();
        }
        currentScene = scene;
        refreshSceneAssets();
    }
    public void refreshSceneAssets() {
        currentScene.screenUpdateEvents = new ArrayList<>();
        currentScene.addScreenUpdateListeners(Engine.get());
        int buttonCount = 0;
        for (Sign sign : currentScene.buttonList) {
            buttonCount++;
            currentScene.addScreenUpdateListeners(sign);
        }
        int entityCount = 0;
        for (Entity entity : currentScene.entityList) {
            entityCount++;
            currentScene.addScreenUpdateListeners(entity);
        }
        if (getDrawPlayer()) currentScene.addScreenUpdateListeners(Player.get());
        if (getDrawPlatform()) currentScene.addScreenUpdateListeners(Platform.get());
        setView(currentScene.sceneView);
//        System.out.format("\n Refreshed Resources:\n\tButtons: %03d Entities: %03d Player: %b Platform: %b\n", buttonCount, entityCount, getDrawPlayer(), getDrawPlatform());
    }

    //Event callers
    public void playerJump() {
        for (PlayerJumpEvent listener : currentScene.playerJumpEvents) {
            listener.onPlayerJump();
        }
    }
    public void playerCrouch() {
        for (PlayerCrouchEvent listener : currentScene.playerCrouchEvents) {
            listener.onPlayerCrouch();
        }
    }

    public void pauseGame() {
        for (PauseGameEvent listener : currentScene.pauseGameEvents) {
            listener.onPauseGame();
        }
    }

    public void resumeGame() {
        for (ResumeGameEvent listener : currentScene.resumeGameEvents) {
            listener.onResumeGame();
        }
    }

    public void gameStart() {
        for (GameStartEvent gameStartEvent : currentScene.gameStartEvents) {
            gameStartEvent.onGameStart();
        }
    }

    public void viewMove(int viewMoveAmount) {
        for (ViewMoveEvent viewMoveEvent : currentScene.viewMoveEvents) {
            viewMoveEvent.onViewMove(viewMoveAmount);
        }
    }

    public void playerStand() {
        for (PlayerStandEvent playerStandEvent : currentScene.playerStandEvents) {
            playerStandEvent.onPlayerStand();
        }
    }

    public void playerStopJumping() {
        for (PlayerStopJumpingEvent playerStopJumpingEvent : currentScene.playerStopJumpingEvents) {
            playerStopJumpingEvent.onPlayerStopJumping();
        }
    }

    public void animationStateUpdate() {
        for (AnimationStateUpdateEvent animationStateUpdateEvent : currentScene.animationStateUpdateEvents) {
            animationStateUpdateEvent.onAnimationStateUpdate();
        }
    }

    public void screenUpdate() {
        for (ScreenUpdateEvent screenUpdateEvent : currentScene.screenUpdateEvents) {
            screenUpdateEvent.onScreenUpdate();
        }
    }

    public void buttonSelected(Vector2i position) {
        for (ButtonSelectedEvent buttonSelectedEvent : currentScene.buttonSelectedEvents) {
            buttonSelectedEvent.onButtonSelection(position);
        }
    }

    public void exitButtonClicked() {
        for (ExitButtonClickedEvent exitButtonClickedEvent : currentScene.exitButtonClickedEvents) {
            exitButtonClickedEvent.onExitButtonClicked();
        }
    }

    public void startButtonClicked(Scene scene) {
        for (StartButtonClickedEvent startButtonClickedEvent : currentScene.startButtonClickedEvents) {   // NEW
            startButtonClickedEvent.onStartButtonClicked(scene);
        }
    }

    public void highScoreButtonClicked() {
        for (HighScoreButtonClickedEvent highScoreButtonClickedEvent : currentScene.highScoreButtonClickedEvents) { // NEW
            highScoreButtonClickedEvent.onHighScoreButtonClicked();
        }
    }

    public void settingsButtonClicked(Scene scene) {
        for (SettingsButtonClickedEvent settingsButtonClickedEvent : currentScene.settingsButtonClickedEvents) { // NEw
            settingsButtonClickedEvent.onSettingsButtonClicked(scene);
        }
    }

    public void aboutButtonClicked(Scene scene){
        for (AboutButtonClickedEvent aboutButtonClickedEvent : currentScene.aboutButtonClickedEvents){ // NEw
            aboutButtonClickedEvent.onAboutButtonClicked(scene);
        }
    }

    public void backButtonClicked(Scene scene){
        for (BackButtonClickedEvent backButtonClickedEvent : currentScene.backButtonClickedEvents){ // NEw
            backButtonClickedEvent.onBackButtonClicked(scene);
        }
    }

    public void controlsButtonClicked(Scene scene){
        for (ControlsButtonClickedEvent controlsButtonClickedEvent : currentScene.controlsButtonClickedEvents){ // NEw
            controlsButtonClickedEvent.onControlsButtonClicked(scene);
        }
    }

    public void difficultyButtonClicked(Scene scene){
        for (DifficultyButtonClickedEvent difficultyButtonClickedEvent : currentScene.difficultyButtonClickedEvents){ // NEw
            difficultyButtonClickedEvent.onDifficultyButtonClicked(scene);
        }
    }

    public void volumeButtonClicked(Scene scene){
        for (VolumeButtonClickedEvent volumeButtonClickedEvent : currentScene.volumeButtonClickedEvents){ // NEw
            volumeButtonClickedEvent.onVolumeButtonClicked(scene);
        }
    }

    public void spriteEntersFrame(int viewLowerBound, int viewUpperBound, Engine engine) {
        for (SpriteEntersFrameEvent spriteEntersFrameEvent : currentScene.spriteEntersFrameEvents) {
            spriteEntersFrameEvent.onSpriteEntersFrame(viewLowerBound, viewUpperBound, engine);
        }
    }

    public void sceneAnimation(float time) {
        for (SceneAnimationEvent sceneAnimationEvent : currentScene.sceneAnimationEvents) {
            sceneAnimationEvent.onSceneAnimate(time);
        }
    }

    public void levelChanged() {
        for (LevelChangeEvent levelChangeEvent : currentScene.levelChangeEvents) {
            levelChangeEvent.onLevelChange();
        }
    }

    public void animationFired() {
        for (AnimationEvent animationEvent : currentScene.animationEvents) {
            animationEvent.onAnimationEvent();
        }
    }

    public void scoreChanged(int amount) {
        for (ScoreChangeEvent scoreChangeEvent : currentScene.scoreChangeEventList) {
            scoreChangeEvent.onScoreChanged(amount);
        }
    }
    public void difficultyIndicatorChanged() {
        for (DifficultyIndicatorChangedEvent difficultyIndicatorChangedEvent : currentScene.difficultyIndicatorChangedEvents) {
            difficultyIndicatorChangedEvent.onDifficultyIndicatorChanged();
        }
    }

    public void noMoreLives() {
        for (NoMoreLivesEvent noMoreLivesEvent: currentScene.noMoreLivesEvents) {
            noMoreLivesEvent.onNoMoreLives();
        }
    }
    public void livesLost() {
        for (LivesLostEvent livesLostEvent : currentScene.livesLostEvents) {
            livesLostEvent.onLivesLost();
        }
    }

    public void featherFired() {
        for (FriendlyFiredEvent friendlyFiredEvent : currentScene.friendlyFiredEvents) {
            friendlyFiredEvent.onFeatherFired();
        }
    }

    public void gameOver() {
        for (GameOverEvent gameOverEvent : currentScene.gameOverEvents) {
            gameOverEvent.onGameOver();
        }
    }

    public void livesGained() {
        for (LivesGainedEvent livesGainedEvent : currentScene.livesGainedEvents) {
            livesGainedEvent.onLivesGained();
        }
    }

    public void enemyFired() {
        for (EnemyFiredEvent enemyFiredEvent : currentScene.enemyFiredEvents) {
            enemyFiredEvent.onEnemyFired();
        }
    }

    public void bossAction() {
        for (BossActionEvent bossActionEvent : currentScene.bossActionEvents) {
            bossActionEvent.onBossFiredEvent();
        }
    }

    public void bossHit(Entity friendlyEntity, Entity entity) {
        for (BossHitEvent bossHitEvent : currentScene.bossHitEvents) {
            bossHitEvent.onBossHit(friendlyEntity, entity);
        }
    }

    public void plusButtonClicked(Scene scene){
        for (PlusButtonClickedEvent plusButtonClickedEvent : currentScene.plusButtonClickedEvents){ // NEw
            plusButtonClickedEvent.onPlusButtonClicked(scene);
        }
    }

    public void minusButtonClicked(Scene scene){
        for (MinusButtonClickedEvent minusButtonClickedEvent : currentScene.minusButtonClickedEvents){ // NEw
            minusButtonClickedEvent.onMinusButtonClicked(scene);
        }
    }

    public void easyButtonClicked(Scene scene){
        for (EasyButtonClickedEvent easyButtonClickedEvent : currentScene.easyButtonClickedEvents){ // NEw
            easyButtonClickedEvent.onEasyButtonClicked(scene);
        }
    }

    public void moderateButtonClicked(Scene scene){
        for (ModerateButtonClickedEvent moderateButtonClickedEvent : currentScene.moderateButtonClickedEvents){ // NEw
            moderateButtonClickedEvent.onModerateButtonClicked(scene);
        }
    }

    public void hardButtonClicked(Scene scene){
        for (HardButtonClickedEvent hardButtonClickedEvent : currentScene.hardButtonClickedEvents){ // NEw
            hardButtonClickedEvent.onHardButtonClicked(scene);
        }
    }

    public void skinUpButtonClicked(Scene scene) {
        for (DuckSkinUpEvent skinUpEvent : currentScene.duckSkinUpEvents) {
            skinUpEvent.onDuckSkinUp();
        }
    }

    public void skinDownButtonClicked(Scene scene) {
        for (DuckSkinDownEvent skinDownEvent : currentScene.duckSkinDownEvents) {
            skinDownEvent.onDuckSkinDown();
        }
    }

    public void highScoreButtonCLicked(Scene scene) {
        for (HighScoreButtonClickedEvent highScoreButtonClickedEvent : currentScene.highScoreButtonClickedEvents) {
            highScoreButtonClickedEvent.onHighScoreButtonClicked();
        }
    }

    @Override
    public void onScreenUpdate() {
        draw(currentScene.background);
    }

    @Override
    public void onViewMove(int viewMoveAmount) {
        currentScene.sceneView.move(viewMoveAmount, 0);
        setView(currentScene.sceneView);
    }

    @Override
    public void onNoMoreLives() {
    }

    public void refresh() {
        for (RefreshEvent refreshEvent : currentScene.actionEventList) {
            refreshEvent.refresh();
        }
    }

    public void spawnEnemy(Enemy enemy) {
        currentScene.addViewMoveListeners(enemy);
        spawnEntity(enemy);
        enemy.refresh();
    }

    public void spawnEntity(Entity entity) {
        currentScene.addActive(entity);
        currentScene.addEntity(entity);
        currentScene.addAnimationListener(entity);
        currentScene.addScreenUpdateListeners(entity);
    }

    public void spawnEnemyProjectile(EnemyProjectile enemyProjectile) {
        spawnEntity(enemyProjectile);
        currentScene.addViewMoveListeners(enemyProjectile);
    }

    public void queueActionToAdd(RefreshEvent actionEvent) {
        actionsToAdd.add(actionEvent);
    }

    public void queueActionsToRemove(RefreshEvent actionEvent) {
        actionsToRemove.add(actionEvent);
    }
}
