package com.example.materialcalculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

// 3+* ->users can not write an asterisk (multiplication) after addition symbol
class ExpressionWriterTest{

private lateinit var writer: ExpressionWriter //class instance

    //since we dont have a constructor for expression writer
    @Before
    fun setUp()  {
        writer = ExpressionWriter()
    }

@Test
fun `Initial parentheses parsed`() {

    //instnace of the function of the class with Calculator Action instance
    writer.processAction(CalculatorAction.Parentheses)
    writer.processAction(CalculatorAction.Number(5))
    writer.processAction(CalculatorAction.Op(Operation.ADD))
    writer.processAction(CalculatorAction.Number(4))
    writer.processAction(CalculatorAction.Parentheses)



    assertThat(writer.expression).isEqualTo("(5+4)")
}

    @Test
    fun `Closing parenthesis at the start not parsed`() {
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Parentheses)

        assertThat(writer.expression).isEqualTo("((")
    }

    @Test
    fun `Parenthesis around a number are parsed`() {
        writer.processAction(CalculatorAction.Parentheses)
        writer.processAction(CalculatorAction.Number(6))
        writer.processAction(CalculatorAction.Parentheses)


        assertThat(writer.expression).isEqualTo("(6)")
    }
    // a lot more we can test
}