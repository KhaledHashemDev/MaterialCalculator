package com.example.materialcalculator.domain

class ExpressionParser(
    private val calculation: String)

{
    //the function we want to test in this expression parser
    //doesn't take any arguments since we already provided our calculation
    //string in the constructor of the class
    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()

        //iterating over the calculation string
        var i = 0
        while(i < calculation.length) {
            val curChar = calculation[i]
            when {
                // when current character is part of the operationSymbols
                curChar in operationSymbols -> {
                    result.add(
                        ExpressionPart.Op(operationFromSymbol(curChar))
                    )
                }
                curChar.isDigit() -> {
// create a helper function it will take the current number and the result list
//it will take the current index, where we are currently at in our parser,
//and it will take our immutable list, and it will add the number that is currently
                    //parsed to our result list as an expression part, and after that it  will return the new index
                    i = parseNumber(i, result)
                    continue //go on in this loop without increasing i again
                }

                curChar in "()" -> { //if curr character is a parenthesis
                    parseParenthesis(curChar, result) //function to parse parenthesis
                }
            }
            i++
        }
        return result
    }

    private fun parseNumber(startingIndex: Int, result: MutableList<ExpressionPart>): Int {
        var i = startingIndex
        val numberAsString = buildString {
//as long as the current character that we fetch here in calculation[i] is any
 //of these characters (digit or period), we append this character into our string and increase i by one
             while(i < calculation.length && calculation[i] in "0123456789."){
                 append(calculation[i]) // append it to our string
            i++
        }
    }
        result.add(ExpressionPart.Number(numberAsString.toDouble())) //add string(double) to the changing list of our ExpressionPart
        return i
    }

    private fun parseParenthesis(curChar: Char, result: MutableList<ExpressionPart>) {
        result.add(
            ExpressionPart.Parentheses(
                type = when(curChar) {
                     '(' -> ExpressionPart.ParenthesesType.Opening
                      ')' -> ExpressionPart.ParenthesesType.Closing
                    else -> throw IllegalArgumentException("Invalid parenthesis type")
                }
            )
        )
    }
}