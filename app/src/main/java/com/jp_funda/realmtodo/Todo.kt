package com.jp_funda.realmtodo

import io.realm.kotlin.types.RealmObject
import java.util.*

class Todo(
    var id: UUID? = UUID.randomUUID(),
    var title: String,
    var description: String,
) : RealmObject