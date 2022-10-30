package com.example.materialcalculator.domain

import kotlin.math.exp


/**
 * ensures that the user only enters what they are allowed to enter
 * and that we always have  a valid expression that we feed into our
 * expression parser and then finally into our expression evaluator
 *
 * So the basic feature signature for the initial testing of this class is
 * that we want to a function that will process an action/button
 */


//  +--3-5 -> allowed
//  *3-5 -> not allowed
//
class ExpressionWriter {

    var expression = ""

    fun processAction(action: CalculatorAction) {

        when(action){

            CalculatorAction.Calculate ->{
                val parser = ExpressionParser(prepareForCalculation())
                val evaluator = ExpressionEvaluator(parser.parse())
                expression  = evaluator.evaluate().toString()

            }

            CalculatorAction.Clear -> {
                expression = ""
            }

            CalculatorAction.Decimal -> {
                if(canEnterDecimal()) {
                    expression += "."
                }
            }

            CalculatorAction.Delete -> {  expression = expression.dropLast(1)
            }

            is CalculatorAction.Number -> {
                expression += action.number
            }

            is CalculatorAction.Op -> {
                 if(canEnterOperation(action.operation)){
                     expression += action.operation.symbol
                 }
            }

            CalculatorAction.Parentheses -> {
                processParentheses()
            }
        }
    }

    private fun prepareForCalculation(): String {
        // 3+4-
        val newExpression = expression.takeLastWhile {
            it in "$operationSymbols(."
        }
          if(newExpression.isEmpty()){
             return "0"
          }
        return newExpression
    }

    private fun processParentheses() {
        val openingCount = expression.count{it == '('}
        val closingCount = expression.count {it ==  ')'}
        expression += when {
            expression.isEmpty()  ||
                    expression.last()  in "$operationSymbols(" -> "("
            //(3+4)
            expression.last() in "0123456789)" &&
                    openingCount == closingCount -> return
            //(3+4*(5-5) -> opening parenthesis not equal to closing parenthesis
            else -> ")"
        }
    }

    private fun canEnterDecimal(): Boolean {
        //.6
        if(expression.isEmpty() || expression.last() in "$operationSymbols.()"){
            return false
        }
        //4+5.56 -> takes 5.56
        return !expression.takeLastWhile {
            it in "0123456789."
        }.contains(".")
    }

    //if the user can't enter a specific operation, we simply ignore that button click
    private fun canEnterOperation(operation: Operation): Boolean {
        if(operation in listOf(Operation.ADD, Operation.SUBTRACT)){
             return expression.isEmpty() || expression.last() in "$operationSymbols()0123456789"
        }
        //(*
        return expression.isNotEmpty() || expression.last() in "0123456789"
    }
}