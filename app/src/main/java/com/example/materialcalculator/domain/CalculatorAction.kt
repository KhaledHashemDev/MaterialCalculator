package com.example.materialcalculator.domain


/**
 * These are like buttons/actions ?
 */
sealed interface CalculatorAction {
    data class Number(val number: Int): CalculatorAction
    data class Op(val operation: Operation): CalculatorAction
    object Clear: CalculatorAction
    object Delete: CalculatorAction
    object Parentheses: CalculatorAction
    object Calculate: CalculatorAction //evaluates expression
    object Decimal: CalculatorAction
}