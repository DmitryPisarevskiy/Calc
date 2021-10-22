package com.dmitry.pisarevskiy.calc

import com.dmitry.pisarevskiy.calc.Logic*

class MainPresenter constructor(private val view: IMainView) {
    fun calculateResult(expression: String){
        view.displayResult("")
    }
}