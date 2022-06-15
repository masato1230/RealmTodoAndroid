package com.jp_funda.realmtodo

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import javax.inject.Inject

class TodoRepository @Inject constructor() {

    private fun getRealmInstance(): Realm {
        val config = RealmConfiguration.Builder(schema = setOf(Todo::class))
            .build()
        return Realm.open(config)
    }

    /** Create. */
    suspend fun createTodo(todo: Todo) {
        getRealmInstance().apply {
            write { copyToRealm(todo) }
        }.close()
    }

    /** Read. */
    suspend fun getAllTodo(): RealmResults<Todo> {
        getRealmInstance().apply {
            val todos = query<Todo>().find()
            close()
            return todos
        }
    }

    /** Update. */
    suspend fun updateTodo(todo: Todo) {
        getRealmInstance().apply {
            write {
                query<Todo>("id == $0", todo.id).find().first().apply {
                    title = todo.title
                    description = todo.description
                }
            }
        }.close()
    }

    /** Delete. */
    suspend fun deleteTodo(todo: Todo) {
        getRealmInstance().apply {
            write {
                delete(query<Todo>("id == $0", todo.id).find())
            }
        }.close()
    }
}