package com.dmitry.pisarevskiy.calc

import com.dmitry.pisarevskiy.calc.Logic.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {
    val logic = Logic()


    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, logic.result("2+2").toInt())
    }

    @Test
    fun multiplying_isNotCorrect() {
        Assert.assertNotEquals(6, logic.result("2*2").toInt())
    }

    @Test
    fun arrayResult_isCorrect() {
        val answers = arrayOf(2,6,21)
        val calculations = arrayOf(logic.result("8-2*3").toInt(),logic.result("6*7-6*6").toInt(), logic.result("7*(4-1)").toInt())
        Assert.assertArrayEquals(answers,calculations)
    }

    @Test
    fun dividing_isCorredt() {
        Assert.assertSame(3, logic.result("9/3").toInt())
    }
}
