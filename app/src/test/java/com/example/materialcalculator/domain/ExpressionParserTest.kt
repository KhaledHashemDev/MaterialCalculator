package com.example.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/*
In this class we will actually put our single test cases,
each test will be a plain function which we will annotate with @Test
in these functions we just have some kind of input that we specify,
we do something with the input, and in the end
we verify if the expected outcome is the actual outcome

WE NEED TO ADD GRADLE DEPENDENCIES FIRST
 */

class ExpressionParserTest {

    //an instance of the class that contains the function we are testing
    private lateinit var parser: ExpressionParser

    @Test //backticks can give real names to our tests, describes our tests.
    fun `Simple expression is properly parsed`(){

         //Structure of all unit tests - 1) GIVEN (input)
        parser = ExpressionParser("3+5-3x4/3")

        //2. DO SOMETHING WITH WHAT'S GIVEN (returns an expression part list)
        val actual = parser.parse()

        //3. ASSERT THAT THE EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
            )
        //assertion that comes from truth library
            assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parenthesis is properly parsed`() {

        //Structure of all unit tests - 1) GIVEN (input)
        parser = ExpressionParser("5-(3x4)")

        //2. DO SOMETHING WITH WHAT'S GIVEN (returns an expression part list)
        val actual = parser.parse()

        //3. ASSERT THAT THE EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Opening),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Closing)
        )
        //assertion that comes from truth library
        assertThat(expected).isEqualTo(actual)

 }

    @Test
    fun `Expression with double is properly parsed`() {

        //Structure of all unit tests - 1) GIVEN (input)
        parser = ExpressionParser("5.5-(3x4)")

        //2. DO SOMETHING WITH WHAT'S GIVEN (returns an expression part list)
        val actual = parser.parse()

        //3. ASSERT THAT THE EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Opening),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Closing)
        )
        //assertion that comes from truth library
        assertThat(expected).isEqualTo(actual)

    }

    @Test
    fun `Expression with a large number is properly parsed`() {

        //Structure of all unit tests - 1) GIVEN (input)
        parser = ExpressionParser("5567485-(3x4)")

        //2. DO SOMETHING WITH WHAT'S GIVEN (returns an expression part list)
        val actual = parser.parse()

        //3. ASSERT THAT THE EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(5567485.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Opening),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Closing)
        )
        //assertion that comes from truth library
        assertThat(expected).isEqualTo(actual)

    }

    @Test
    fun `Expression with complicated parenthesis is properly parsed`() {

        //Structure of all unit tests - 1) GIVEN (input)
        parser = ExpressionParser("5-((3x4)/10000)")

        //2. DO SOMETHING WITH WHAT'S GIVEN (returns an expression part list)
        val actual = parser.parse()

        //3. ASSERT THAT THE EXPECTED == ACTUAL
        val expected = listOf(
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Opening),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Opening),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(4.0),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Closing),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(10000.0),
            ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Closing),

            )
        //assertion that comes from truth library
        assertThat(expected).isEqualTo(actual)

    }
}

//Other tests could include more complex nested parenthesis/calculations
//Other tests can include testing decimal numbers
//and so on