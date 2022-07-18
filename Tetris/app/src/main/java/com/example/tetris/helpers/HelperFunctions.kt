package com.example.tetris.helpers

fun array2dByte(sizeOuter: Int, sizeInner: Int): Array<ByteArray>
        = Array(sizeOuter){ ByteArray(sizeInner) }