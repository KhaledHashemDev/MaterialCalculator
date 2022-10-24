package com.example.materialcalculator.domain


//to separate different parenthesis parts like numbers, operation symbols and numbers
sealed interface ExpressionPart {
    data class Number(val number: Double): ExpressionPart
    data class Op(val operation: Operation): ExpressionPart
    data class Parentheses(val type:  ParenthesesType): ExpressionPart

//sealed interface for opened and closed parenthesis
sealed interface ParenthesesType {
    object Opening: ParenthesesType
    object Closing: ParenthesesType

}

}