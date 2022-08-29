package com.BowesGaming.Components.HudElements;

import com.BowesGaming.Components.Entity;
import com.BowesGaming.Loaders.Cnst;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Loaders.StaticHelper;
import com.BowesGaming.Events.ViewMoveEvent;
import org.jsfml.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Icon extends Entity implements ViewMoveEvent {
    public List<String> animationLabels = new ArrayList<>();
    public List<Integer> animationBoundaries = new ArrayList<>();
    public String currentAnimationLabel;

    public Icon( String texture ) {
        Texture iconTexture = StaticHelper.getTextureFromPath(texture);
        setTexture( iconTexture );
        setDrawn(true);
        setEntityAnimation(new Animation(0.1f, iconTexture, 16, 16));
        scale(Cnst.entityScale, Cnst.entityScale);
        setPosition(Cnst.resX /2, Cnst.resY /2);
        setTextureRect( getEntityAnimation().getNext() );
    }

    public void createAnimationState(String label, int start, int end) {
        animationLabels.add(label);
        animationBoundaries.add(start);
        animationBoundaries.add(end);
    }

    public void setAnimationState(String label, float period) {
        int position = animationLabels.indexOf(label);
        currentAnimationLabel = label;
        getEntityAnimation().changeAnimation(animationBoundaries.get(position*2), animationBoundaries.get((position*2)+1), period);
    }

    @Override
    public void onViewMove(int viewMoveAmount) {
        move(viewMoveAmount, 0);
    }

    @Override
    public void onScreenUpdate() {
        Engine.get().draw(this);
    }
}
