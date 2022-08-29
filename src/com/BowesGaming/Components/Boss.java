package com.BowesGaming.Components;

import com.BowesGaming.Components.HudElements.ScoreCounter;
import com.BowesGaming.Events.RefreshEvent;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.EntityGenerator;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.AnimationEvent;
import com.BowesGaming.Events.ViewMoveEvent;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;

public class Boss implements ViewMoveEvent, AnimationEvent, RefreshEvent {
    private static BossHead head = new BossHead();
    private static BossBody body = new BossBody();
    private static Boss INSTANCE = new Boss();
    private static Clock movementEffectClock = new Clock();
    private static Clock attackingClock = new Clock();
    private static Clock cooldownClock = new Clock();
    private static boolean isActive = false;
    private static int difficultyLevel = 1;
    private static int healthRemaining = 100;
    private static boolean isAttacking = false;
    private static float derivative = 0;
    private static boolean relocated = false;
    private static float headRotation = 0;
    private static Clock collisionTimer = new Clock();
    private static boolean hit = false;

    /**
     * gets the actions that the head and body of boss should do from engine
     */
    public Boss() {
        Engine.get().queueActionToAdd(head);
        Engine.get().queueActionToAdd(body);
    }

    public static int getHealthRemaining() {
        return healthRemaining;
    }

    public static void setHealthRemaining(int healthRemaining) {
        Boss.healthRemaining = healthRemaining;
    }

    /**
     * sets the position of the head and body of the boss on screen
     * @param v1
     * @param v2
     */
    public void setPosition(float v1, float v2) {
        head.setPosition(v1, v2+30);
        body.setPosition(v1 + head.getGlobalBounds().width, v2);
    }

    /**
     * increases difficulty level by 1
     */
    public static void increaseDifficulty() {
        difficultyLevel += 1;
    }

    /**
     *
     * @param val
     */
    public static void setIsActive(boolean val) {
        isActive = val;
    }

    /**
     * moves the head and body of the boss respectively
     * @param v1
     * @param v2
     */
    public static void move(float v1, float v2) {
        head.move(v1, v2);
        body.move(v1, v2);
    }

    /**
     *
     * @return position of the head of the boss
     */
    public static Vector2f getPosition() {
        return head.getPosition();
    }

    /**
     *
     * @return returns the head for boss
     */
    public static BossHead getHead() {
        return head;
    }

    /**
     *
     * @return returns the body for boss
     */
    public static BossBody getBody() {
        return body;
    }

    /**
     * moves the head and body without changing the y axis
     * @param viewMoveAmount
     */
    @Override
    public void onViewMove(int viewMoveAmount) {
        head.move(viewMoveAmount, 0);
        body.move(viewMoveAmount, 0);
    }

    public static Boss get() {
        return INSTANCE;
    }

    /**
     * changes frames for head and body
     */
    @Override
    public void onAnimationEvent() {
        head.onAnimationEvent();
        body.onAnimationEvent();
    }

    /**
     *
     */
    @Override
    public void refresh() {
        head.setCollisionBoxPosition();
        body.setCollisionBoxPosition();
        if (isActive && !isAttacking) {
            if (cooldownClock.getElapsedTime().asSeconds() > 5) {
                int action = StaticHelper.getRandom(0, difficultyLevel + 1);
                switch (action) {
                    case 0:
                        isAttacking = true;
                        derivative = (Player.get().getPosition().y - getPosition().y) / (getPosition().x - Player.get().getPosition().x);
                        break;
                    case 1:
                        Enemy enemy = EntityGenerator.generateEnemy();
                        Engine.get().spawnEnemy(enemy);
                        break;
                }
                cooldownClock.restart();
            }
        }
        if (isAttacking) {
            if (attackingClock.getElapsedTime().asSeconds() > 0.016) {
                move(-30, derivative * 30);
                attackingClock.restart();
            }
            float lowerScreenBoundary = Engine.get().getView().getCenter().x - Engine.get().getView().getSize().x/2;
            float upperScreenBoundary = Engine.get().getView().getCenter().x + Engine.get().getView().getSize().x/2;
            if (getPosition().x < lowerScreenBoundary - 300 && !relocated) {
                setPosition(upperScreenBoundary + 300, Cnst.resY * 1/4);
                derivative = 0;
                relocated = true;
            } else if (getPosition().x < upperScreenBoundary - body.getGlobalBounds().width - head.getGlobalBounds().width - 100 && relocated) {
                isAttacking = false;
                attackingClock.restart();
                relocated = false;
            }
        } else {
            if (movementEffectClock.getElapsedTime().asSeconds() <= (Math.PI)) {
                float val = 3 * (float) Math.sin(movementEffectClock.getElapsedTime().asSeconds());
                move(val ,val);
            } else if (movementEffectClock.getElapsedTime().asSeconds() <= (Math.PI*2)) {
                float val = 3 * (float) Math.sin(movementEffectClock.getElapsedTime().asSeconds());
                move(val, val);
            } else {
                movementEffectClock.restart();
            }
            if (getHealthRemaining() < 0 && !isAttacking) {
                move(-5, -5);
                if (getPosition().y < - 200) {
                    Engine.get().currentScene.forceSceneSwap = true;
                    return;
                }
            } else {
                float xDisp = head.getPosition().x - Player.get().getPosition().x;
                float yDisp = Player.get().getPosition().y - head.getPosition().y;
                headRotation = 360 - (float) (Math.tan(yDisp/xDisp) * (2*Math.PI)) - 15;
                head.setRotation(headRotation);
            }
        }
        if (collisionTimer.getElapsedTime().asSeconds() > 1 && hit) {
            hit = false;
            head.getEntityAnimation().changeAnimation(0, 0, 10);
            body.getEntityAnimation().changeAnimation(0, 3, 0.1f);
        }
    }

