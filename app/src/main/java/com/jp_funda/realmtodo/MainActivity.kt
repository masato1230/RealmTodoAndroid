package com.jp_funda.realmtodo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jp_funda.realmtodo.ui.theme.RealmTodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealmTodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainContent() {
    val isShowDialog = remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { isShowDialog.value = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "追加ボタン")
            }
        }
    ) {
        if (isShowDialog.value) {
            EditDialog(isShowDialog)
        }
    }
}

@Composable
fun EditDialog(isShowDialog: MutableState<Boolean>) {
    AlertDialog(
        onDismissRequest = { isShowDialog.value = false },
        title = { Text(text = "Todo新規作成") },
        text = {
            Column {
                Text(text = "タイトル")
                TextField(value = "", onValueChange = { /* Todo */ })
                Text(text = "詳細")
                TextField(value = "", onValueChange = { /* Todo */ })
            }
        },
        buttons = {
            Row(
                modifier = Modifier.padding(all = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = { isShowDialog.value = false },
                ) {
                    Text(text = "キャンセル")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    modifier = Modifier.width(120.dp),
                    onClick = {
                        isShowDialog.value = false
                        /* TODO todoの追加 */
                    },
                ) {
                    Text(text = "OK")
                }
            }
        }
    )
}