package com.jp_funda.realmtodo

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Inject

class TodoRepository @Inject constructor() {

    private fun getRealmInstance(): Realm {
        val config = RealmConfiguration.Builder(schema = setOf(Todo::class))
            .build()
        return Realm.open(config)
    }
}