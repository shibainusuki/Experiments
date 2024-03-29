package com.example.experiments

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.experiments.jetpackcompose.layout.Dialog
import com.example.experiments.jetpackcompose.sideeffect.SideEffect
import com.example.experiments.kotlinbasic.list.ListCollection
import com.example.experiments.kotlinbasic.runcatching.RunCatchingDemo
import com.example.experiments.ui.theme.ExperimentsTheme
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExperimentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    Dialog().AlertDialogScreen()
                }
            }
        }

        val runCatchingDemo = RunCatchingDemo()

        runCatching {
            runCatchingDemo.doConvertStringToInt("あいうえお")
        }.onSuccess {
            Log.d("テスト", "converted value is $it")
        }.onFailure {
            if(it is IOException){
                Log.d("テスト", "Result is Failure$it")
            }
        }

        //mapの挙動確認
        ListCollection().mapNumListToDoubleNum()
        ListCollection().extractUserIds()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExperimentsTheme {
        //HelloScreen()
        //SideEffect().SideEffectCounter1()
        SideEffect().SideEffectCounter2()
        //SideEffect().SideEffectCounter3()
    }
}


@Composable
fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("タロウ") }
    Log.d("テスト", "name is: $name")

    HelloContent(name = name, onNameChange = { name = it })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Hello, $name",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}