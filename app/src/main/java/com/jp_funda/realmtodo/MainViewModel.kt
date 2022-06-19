package com.jp_funda.realmtodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    private val _title = MutableLiveData("")
    val title: LiveData<String> = _title

    private val _description = MutableLiveData("")
    val description: LiveData<String> = _description

    fun setTitle(value: String) {
        _title.value = value
    }

    fun setDescription(value: String) {
        _description.value = value
    }

    fun clearTitleAndDescription() {
        _title.value = ""
        _description.value = ""
    }

    fun refreshTodos() {
        viewModelScope.launch {
            _todos.value = todoRepository.getAllTodos()
        }
    }

    fun addTodo() {
        viewModelScope.launch {
            val todo = Todo()
            todo.title = _title.value ?: ""
            todo.description = _description.value ?: ""
            todoRepository.createTodo(todo)
            clearTitleAndDescription()
            refreshTodos()
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.createTodo(todo)
            refreshTodos()
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
            refreshTodos()
        }
    }
}