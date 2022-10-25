package com.example.materialcalculator.domain

import java.lang.RuntimeException


//3+(4*3) - 5
/**
 * Uses the following grammar; (% is percentage not modulo)
 * expression :	term | term + term | term − term
 * term :		factor | factor * factor | factor / factor | factor % factor
 * factor : 	number | ( expression ) | + factor | − factor
 */
class ExpressionEvaluator(
    private val expression: List<ExpressionPart>
) {

    //evaluates/calculates our expression and returns a double amount
    fun evaluate(): Double {
        return evalExpression(expression).value //final accumulated value
    }

    //evaluating term - ex: 3*4
    private fun evalTerm(expression: List<ExpressionPart>): ExpressionResult {
        val result = evalFactor(expression) //term is always a factor -> 3
        var remaining =  result.remainingExpression //-> * 4
        var sum = result.value //-> 3
        while(true){

            when(remaining.firstOrNull()) { //first character of the remaining expression

                ExpressionPart.Op(Operation.MULTIPLY) -> { //if it's multiply
                  val factor = evalFactor(remaining.drop(1))//read another factor
                    sum *=  factor.value //-> 3 * 4 (4 is factor.value)
                    remaining = factor.remainingExpression ///might be none ?
                }

                ExpressionPart.Op(Operation.DIVIDE) -> { //if it's multiply
                    val factor = evalFactor(remaining.drop(1))//read another factor
                    sum /=  factor.value
                    remaining = factor.remainingExpression
                }

                ExpressionPart.Op(Operation.PERCENT) -> { //if it's multiply
                    val factor = evalFactor(remaining.drop(1))//read another factor
                    sum *=  (factor.value / 100.0)
                    remaining = factor.remainingExpression
                }
                  else -> return ExpressionResult(remaining, sum)

            }
        }
    }

    private fun evalExpression(expression: List<ExpressionPart>): ExpressionResult {
        val result = evalTerm(expression) //term is always a factor -> 3
        var remaining =  result.remainingExpression //
        var sum = result.value //
        while(true){

            when(remaining.firstOrNull()) { //first character of the remaining expression

                ExpressionPart.Op(Operation.ADD) -> {
                    val term = evalTerm(remaining.drop(1))
                    sum +=  term.value //-> 3 * 4 (4 is factor.value)
                    remaining = term.remainingExpression ///might be none ?
                }

                ExpressionPart.Op(Operation.SUBTRACT) -> {
                    val term = evalTerm(remaining.drop(1))//read another factor
                    sum -=  term.value
                    remaining = term.remainingExpression
                }

                else -> return ExpressionResult(remaining, sum)

            }
        }
    }

    /* -3 +
    WE WILL HAVE FUNCTIONS TO EVALUATE AN EXPRESSION, TERM, AND A FACTOR
    A factor is either a number or an expression in parentheses
    e.g. 5.0, -7.5, -(3+4*5)
    but NOT something like 3 * 5, 4 + 5
     */

    //1st helper function (evaluates factor) - takes a list of Expression Part and returns Expression Result
    private fun evalFactor(expression: List<ExpressionPart>) : ExpressionResult {
        /**
         * Takes our current expression and ensures that at the current point where we
         * are evaluating, that this fits in this specific schema
         */
    return when(val part = expression.firstOrNull()){//we now take a look at the first character of our expression
             ExpressionPart.Op(Operation.ADD) -> { //if its add, as soon as we read an add operation
                 evalFactor(expression.drop(1))
             }
        ExpressionPart.Op(Operation.SUBTRACT) -> { //if its subtract, as soon as we read an subtract operation

            //we need a run here, everytime we read something from our actual expression,
            // , like a plus or a minus. we want to evaluate the next piece/character/term..
            //and that's why we DROP that very first character (+ or -, for example)
            evalFactor(expression.drop(1)).run{
                ExpressionResult(remainingExpression, -value)//after dropping the first character
                //of our expression (-), take that as the remaining expression
            }
        }

        //if the first part is a parenthesis, then looking at the factor, what's inside
        //is going to be an expression, so we don't actually evaluate factor after, we evaluate expression
        ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Opening) -> {
                evalExpression(expression.drop(1)).run{
                    //we drop a 1 again for the remaining expression
                    ExpressionResult(remainingExpression.drop(1), value)
                }
        }
        ExpressionPart.Op(Operation.PERCENT) -> evalTerm(expression.drop(1))
        //we dont need a recursive call for a number since it's just a number
        is ExpressionPart.Number -> ExpressionResult(
            remainingExpression = expression.drop(1),
            value = part.number
        )
        else -> throw RuntimeException("Invalid Part")
        }
    }
    //-3 + (4*3)
    //when we read an add operation in our expression, if we take a look in our factor row, w
    // we can see that a factor can be a plus factor, so the format in the end, for our factor
    //for the example mentioned above, is +(4*3), soo that's where the recursive call is applicable.
    //now in this case we will be parsing parenthesis, when we take look at our factor table,
    //we can see a factor can also be parenthesis with an expression inside, so that will be a function
    //where we will have a function to evaluate an expression, a term, and a factor and so on
    //and that's where all those recursive calls are coming from.



     //all our helper functions will return Expression Result
    //a simple data class (helper class) - a recursive parser that accumulates the actual evaluation result
    data class ExpressionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double
    )
}