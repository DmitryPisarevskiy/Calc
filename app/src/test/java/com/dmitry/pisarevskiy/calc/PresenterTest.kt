package com.dmitry.pisarevskiy.calc

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PresenterTest {

    lateinit var presenter: MainPresenter

    @Mock
    lateinit var view: IMainView

    @Mock
    lateinit var logic: Logic

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(logic.textResult(anyString())).thenReturn("7")
        presenter = MainPresenter(view,logic)
    }

    @Test
    fun view_displayResult_callNumber() {
        val exp = "2*2+3"
        presenter.calculateResult(exp)
        Mockito.verify(view, Mockito.times(1)).displayResult(logic.textResult(exp))
    }

    @Test
    fun logic_result_callNumber() {
        val exp = "2*2+3"
        presenter.calculateResult(exp)
        Mockito.verify(logic, Mockito.times(1)).textResult(exp)
    }

    @Test
    fun calculation_isCorrect() {
        val exp = "2*2+3"
        val presenter2 = MainPresenter(view, Logic())
        presenter2.calculateResult(exp)
        Assert.assertEquals(presenter2.logic.textResult(exp) , "7.0")
    }
}