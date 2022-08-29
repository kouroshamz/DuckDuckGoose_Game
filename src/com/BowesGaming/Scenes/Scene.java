package com.BowesGaming.Scenes;

import com.BowesGaming.Components.Audio;
import com.BowesGaming.Components.Buttons.Button;
import com.BowesGaming.Components.Entity;
import com.BowesGaming.Components.Buttons.Sign;
import com.BowesGaming.Components.LevelIndicator;
import com.BowesGaming.Components.Platform;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.*;
import com.BowesGaming.Levels.Level;
import org.jsfml.audio.Music;
import org.jsfml.graphics.*;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    public Level currentLevel = null;
    public Boolean drawPlayer = false;
    public Boolean drawPlatform = false;
    public List<Entity> entityList = new ArrayList<>();
    public List<Sign> buttonList = new ArrayList<>();
    public List<Entity> activeEntitiesList = new ArrayList<>();
    public List<Text> sceneTextList = new ArrayList<>();
    public List<Entity> playerCollidables = new ArrayList<>();
    public List<Level> levelList = new ArrayList<>();
    public boolean forceSceneSwap = false;
    public double currentLevelDifficulty = 0;
    public boolean gameScene = false;
    public boolean gameOverScene = false;
    public View sceneView;
    public Background background = new Background();
    public Texture platformTexture;
    public int viewMoveSpeed = 0;
    public Audio music = null;
    public Audio bossMusic = new Audio("BossBattleMusic.wav");
    public boolean instructionSceneShownThisSession = false;
    public LevelIndicator levelIndicator;

    public List<PlayerJumpEvent> playerJumpEvents = new ArrayList<>();
    public List<PlayerCrouchEvent> playerCrouchEvents = new ArrayList<>();
    public List<PauseGameEvent> pauseGameEvents = new ArrayList<>();
    public List<ResumeGameEvent> resumeGameEvents = new ArrayList<>();
    public List<GameStartEvent> gameStartEvents = new ArrayList<>();
    public List<ViewMoveEvent> viewMoveEvents = new ArrayList<>();
    public List<PlayerStandEvent> playerStandEvents = new ArrayList<>();
    public List<PlayerStopJumpingEvent> playerStopJumpingEvents = new ArrayList<>();
    public List<AnimationStateUpdateEvent> animationStateUpdateEvents = new ArrayList<>();
    public List<ScreenUpdateEvent> screenUpdateEvents = new ArrayList<>();
    public List<ButtonSelectedEvent> buttonSelectedEvents = new ArrayList<>();
    public List<ExitButtonClickedEvent> exitButtonClickedEvents = new ArrayList<>();
    public List<StartButtonClickedEvent> startButtonClickedEvents = new ArrayList<>();
    public List<HighScoreButtonClickedEvent> highScoreButtonClickedEvents = new ArrayList<>();
    public List<SettingsButtonClickedEvent> settingsButtonClickedEvents = new ArrayList<>();
    public List<CollisionEvent> collisionEvents = new ArrayList<>();
    public List<SpriteEntersFrameEvent> spriteEntersFrameEvents = new ArrayList<>();
    public List<SceneAnimationEvent> sceneAnimationEvents = new ArrayList<>();
    public List<LevelChangeEvent> levelChangeEvents = new ArrayList<>();
    public List<AnimationEvent> animationEvents = new ArrayList<>();
    public List<BackButtonClickedEvent> backButtonClickedEvents = new ArrayList<>();
    public List<VolumeButtonClickedEvent> volumeButtonClickedEvents = new ArrayList<>();
    public List<ControlsButtonClickedEvent> controlsButtonClickedEvents = new ArrayList<>();
    public List<DifficultyButtonClickedEvent> difficultyButtonClickedEvents = new ArrayList<>();
    public List<AboutButtonClickedEvent> aboutButtonClickedEvents = new ArrayList<>();
    public List<PlusButtonClickedEvent>  plusButtonClickedEvents = new ArrayList<>();
    public List<MinusButtonClickedEvent> minusButtonClickedEvents = new ArrayList<>();
    public List<EasyButtonClickedEvent> easyButtonClickedEvents = new ArrayList<>();
    public List<ModerateButtonClickedEvent> moderateButtonClickedEvents = new ArrayList<>();
    public List<HardButtonClickedEvent> hardButtonClickedEvents = new ArrayList<>();
    public List<ScoreChangeEvent> scoreChangeEventList = new ArrayList<>();
    public List<DifficultyIndicatorChangedEvent> difficultyIndicatorChangedEvents = new ArrayList<>();
    public List<NoMoreLivesEvent> noMoreLivesEvents = new ArrayList<>();
    public List<LivesLostEvent> livesLostEvents = new ArrayList<>();
    public List<FriendlyFiredEvent> friendlyFiredEvents = new ArrayList<>();
    public List<GameOverEvent> gameOverEvents = new ArrayList<>();
    public List<LivesGainedEvent> livesGainedEvents = new ArrayList<>();
    public List<EnemyFiredEvent> enemyFiredEvents = new ArrayList<>();
    public List<BossActionEvent> bossActionEvents = new ArrayList<>();
    public List<BossHitEvent> bossHitEvents = new ArrayList<>();
    public List<RefreshEvent> actionEventList = new ArrayList<>();
    public List<DuckSkinUpEvent> duckSkinUpEvents = new ArrayList<>();
    public List<DuckSkinDownEvent> duckSkinDownEvents = new ArrayList<>();

    public Scene() {
        ConstView view = Engine.get().getDefaultView();
        sceneView = new View( view.getCenter(), view.getSize() );
        background.setPosition(view.getCenter().x-view.getSize().x/2, (float) view.getCenter().y-view.getSize().y/2);
    }

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }
    public void addMultiple(List<Entity> entities) {
        entityList.addAll(entities);
    }
    public void addPlayerCollidable(Entity entity) {
        playerCollidables.add(entity);
    }
    public void removePlayerCollidable(Entity entity) {
        playerCollidables.remove(entity);
    }
    public void removeEntities(List<Entity> entities) {
        entityList.removeAll(entities);
    }
    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public void addEntity(Sign sign) {
        buttonList.add(sign);
        sceneTextList.add(sign.getText());
        buttonSelectedEvents.add(sign);
    }
    public void addEntity(List<Button> buttons) {
        buttonList.addAll(buttons);
    }
    public void removeButton(Button button) {
        buttonList.remove(button);
        sceneTextList.remove(button.getText());
    }

    public void addActive(Entity entity) {
        activeEntitiesList.add(entity);
    }
    public void addActive(List<Entity> entities) {
        activeEntitiesList.addAll(entities);
    }
    public void removeActiveEntity(Entity entity) {
        activeEntitiesList.remove(entity);
    }

    public void addText(Text text) {
        sceneTextList.add(text);
    }
    public void removeText(Text text) {
        sceneTextList.remove(text);
    }

    public List<Entity> getEntities() {
        return entityList;
    }
    public List<Sign> getButtons() {
        return buttonList;
    }
    public List<Entity> getActive() {
        return activeEntitiesList;
    }
    public Sprite getBackground() {
        return background;
    }
    public Boolean getDrawPlayer() { return drawPlayer; }
    public Boolean getDrawPlatform() {return drawPlatform;}
    public Texture getPlatformTexture() {return platformTexture;}
    public int getViewMoveSpeed() {return viewMoveSpeed;}

    //Event adders
    public void addPlayerJumpListener(PlayerJumpEvent listener) {
        playerJumpEvents.add(listener);
    }

    public void addPlayerCrouchListener(PlayerCrouchEvent listener) {
        playerCrouchEvents.add(listener);
    }

    public void addPauseGameEvent(PauseGameEvent listener) {
        pauseGameEvents.add(listener);
    }

    public void addResumeGameEvent(ResumeGameEvent listener) {
        resumeGameEvents.add(listener);
    }

    public void addGameStartEvent(GameStartEvent listener) {
        gameStartEvents.add(listener);
    }

    public void addViewMoveListeners(ViewMoveEvent listener) {
        viewMoveEvents.add(listener);
    }

    public void addPlayerStandListener(PlayerStandEvent listener) {
        playerStandEvents.add(listener);
    }

    public void addPlayerStopJumpingListener(PlayerStopJumpingEvent listener) {
        playerStopJumpingEvents.add(listener);
    }

    public void addAnimationStateUpdateListeners(AnimationStateUpdateEvent listeners) {
        animationStateUpdateEvents.add(listeners);
    }

    public void addScreenUpdateListeners(ScreenUpdateEvent listeners) {
        screenUpdateEvents.add(listeners);
    }

    public void removeScreenUpdateListeners(ScreenUpdateEvent listeners) {
        screenUpdateEvents.remove(listeners);
    }

    public void addButtonSelectedListeners(ButtonSelectedEvent listeners) {
        buttonSelectedEvents.add(listeners);
    }

    public void addExitButtonClickedListener(ExitButtonClickedEvent listeners) {
        exitButtonClickedEvents.add(listeners);
    }

    public void addStartButtonClickedListener(StartButtonClickedEvent listeners) {
        startButtonClickedEvents.add(listeners);
    }

    public void addHighScoreButtonClickedListener(HighScoreButtonClickedEvent listeners) {
        highScoreButtonClickedEvents.add(listeners);
    }

    public void addSettingsButtonClickedListener(SettingsButtonClickedEvent listeners) {
        settingsButtonClickedEvents.add(listeners);
    }

    public void addBackButtonClickedListener(BackButtonClickedEvent listeners) {
        backButtonClickedEvents.add(listeners);
    }

    public void addVolumeButtonClickedListener(VolumeButtonClickedEvent listeners) {
        volumeButtonClickedEvents.add(listeners);
    }

    public void addControlsButtonClickedListener(ControlsButtonClickedEvent listeners) {
        controlsButtonClickedEvents.add(listeners);
    }

    public void addDifficultyButtonClickedListener(DifficultyButtonClickedEvent listeners) {
        difficultyButtonClickedEvents.add(listeners);
    }

    public void addAboutButtonClickedListener(AboutButtonClickedEvent listeners) {
        aboutButtonClickedEvents.add(listeners);
    }

    public void addCollisionListener(CollisionEvent listeners) {
        collisionEvents.add(listeners);
    }

    public void removeCollisionListener(CollisionEvent listeners) {
        collisionEvents.remove(listeners);
    }

    public void addSpriteEntersFrameListener(SpriteEntersFrameEvent listeners) {
        spriteEntersFrameEvents.add(listeners);
    }

    public void removeViewMoveListener(ViewMoveEvent listeners) {
        viewMoveEvents.remove(listeners);
    }

    public void removePlayerCrouchListener(PlayerCrouchEvent playerCrouchEvent) {
        playerCrouchEvents.remove(playerCrouchEvent);
    }

    public void removePlayerJumpListener(PlayerJumpEvent playerJumpEvent) {
        playerJumpEvents.remove(playerJumpEvent);
    }

    public void removePlayerStandListener(PlayerStandEvent playerStandEvent) {
        playerStandEvents.remove(playerStandEvent);
    }

    public void removePlayerStopJumpingListener(PlayerStopJumpingEvent playerStopJumpingEvent) {
        playerStopJumpingEvents.remove(playerStopJumpingEvent);
    }

    public void addSceneAnimationListener(SceneAnimationEvent sceneAnimationEvent) {
        sceneAnimationEvents.add(sceneAnimationEvent);
    }

    public void removeSceneAnimationListener(SceneAnimationEvent sceneAnimationEvent) {
        sceneAnimationEvents.remove(sceneAnimationEvent);
    }

    public void addLevelChangeListener(LevelChangeEvent levelChangeEvent) {
        levelChangeEvents.add(levelChangeEvent);
    }

    public void removeLevelChangeListener(LevelChangeEvent levelChangeEvent) {
        levelChangeEvents.remove(levelChangeEvent);
    }

    public void addAnimationListener(AnimationEvent animationEvent) {
        animationEvents.add(animationEvent);
    }

    public void addDifficultyIndicatorListener(DifficultyIndicatorChangedEvent difficultyIndicatorChangedEvent) {
        difficultyIndicatorChangedEvents.add(difficultyIndicatorChangedEvent);
    }

    public void removeAnimationListener(AnimationEvent animationEvent) {
        animationEvents.remove(animationEvent);
    }

    public void addScoreChangedListener(ScoreChangeEvent scoreChangeEvent) {
        scoreChangeEventList.add(scoreChangeEvent);
    }

    public void addNoMoreLivesListener(NoMoreLivesEvent noMoreLivesEvent) {
        noMoreLivesEvents.add(noMoreLivesEvent);
    }

    public void addLivesLostListener(LivesLostEvent livesLostEvent) {
        livesLostEvents.add(livesLostEvent);
    }

    public void addLivesGainedListener(LivesGainedEvent livesGainedEvent) {
        livesGainedEvents.add(livesGainedEvent);
    }

    public void addFeatherFiredListener(FriendlyFiredEvent friendlyFiredEvent) {
        friendlyFiredEvents.add(friendlyFiredEvent);
    }

    public void addPlusButtonClickedListener(PlusButtonClickedEvent listeners){
        plusButtonClickedEvents.add(listeners);
    }

    public void addMinusButtonClickedListener(MinusButtonClickedEvent listeners){
        minusButtonClickedEvents.add(listeners);
    }

    public void addEasyButtonClickedListener(EasyButtonClickedEvent listeners){
        easyButtonClickedEvents.add(listeners);
    }

    public void addModerateButtonClickedListener(ModerateButtonClickedEvent listeners){
        moderateButtonClickedEvents.add(listeners);
    }

    public void addHardButtonClickedListener(HardButtonClickedEvent listeners){
        hardButtonClickedEvents.add(listeners);
    }

    public void addDuckSkinUpListener(DuckSkinUpEvent listeners) {
        duckSkinUpEvents.add(listeners);
    }

    public void addDuckSkinDownListener(DuckSkinDownEvent listeners) {
        duckSkinDownEvents.add(listeners);
    }

    public void addGameOverListener(GameOverEvent gameOverEvent) {
        gameOverEvents.add(gameOverEvent);
    }

    public void addEnemyFiredListener(EnemyFiredEvent enemyFiredEvent) {
        enemyFiredEvents.add(enemyFiredEvent);
    }

    public void addBossFiredListener(BossActionEvent bossActionEvent) {
        bossActionEvents.add(bossActionEvent);
    }

    public void addBossHitEventListener(BossHitEvent bossHitEvent) {
        bossHitEvents.add(bossHitEvent);
    }

    public void setBackground(Texture texture) {
        background.setBackground(texture);
    }

    public void removeEntities(Entity entity) {
        entityList.remove(entity);
    }

    public void setPlatformTexture(Texture texture) {
        Platform.get().setPlatformTexture(texture);
    }

    public void addAction(RefreshEvent actionEvent) {
        actionEventList.add(actionEvent);
    }

    public void removeAction(RefreshEvent actionEvent) {
        actionEventList.remove(actionEvent);
    }
}
