package com.example.tetris.models

import com.example.tetris.helpers.array2dByte

class Frame(private val width: Int) {
    val data = ArrayList<ByteArray>()

    fun addRow(byteStr: String): Frame {
        val row = ByteArray(byteStr.length)

        for (index in byteStr.indices){
            row[index] = "${byteStr[index]}".toByte()
        }

        data.add(row)
        return this
    }

    fun as2dByteArray(): Array<ByteArray> {
        val bytes = array2dByte(data.size, width)
        return data.toArray(bytes)
    }
}