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

    private fun refreshTodos() {
        viewModelScope.launch {
            _todos.value = todoRepository.getAllTodo()
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.createTodo(todo)
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