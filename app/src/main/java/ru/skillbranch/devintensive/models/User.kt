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