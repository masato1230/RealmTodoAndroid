package com.jp_funda.realmtodo

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

class Todo : RealmObject {
    @PrimaryKey
    @NotNull
    var id: UUID = UUID.randomUUID()
    var title: String = ""
    var description: String = ""
}