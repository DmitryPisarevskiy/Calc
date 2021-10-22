package com.dmitry.pisarevskiy.calc

class MainPresenter constructor(private val view: IMainView) {
    fun calculateResult(expression: String){
        view.displayResult(Logic.textResult(expression))
    }
}