package com.example.tetris.models;

import android.graphics.Color;
import android.graphics.Point;

public class Block {
    private int shapeIndex;
    private int frameNumber;
    private BlockColor color;
    private Point position;

    public enum BlockColor{
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
