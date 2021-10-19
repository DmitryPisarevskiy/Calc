package com.dmitry.pisarevskiy.calc

import com.dmitry.pisarevskiy.calc.Logic.*
import org.junit.Assert
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, result("2+2").toInt())
    }

    @Test
    fun multiplying_isNotCorrect() {
        Assert.assertNotEquals(6, result("2*2").toInt())
    }

    @Test
    fun arrayResult_isCorrect() {
        val answers = arrayOf(2,6,21)
        val calculations = arrayOf(result("8-2*3").toInt(),result("6*7-6*6").toInt(), result("7*(4-1)").toInt())
        Assert.assertArrayEquals(answers,calculations)
    }

    @Test
    fun dividing_isCorredt() {
        Assert.assertSame(3, result("9/3").toInt())
    }
}
