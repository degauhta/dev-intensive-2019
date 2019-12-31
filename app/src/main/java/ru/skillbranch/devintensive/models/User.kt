package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?,
    val rating: Int = 0,
    val respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {

    var introBit: String

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")


    init {
        introBit = getIntro()
        println(
            "It's alive!!! \n${if (lastName === "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName!!!"}"
        )
    }

    private constructor(builder: Builder) : this(
        builder.id,
        builder.firstName,
        builder.lastName,
        builder.avatar,
        builder.rating,
        builder.respect,
        builder.lastVisit,
        builder.isOnline
    )

    class Builder {
        var id: String = ""
            private set
        var firstName: String? = null
            private set
        var lastName: String? = null
            private set
        var avatar: String? = null
            private set
        var rating: Int = 0
            private set
        var respect: Int = 0
            private set
        var lastVisit: Date? = null
            private set
        var isOnline: Boolean = false
            private set

        fun id(id: String) = apply { this.id = id }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun avatar(avatar: String) = apply { this.avatar = avatar }
        fun rating(rating: Int) = apply { this.rating = rating }
        fun respect(respect: Int) = apply { this.respect = respect }
        fun lastVisit(lastVisit: Date) = apply { this.lastVisit = lastVisit }
        fun isOnline(isOnline: Boolean) = apply { this.isOnline = isOnline }
        fun build() = User(this)
    }

    private fun getIntro() = """
        tu tu tu tuuuuu !!!
        tu tu tu tuuuuuuuuu ...
        
        tu tu tu tuuuuu !!!
        tu tu tu tuuuuuuuuu ...
        
        ${"\n\n\n"}

        $firstName $lastName
        
    """.trimIndent()

    fun printMe(): Unit = println(
        """
            id:$id
            firstName:$firstName
            lastName:$lastName
            avatar:$avatar
            rating:$rating
            respect:$respect
            lastVisit:$lastVisit
            isOnline:$isOnline
        """.trimIndent()
    )

    companion object Factory {

        private var lastId: Int = -1

        fun makeUser(fullName: String?): User {
            lastId++
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}