    public static class BossHead extends Entity {
        private BossHead() {
            String texturePath = "Enemies/BigGooseHead.png";
            setEntityType(EntityType.BossHead);
            Texture texture = StaticHelper.getTextureFromPath(texturePath);
            setTexture(texture);
            setEntityAnimation(new Animation(0.5f, texture, 16, 16));
            getEntityAnimation().changeAnimation(0, 0, 10);
            scale(Cnst.entityScale, Cnst.entityScale);
            setTextureRect(getEntityAnimation().getNext());
            setCollisionBox(StaticHelper.getCollisionBox( texturePath ));
            setCollisionBoxPosition();
        }

        /**
         * this method is called for collision with boss's head
         * @param collidedEntity
         */
        @Override
        public void onCollision(Entity collidedEntity) {
            if (!hit) {
                switch (collidedEntity.getEntityType()) {
                    case Player:
                        Engine.get().livesLost();
                        Audio playerHitSound = new Audio("Hit5.wav");
                        playerHitSound.play();
                        hit = true;
                        break;
                    case FriendlyProjectile:
                        setHealthRemaining(getHealthRemaining() - 10);
                        ScoreCounter.score += (200 * Engine.get().currentScene.currentLevel.scoreMultiplier);
                        Audio projectileHitSound = new Audio("Hit5.wav");
                        projectileHitSound.play();
                        System.out.println("Boss Health: " + getHealthRemaining());
                        hit = true;
                        collisionTimer.restart();
                        collidedEntity.delete();
                        head.getEntityAnimation().changeAnimation(1, 1, 10);
                        body.getEntityAnimation().changeAnimation(4, 7, 0.1f);
                        break;
                }
            }
        }
    }
    public static class BossBody extends Entity {

        private BossBody() {
            String texturePath = "Enemies/BigGooseBody.png";
            setEntityType(EntityType.BossBody);
            Texture texture = StaticHelper.getTextureFromPath(texturePath);
            setTexture(texture);
            setEntityAnimation(new Animation(0.5f, texture, 35, 20));
            getEntityAnimation().changeAnimation(0, 3, 0.1f);
            scale(Cnst.entityScale, Cnst.entityScale);
            setTextureRect( getEntityAnimation().getNext() );
            setCollisionBox(StaticHelper.getCollisionBox( texturePath ));
            setCollisionBoxPosition();
        }

        /**
         * this method is called for collision with boss's head
         * @param collidedEntity
         */
        @Override
        public void onCollision(Entity collidedEntity) {
            if (!hit) {
                switch (collidedEntity.getEntityType()) {
                    case Player:
                        Engine.get().livesLost();
                        Audio playerHitSound = new Audio("Hit5.wav");
                        playerHitSound.play();
                        break;
                    case FriendlyProjectile:
                        Audio projectileHitSound = new Audio("Hit5.wav");
                        projectileHitSound.play();
                        setHealthRemaining(getHealthRemaining() - 5);
                        ScoreCounter.score += (100 * Engine.get().currentScene.currentLevel.scoreMultiplier);
                        System.out.println("Boss Health: " + getHealthRemaining());
                        hit = true;
                        collisionTimer.restart();
                        collidedEntity.delete();
                        head.getEntityAnimation().changeAnimation(1, 1, 10);
                        body.getEntityAnimation().changeAnimation(4, 7, 0.5f);
                        break;
                }
            }
        }
    }
}
