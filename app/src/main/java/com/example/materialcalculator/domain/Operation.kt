package com.example.materialcalculator.domain


/**
 *Represents our different operations that we have to use our calculator
 * like addition,subtraction, multiplication, division and percentage operation.
 */
enum class Operation(val symbol: Char) {
    //to distinguish  different symbols for our calculator operations
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    PERCENT('%'),
}

//returns all those symbols combined in a string to check if a character
// is one of our symbols- provides the following expression "+-x/%"
//.values returns an array of all our characters(operations and then we join it to string
val operationSymbols = Operation.values().map {it.symbol}.joinToString("") //with no spaces

//function to get an instance of our operation from our string symbol
fun operationFromSymbol(symbol: Char): Operation{
    return Operation.values().find { it.symbol == symbol}
        ?: throw IllegalArgumentException("Invalid symbol")
}

/**
 * Sealed vs Enum classes vs Sealed Onterface:
 * Used when you want to group different related options together in a class.
 *
 * Sealed class- an abstract class where all the children that inherit from this class
 * are known at compile time, no other modules can extend that class
 *
 *The most basic use case for enum classes is the implementation of type-safe enums:
enum class Direction {
NORTH, SOUTH, WEST, EAST
}
Each enum constant is an object. Enum constants are separated by commas.
Since each enum is an instance of the enum class, it can be initialized as:
enum class Color(val rgb: Int) {
RED(0xFF0000),
GREEN(0x00FF00),
BLUE(0x0000FF)
}
 *
 */

//like grouping some HTTP errors together
//Some Http error 404 - not found
//401 - unauthorized
//403 - forbidden