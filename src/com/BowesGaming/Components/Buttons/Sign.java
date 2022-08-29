package com.BowesGaming.Components.Buttons;

import com.BowesGaming.Components.Entity;
import com.BowesGaming.Engine.Engine;
import com.BowesGaming.Events.ButtonSelectedEvent;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

public class Sign extends Entity implements ButtonSelectedEvent {
    private Vector2i topLeft;
    private Vector2i bottomRight;

    private Text text;
    private int textSize = 0;

    public Sign(Texture signTexture, String signText, Font signFont, int signTextSize, int signPositionX, int signPositionY) {
        setTexture(signTexture);
        signPositionX -= getTexture().getSize().x/2;
        signPositionY -= getTexture().getSize().y/2;

        setPosition(signPositionX, signPositionY);
        this.setTextSize(signTextSize);
        setTopLeft(new Vector2i(signPositionX, signPositionY));
        setBottomRight(new Vector2i(signPositionX + getTexture().getSize().x, signPositionY + getTexture().getSize().y));
        setText(new Text(signText, signFont, signTextSize));
        centreText();
    }

    public void centreText() {
        getText().setPosition( (int) (getGlobalBounds().left + (getTexture().getSize().x/2) - (getText().getString().length() * getTextSize())/3.5),(int) (getGlobalBounds().top + (getTexture().getSize().y/2) - (getTextSize() /1.5)));
    }

    public void staticTextChange(String newText) {
        getText().setString(newText);
    }

    public void changeText(String newText) {
        getText().setString(newText);
        centreText();
    }

    @Override
    public void onScreenUpdate() {
        super.onScreenUpdate();
        Engine.get().draw(text);
    }

    public Vector2i getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Vector2i topLeft) {
        this.topLeft = topLeft;
    }

    public Vector2i getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Vector2i bottomRight) {
        this.bottomRight = bottomRight;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    @Override
    public void onButtonSelection(Vector2i vector2i) {

    }
}
