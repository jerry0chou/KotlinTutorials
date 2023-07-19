package annotations

import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class FieldName(val name: String)

interface NameStrategy{
    fun mapTo(name: String): String
}

annotation class MappingStrategy(val kclass: KClass<out NameStrategy>)

object CamelToUnderScore: NameStrategy{
    override fun mapTo(name: String): String {
        return name.uppercase().fold(StringBuilder()){
            acc, c ->
            when{
                c.isUpperCase() -> acc.append('_').append(c.lowercase())
                else-> acc.append(c)
            }
            acc
        }.toString()
    }
}

@MappingStrategy(CamelToUnderScore::class)
data class UserVO(
    val login: String,
    @FieldName("avatar_url")
    val avatarUrl: String,
    @FieldName("html_url")
    var htmlUrl: String
)


data class UserDTO(
    var id: Int,
    var login: String,
    var avatar_url: String,
    var url: String,
    var html_url: String
)

inline fun <reified To : Any> Map<String, Any?>.mapAs(): To {
    return To::class.constructors.firstOrNull()?.let {con->
        con.parameters.map { parameter ->
            println("[$parameter], ${parameter.name}")
            parameter to (this[parameter.name]
                ?: (parameter.annotations.filterIsInstance<FieldName>().firstOrNull()?.name?.let(this::get))
                ?: To::class.findAnnotation<MappingStrategy>()?.kclass?.objectInstance?.mapTo(parameter.name!!)?.let(this::get)
                ?: if (parameter.type.isMarkedNullable) null
                else throw IllegalArgumentException("${parameter.name} is required but missing."))
        }.toMap()
            .let(con::callBy)
    } ?: throw IllegalArgumentException("No primary constructor found for ${To::class.simpleName}")
}
inline fun <reified From: Any, reified To: Any> From.mapAs(): To{
    return From::class.memberProperties.associate { it.name to it.get(this) }.mapAs()
}



fun main() {
    val userDTO = UserDTO(
        0,
        "Bennyhuo",
        "https://avatars2.githubusercontent.com/u/30511713?v=4",
        "https://api.github.com/users/bennyhuo",
        "https://github.com/bennyhuo"
    )

    val userVO: UserVO = userDTO.mapAs()
    println(userVO)
}