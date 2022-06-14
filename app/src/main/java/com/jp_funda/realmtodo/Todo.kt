package com.jp_funda.realmtodo

import io.realm.kotlin.types.RealmObject

class Todo(
    var title: String,
    var description: String,
) : RealmObject