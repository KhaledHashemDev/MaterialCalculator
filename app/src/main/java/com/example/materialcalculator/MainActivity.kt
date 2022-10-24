package com.example.materialcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.materialcalculator.ui.theme.MaterialCalculatorTheme

/**
 * We will be using a master-staging-development branch configuration
 * Staging branch will be for a specific app version, will be done once we get to the staging
 * part once app is developed and is ready for production.
 * First we want to create the user parser
 * business logic -> expression parser, expression evaluator - takes the parsed expression
 * and calculates the result, Expression writer - responsible to ensure that if the user wants to enter
 * some kind of symbol or number, that they can do that, prevents two/three divisions after another
 * for example also.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialCalculatorTheme {

            }
        }
    }
}

