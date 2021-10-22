package com.dmitry.pisarevskiy.calc

class MainPresenter constructor(val view: IMainView,
                                val logic: Logic) {
    fun calculateResult(expression: String) {
        view.displayResult(logic.textResult(expression))
    }
}