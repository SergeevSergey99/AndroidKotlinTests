package com.example.tetris.models;

import android.graphics.Color;
import android.graphics.Point;

import androidx.annotation.NonNull;

import com.example.tetris.constants.FieldConstants;

import java.util.Random;

public class Block {
    private int shapeIndex;
    private int frameNumber;
    private BlockColor color;
    private Point position;

    private Block(int shapeIndex, BlockColor blockColor) {
        this.frameNumber = 0;
        this.shapeIndex = shapeIndex;
        this.color = blockColor;

        this.position = new Point(FieldConstants.COLUMN_COUNT.getValue() / 2, 0);
    }

    public static Block createBlock() {
        Random random = new Random();
        int shapeIndex = random.nextInt(Shape.values().length);
        BlockColor blockColor = BlockColor.values()[random.nextInt(BlockColor.values().length)];

        Block block = new Block(shapeIndex, blockColor);
        block.position.x = block.position.x - Shape.values()[shapeIndex].getStartPosition();

        return block;
    }

    public static int getColor(byte value) {
        for (BlockColor color : BlockColor.values()) {
            if (value == color.byteValue)
                return color.rgbValue;
        }
        return -1;
    }

    public final void setState(int frame, Point position) {
        this.frameNumber = frame;
        this.position = position;
    }

    @NonNull
    public final byte[][] getShape(int frameNumber) {
        return Shape.values()[shapeIndex].getFrame(frameNumber).as2dByteArray();
    }

    public Point getPosition() {
        return this.position;
    }
    public final int getFrameCount(){
        return Shape.values()[shapeIndex].getFrameCount();
    }

    public final int getFrameNumber() {
        return this.frameNumber;
    }

    public int getColor() {
        return this.color.rgbValue;
    }

    public byte getStaticValue() {
        return this.color.byteValue;
    }

    public enum BlockColor {
        PINK(Color.rgb(255, 105, 180), (byte) 2),
        GREEN(Color.rgb(0, 128, 0), (byte) 3),
        ORANGE(Color.rgb(255, 140, 0), (byte) 4),
        YELLOW(Color.rgb(255, 255, 0), (byte) 5),
        CYAN(Color.rgb(0, 255, 255), (byte) 6);

        BlockColor(int rgb, byte value) {
            rgbValue = rgb;
            byteValue = value;
        }

        private final int rgbValue;
        private final byte byteValue;
    }
}
