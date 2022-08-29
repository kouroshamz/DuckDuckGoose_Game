package com.BowesGaming.Components;

import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.*;
import com.BowesGaming.Loaders.StaticHelper;
import org.jsfml.audio.Music;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;

import java.util.ArrayList;
import java.util.List;

public class Entity extends Sprite implements RefreshEvent, ScreenUpdateEvent, AnimationEvent, DeleteEvent, CollisionEvent {
    private RectangleShape collisionBox = new RectangleShape();
    private boolean isDrawn = true;
    private boolean isCollisionBoxDrawn = false;
    private boolean isCollidable = true;
    private EntityType entityType = EntityType.Entity;

    private int collisionBoxOffset;
    private int collisionOffsetY;

    private Animation entityAnimation;

    public Entity() {
    }

    /**
     * this method calculates centers the collision box with its entity
     */
    public void calculateCollisionBoxOffset() {
        setCollisionBoxOffset((int) ((getGlobalBounds().width - getCollisionBox().getGlobalBounds().width) / 2));
        setCollisionOffsetY((int) ((getGlobalBounds().height - getCollisionBox().getGlobalBounds().height) / 2));
    }

    /**
     * this method positions the collision box in the correct place
     */
    public void setCollisionBoxPosition() {
        getCollisionBox().setPosition(getGlobalBounds().left, getGlobalBounds().top);
        calculateCollisionBoxOffset();
        getCollisionBox().move(getCollisionBoxOffset(), getCollisionOffsetY());
        getCollisionBox().setRotation(getRotation());
    }

    /**
     * asks the engine if it should draw the collision box and the entities and draws it
     */
    @Override
    public void onScreenUpdate() {
        if (isDrawn()) Engine.get().draw(this);
        if (isCollisionBoxDrawn()) Engine.get().draw(getCollisionBox());
    }

    /**
     * tells the entitiy if its time to move on to the next animation
     */
    @Override
    public void onAnimationEvent() {
        setTextureRect(getEntityAnimation().getNext());
    }

    /**
     * this method deletes the related entities when two hit-boxes collide
     */
    @Override
    public void delete() {
        Engine.get().currentScene.removeEntity(this);
        Engine.get().currentScene.removeActiveEntity(this);
        Engine.get().currentScene.removeScreenUpdateListeners(this);
        Engine.get().currentScene.removeAnimationListener(this);
        Engine.get().queueActionsToRemove(this);
        setCollidable(false);
        setDrawn(false);
    }

    /**
     * this method is called when two hit-boxes collide in a game
     *
     * @param collidedEntity
     */
    @Override
    public void onCollision(Entity collidedEntity) {
    }

    /**
     * base method that makes this class run
     */
    @Override
    public void refresh() {
    }

    public RectangleShape getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(RectangleShape collisionBox) {
        this.collisionBox = collisionBox;
    }

    public boolean isDrawn() {
        return isDrawn;
    }

    public void setDrawn(boolean drawn) {
        this.isDrawn = drawn;
    }

    public boolean isCollisionBoxDrawn() {
        return isCollisionBoxDrawn;
    }

    public void setCollisionBoxDrawn(boolean collisionBoxDrawn) {
        this.isCollisionBoxDrawn = collisionBoxDrawn;
    }

    public boolean isCollidable() {
        return isCollidable;
    }

    public void setCollidable(boolean collidable) {
        this.isCollidable = collidable;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public int getCollisionBoxOffset() {
        return collisionBoxOffset;
    }

    public void setCollisionBoxOffset(int collisionBoxOffset) {
        this.collisionBoxOffset = collisionBoxOffset;
    }

    public int getCollisionOffsetY() {
        return collisionOffsetY;
    }

    public void setCollisionOffsetY(int collisionOffsetY) {
        this.collisionOffsetY = collisionOffsetY;
    }

    public Animation getEntityAnimation() {
        return entityAnimation;
    }

    public void setEntityAnimation(Animation entityAnimation) {
        this.entityAnimation = entityAnimation;
    }

    public static enum EntityType {
        Entity,
        Player,
        FriendlyProjectile,
        Enemy,
        EnemyProjectile,
        Obstacle,
        Boss,
        BossHead,
        BossBody,
        PowerUp,
    }

    public static class Animation {
        public List<IntRect> activeFrames;
        private List<IntRect> frames;
        private float framePeriod;
        private int currentIndex;
        private Clock animationTimer;

        /**
         * holds the elements in the sprite sheet and and calls changeAnimation method to switch between the wanted frames
         *
         * @param framePeriod
         * @param spriteSheet
         * @param spriteX
         * @param spriteY
         */
        public Animation(float framePeriod, Texture spriteSheet, int spriteX, int spriteY) {
            frames = new ArrayList<>();
            activeFrames = new ArrayList<>();
            animationTimer = new Clock();

            int countX = spriteSheet.getSize().x / spriteX;
            int countY = spriteSheet.getSize().y / spriteY;

            for (int i = 0; i < countY; i++) {
                for (int j = 0; j < countX; j++) {
                    IntRect r = new IntRect(j * spriteX, i * spriteY, spriteX, spriteY);
                    frames.add(r);
                }
            }

            currentIndex = 0;
            changeAnimation(0, frames.size() - 1, framePeriod);
        }

        /**
         * this method switches between animations when called
         *
         * @param startFrame
         * @param endFrame
         * @param period
         */
        public void changeAnimation(int startFrame, int endFrame, float period) {
            currentIndex = 0;
            framePeriod = period;
            activeFrames.clear();
            for (int i = startFrame; i <= endFrame; i++) {
                activeFrames.add(frames.get(i));
            }
        }

        /**
         * this method gets the next frame of animation
         *
         * @return
         */
        public IntRect getNext() {
            if (animationTimer.getElapsedTime().asSeconds() > (currentIndex + 1) * framePeriod) {
                int frame = (currentIndex + 1) % activeFrames.size();
                if (frame == 0) {
                    animationTimer.restart();
                }
                currentIndex = frame;
            }
            return activeFrames.get(currentIndex);
        }
    }
}
