package com.BowesGaming.Components;

import com.BowesGaming.Events.ViewMoveEvent;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.ScreenUpdateEvent;
import org.jsfml.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public final class Platform extends Entity implements ScreenUpdateEvent, ViewMoveEvent {
    private List<Entity> blocks;
    private int levelLength;
    private static final Platform platformInstance = new Platform();

    public static Platform get() {
        return platformInstance;
    }

    private Platform() {
    }

//    public void create(int length, int height) {
//        blocks = new ArrayList<>();
//        int numberOfPlatforms = length / Constant.platformWidth;
//        for (int platCount = 0; platCount < numberOfPlatforms; platCount++) {
//            Entity platform = new Entity();
//            platform.setPosition(platCount*Constant.platformWidth*Constant.entityScale, height);
//            platform.setScale(Constant.entityScale, Constant.entityScPlayer.get().jumpsLeft++;ale);
//            blocks.add( platform );
//        }
//        levelLength = blocks.size()* Constant.platformWidth*Constant.entityScale;
//    }

    /**
     * creates the platform
     */
    public void create() {
        blocks = createAmountOfBlocks(Cnst.resX);
    }

    private List<Entity> createAmountOfBlocks(int widthToFill) {
        List<Entity> localBlocks = new ArrayList<>();
        int blocksRequired = (widthToFill / Cnst.platformWidth/2) + 1;
        for (int count = 0; count < blocksRequired; count++) {
            Entity platformBlock = new Entity();
            platformBlock.setPosition(count * Cnst.platformWidth * Cnst.entityScale, Cnst.floorPosition + (16 * Cnst.entityScale));
            platformBlock.setScale(Cnst.entityScale, Cnst.entityScale);
            localBlocks.add(platformBlock);
        }
        return localBlocks;
    }

    /**
     * goes through each block and lists it properly
     */
    @Override
    public void onScreenUpdate() {
        for (Entity block : blocks) {
            block.onScreenUpdate();
        }
    }

    /**
     *
     * @param texture
     */
    public void setPlatformTexture(Texture texture) {
        for (Entity block : blocks) {
            block.setTexture(texture);
        }
    }

    /**
     * this method returns length of level when called
     * @return length of the platform in each level
     */
    public int getLevelLength() {
        return levelLength;
    }

    /**
     * make the platform continue moving with the player
     * @param viewMoveAmount
     */
    @Override
    public void onViewMove(int viewMoveAmount) {
        for (int blockIndex = 0; blockIndex < blocks.size(); blockIndex++) {
            Entity block = blocks.get(blockIndex);
            if (block.getGlobalBounds().left < Engine.get().getView().getCenter().x - Cnst.resX /2 - (block.getGlobalBounds().width)) {
                blocks.remove(block);
                Entity finalBlock = blocks.get(blocks.size()-1);
                float finalBlockPosition = finalBlock.getPosition().x + finalBlock.getGlobalBounds().width;
                blocks.add(block);
                block.setPosition(finalBlockPosition, Cnst.floorPosition + (16 * Cnst.entityScale));
                break;
            }
        }
    }
}
