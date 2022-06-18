package com.jp_funda.realmtodo

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.*

class Todo: RealmObject {
    @PrimaryKey var id: UUID = UUID.randomUUID()
    var title: String = ""
    var description: String = ""
}