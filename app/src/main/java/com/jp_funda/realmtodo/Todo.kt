package com.jp_funda.realmtodo

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

class Todo : RealmObject {
    @PrimaryKey
    @NotNull
    var id: String = UUID.randomUUID().toString()
    var title: String = ""
    var description: String = ""
}