package reflections

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

fun <T: Any> T.deepCopy(): T {
    if(!this::class.isData) return this

    return this::class.primaryConstructor!!.let { primaryConstructor->
        primaryConstructor.parameters.associate { parameter ->
            val value = (this::class as KClass<T>).memberProperties.first { it.name == parameter.name }.get(this)
            println("parameter.type.classifier, ${parameter.type.classifier}")
            if ((parameter.type.classifier as? KClass<*>)?.isData == true)
                parameter to value?.deepCopy()
            else
                parameter to value
        }.let(primaryConstructor::callBy)
    }
}

data class Person(val id: Int, val name: String, val group: Group)
data class Group(val id: Int, val name: String, val location: String)

fun main() {
    val person = Person(0, "jerry", Group(0, "Freedom", "Perfect"))
    val person2 = person.copy()
    val person3 = person.deepCopy()
    println(person === person2)
    println(person.group === person2.group)
    println(person.group === person3?.group)
}