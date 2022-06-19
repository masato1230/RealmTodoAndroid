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

    private var updatingTodo: Todo? = null

    fun setUpdatingTodo(todo: Todo) {
        updatingTodo = todo
        _title.value = todo.title
        _description.value = todo.description
    }

    val isUpdating: Boolean
        get() {
            return updatingTodo != null
        }

    fun setTitle(value: String) {
        _title.value = value
    }

    fun setDescription(value: String) {
        _description.value = value
    }

    fun clearData() {
        updatingTodo = null
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
            clearData()
            refreshTodos()
        }
    }

    // 更新
    fun updateTodo() {
        viewModelScope.launch {
            updatingTodo!!.title = _title.value ?: ""
            updatingTodo!!.description = _description.value ?: ""
            todoRepository.updateTodo(updatingTodo!!)
            clearData()
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