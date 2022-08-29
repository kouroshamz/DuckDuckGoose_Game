package com.BowesGaming.Components;

import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.*;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity implements PlayerCrouchEvent, PlayerJumpEvent, PlayerStandEvent, ViewMoveEvent, LivesLostEvent, LivesGainedEvent {

    public PlayerState playerState;

    public Clock featherTimer = new Clock();

    public int levelPosition = 0;
    public int currentLives = 5;
    public int maxJumps = 2;
    public int jumpVelModifier = 4;

    public List<String> availableSkins = new ArrayList<>();

    public boolean featherEquipped = false;

    public int jumpsLeft = 2;
    private float velocity = 0;
    private double g = 9.81;
    private Clock jumpTimer = new Clock();
    private Clock cooldownTimer = new Clock();
    public boolean infiniteFeather = false;

    private static final Player playerInstance = new Player();


    public static Player get() {
        return playerInstance;
    }

    /**
     * constructor of player
     */
    private Player() {
        super();
    }

    /**
     * this method creates the player with the chosen skin by the user
     */
    public void create() {
        availableSkins.add("Player/Default.png");
        availableSkins.add("Player/Wizard.png");
        availableSkins.add("Player/Female.png");
        changeSkin();
        playerState = PlayerState.STANDING;
        setEntityType(EntityType.Player);
        setPosition(Cnst.duckX, Cnst.floorPosition);
        scale(Cnst.entityScale, Cnst.entityScale);
    }

    /**
     * this method is called to change the skin of the player
     */
    public void changeSkin() {
        Texture texture = StaticHelper.getTextureFromPath(availableSkins.get(Cnst.skinID));
        setTexture(texture);
        setCollisionBox(StaticHelper.getCollisionBox(availableSkins.get(Cnst.skinID)));
        setEntityAnimation(new Animation(0.1f, texture, 16, 16));
        getEntityAnimation().changeAnimation(0, 11,0.1f);
    }

    /**
     * this method is for the player crouching state
     */
    @Override
    public void onPlayerCrouch() {
        if (playerState != PlayerState.JUMPING) {
            playerState = PlayerState.CROUCHING;
            if (isCollidable()) {
                getEntityAnimation().changeAnimation(14, 19, 0.1f);
            } else {
                getEntityAnimation().changeAnimation(34, 39, 0.1f);
            }
        }
    }

    /**
     * this method is called when player is instructed to jump
     */
    @Override
    public void onPlayerJump() {
        if (jumpsLeft > 0) {
            jumpsLeft--;
            jumpTimer.restart();
            velocity = 0;
            velocity += -Cnst.entityScale * (jumpVelModifier - (0.5f*(maxJumps - jumpsLeft)));
            playerState = PlayerState.JUMPING;
            if (isCollidable()) {
                getEntityAnimation().changeAnimation(12, 13, 0.1f);
            } else {
                getEntityAnimation().changeAnimation(32, 33, 0.1f);
            }
            Audio jumpSound = new Audio("Jump.wav");
            jumpSound.play();
        }
    }

    /**
     * this is the method for the jumping animation preformed by player
     */
    public void jump() {
        if ((int) getPosition().y <= Cnst.floorPosition) {
            velocity += (g * jumpTimer.getElapsedTime().asSeconds());
            move(0, velocity);
        } else {
            setPosition(getPosition().x, Cnst.floorPosition);
            velocity = 0;
            playerState = PlayerState.STANDING;
            if (isCollidable()) {
                getEntityAnimation().changeAnimation(0, 11, 0.1f);
            } else {
                getEntityAnimation().changeAnimation(20, 31, 0.1f);
            }
            jumpsLeft = maxJumps;
        }
    }

    /**
     * this method is for the crouching animation preformed by player
     */
    public void crouch() {
        setCollisionBox(StaticHelper.getCollisionBox("CrouchedDuck"));
        setCollisionBoxPosition();
        getCollisionBox().move(0, 5* Cnst.entityScale);
        setTextureRect(getEntityAnimation().getNext());
    }

    /**
     * this is the standing state of the player which is the default state
     */
    @Override
    public void onPlayerStand() {
        if (playerState != PlayerState.JUMPING) {
            playerState = PlayerState.STANDING;
            if (isCollidable()) {
                getEntityAnimation().changeAnimation(0, 11, 0.1f);
            } else {
                getEntityAnimation().changeAnimation(20, 31, 0.1f);
            }
            setCollisionBox(StaticHelper.getCollisionBox("Player/Default.png"));
            setCollisionBoxPosition();
            setTextureRect(getEntityAnimation().getNext());
        }
    }

    /**
     * moves the player in the screen
     * @param viewMoveAmount
     */
    @Override
    public void onViewMove(int viewMoveAmount) {
        Cnst.duckX = (int) (getPosition().x + viewMoveAmount);
        move(viewMoveAmount, 0);
        levelPosition += (viewMoveAmount/ Cnst.entityScale);
    }

    /**
     * this method is called when player collides with enemy or obstacle and loses a life
     */
    @Override
    public void onLivesLost() {
        if (isCollidable()) {
            if (currentLives > 0) {
                currentLives -= 1;
            }
            if (maxJumps == 3) {
                maxJumps = 2;
            }
            getEntityAnimation().changeAnimation(20, 31, 0.1f);
            setCollidable(false);
            cooldownTimer.restart();
        }
    }

    /**
     * this method is for when the player earns an extra life
     */
    @Override
    public void onLivesGained() {
        currentLives++;
    }

    /**
     * checks the state of the player in different scenarios
     */
    @Override
    public void refresh() {
//        System.out.println(getPosition());
        if (playerState == PlayerState.JUMPING) {
            jump();
        } else if (playerState == PlayerState.CROUCHING) {
            crouch();
        }
        if (featherEquipped) {
            if (featherTimer.getElapsedTime().asSeconds() > Cnst.featherTimeInSeconds) {
                featherEquipped = false;
            }
        }
        if (cooldownTimer.getElapsedTime().asSeconds() > 3 && !isCollidable()) {
            setCollidable(true);
            getEntityAnimation().changeAnimation(0, 11, 0.1f);
        }
    }

    public enum PlayerState {
        STANDING,
        JUMPING,
        CROUCHING,
        FALLING,
        COLLISION,
        IDLE,
        ACTION
    }
}
