package com.eogresources.composetestbugrepro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eogresources.composetestbugrepro.ui.theme.ComposeTestBugReproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestBugReproTheme {
                SimpleForm()
            }
        }
    }
}

@Composable
fun SimpleForm() {
    Surface(
        modifier = Modifier.padding(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Dropdown()
            Spacer(modifier = Modifier.size(16.dp))
            TextInput()
        }
    }
}

@Composable
fun Dropdown() {
    var value by remember { mutableStateOf("") }
    var isDropdownOpen by remember { mutableStateOf(false) }
    val items = remember { listOf(
        "First",
        "Second",
        "Third"
    ) }

    Box {
        Button(
            onClick = {
                isDropdownOpen = true
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSecondary
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            contentPadding = PaddingValues(
                top = 0.dp,
                start = 0.dp,
                end = 0.dp,
                bottom = 0.dp
            ),
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = {},
                label = { Text(text = "Input") },
                placeholder = { Text("Choose an item") },
                enabled = false,
                readOnly = true
            )
        }
        DropdownMenu(
            modifier = Modifier.width(IntrinsicSize.Max),
            expanded = isDropdownOpen,
            onDismissRequest = { isDropdownOpen = false }
        ) {
            items.forEach { itemValue ->
                DropdownMenuItem(
                    onClick = {
                        value = itemValue
                        isDropdownOpen = false
                    }
                ) {
                    Text(text = itemValue)
                }
            }
        }
    }
}

@Composable
fun TextInput() {
    val defaultSizeModifier = Modifier.fillMaxWidth()
    var value by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = defaultSizeModifier,
        value = value,
        onValueChange = {
            value = it
        },
        label = { Text("Label") },
        readOnly = false,
        singleLine = false,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    )
}
