package generics

import generics.Models.register
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.log
import kotlin.reflect.KClass
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty


object Models{
    private val modelMap = ConcurrentHashMap<Class<out AbsModel>, AbsModel>()
    fun <T: AbsModel> KClass<T>.get(): T {
        return modelMap[this.java] as T
    }
    fun AbsModel.register(){
        modelMap[this.javaClass] = this
    }
}
abstract class AbsModel{
    init {
        Models.run {register()}
    }
}

class ModelDelegate<T: AbsModel>(val kClass: KClass<T>): ReadOnlyProperty<Any, T>{
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        println("property: $property")
        return Models.run { kClass.get() }
    }
}
inline fun <reified T: AbsModel> modelOf(): ModelDelegate<T> = ModelDelegate(T::class)

class DatabaseModel : AbsModel(){
    fun query(sal: String): Int = 0
}

class NetworkModel: AbsModel(){
    fun get(url: String):String = """{"code": 0}"""
}

val x: Int
    get() = Math.random().toInt()

class MainViewModel{
    val databaseModel by modelOf<DatabaseModel>()
    val networkModel by modelOf<NetworkModel>()
}

fun main() {
    DatabaseModel()
    NetworkModel()
    val mainViewModel = MainViewModel()
    mainViewModel.databaseModel.query("select * from mysql.user").let(::println)
    mainViewModel.networkModel.get("https://www.imooc.com").let(::println)
}