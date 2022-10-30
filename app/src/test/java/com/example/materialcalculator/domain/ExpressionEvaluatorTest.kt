package com.example.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class
ExpressionEvaluatorTest {

    private lateinit var evaluator: ExpressionEvaluator

    @Test //backticks can give real names to our tests, describes our tests.
    fun `Simple expression is properly evaluated`(){

        //Structure of all unit tests - 1) GIVEN (input)
        evaluator = ExpressionEvaluator(listOf(
        ExpressionPart.Number(4.0),
        ExpressionPart.Op(Operation.ADD),
        ExpressionPart.Number(5.0),
        ExpressionPart.Op(Operation.SUBTRACT),
        ExpressionPart.Number(3.0),
        ExpressionPart.Op(Operation.MULTIPLY),
        ExpressionPart.Number(5.0),
        ExpressionPart.Op(Operation.DIVIDE),
        ExpressionPart.Number(3.0),
        ))

        //2. DO SOMETHING WITH WHAT'S GIVEN (returns an expression part list)
        val actual = evaluator.evaluate()

        //3. ASSERT THAT THE EXPECTED == ACTUAL
        val expected = 4.00

        //assertion that comes from truth library
        assertThat(expected).isEqualTo(actual)
    }

    @Test //backticks can give real names to our tests, describes our tests.
    fun `Simple expression with subtract is properly evaluated`(){

        //Structure of all unit tests - 1) GIVEN (input)
        evaluator = ExpressionEvaluator(listOf(
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
        ))

        //2. DO SOMETHING WITH WHAT'S GIVEN (returns an expression part list)
        val actual = evaluator.evaluate()

        //3. ASSERT THAT THE EXPECTED == ACTUAL
        val expected = -4.00

        //assertion that comes from truth library
        assertThat(expected).isEqualTo(actual)
    }

    @Test //backticks can give real names to our tests, describes our tests.
    fun `Expression with decimals is properly evaluated`(){

        //Structure of all unit tests - 1) GIVEN (input)
        evaluator = ExpressionEvaluator(listOf(
            ExpressionPart.Number(4.5),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.5),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.5),
        ))

        //2. DO SOMETHING WITH WHAT'S GIVEN (returns an expression part list)
        val actual = evaluator.evaluate()

        //3. ASSERT THAT THE EXPECTED == ACTUAL
        val expected = 4.5

        //assertion that comes from truth library
        assertThat(expected).isEqualTo(actual)
    }

    //includes another way of following the three steps of unit testing
    @Test
    fun `Simple equation with parentheses properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Opening),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Parentheses(ExpressionPart.ParenthesesType.Closing),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(4.0),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(6.5)
    }


    //you can also create more tests, more complex expression with parenthesis,
    //with nested parenthesis

}