package annotations

import java.lang.StringBuilder
import java.lang.reflect.Proxy
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.valueParameters

data class User(
    var login: String,
    var location: String,
    var bio: String)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Api(val url: String)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Path(val url: String = "")

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Get(val url: String = "")

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class PathVariable(val name: String = "")

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class Query(val name: String = "")

@Api("https://api.github.com")
interface GitHubApi {

    @Api("users")
    interface Users {

        @Get("{name}")
        fun get(name: String): User

        @Get("{name}/followers")
        fun followers(name: String): List<User>

    }

    @Api("repos")
    interface Repos {
        @Get("{owner}/{repo}/forks")
        fun forks(owner: String, repo: String)
    }
}

object RetroApi{
    const val PATH_PATTERN = """(\{(\w+)\})"""
    val enclosing = {
        cls: Class<*> ->
        var currentCls: Class<*>?  = cls
        println("currentCls?.simpleName ${currentCls?.simpleName}")
        sequence {
            while (currentCls !=null){
                currentCls = currentCls?.also{yield(it)}?.enclosingClass
            }
        }
    }

    inline fun <reified T> create(): T{
        val functionMap = T::class.functions.associateBy { it.name }
        val interfaces = enclosing(T::class.java).takeWhile { it.isInterface }.toList()

        val apiPath = interfaces.foldRight(StringBuilder()){ clazz, acc ->
            acc.append(clazz.getAnnotation(Api::class.java)?.url?.takeIf { it.isNotBlank() }
                ?: clazz.name).append("/")
            }.toString()
        println("apiPath: $apiPath")
        println("functionMap, $functionMap")
        return Proxy.newProxyInstance(RetroApi.javaClass.classLoader, arrayOf(T::class.java)){
            proxy, method, args->
            functionMap[method.name]?.takeIf { it.isAbstract }?.let {
                    function ->
                val parameterMap = function.valueParameters.map {
                    it.name to args[it.index - 1]
                }.toMap()

                //{name}
                val endPoint = function.findAnnotation<Get>()!!.url.takeIf { it.isNotEmpty() }?: function.name
                val compiledEndPoint = Regex(PATH_PATTERN).findAll(endPoint).map {
                        matchResult ->
                    matchResult.groups[1]!!.range to parameterMap[matchResult.groups[2]!!.value]
                }.fold(endPoint) {
                        acc, pair ->
                    acc.replaceRange(pair.first, pair.second.toString())
                }
                val url = apiPath + compiledEndPoint
                println(url)

                return@let null
//                okHttp.newCall(Request.Builder().url(url).get().build()).execute().body()?.charStream()?.use {
//                    gson.fromJson(JsonReader(it), method.genericReturnType)
//                }

            }
        } as T

    }
}

fun main() {
    val userApi = RetroApi.create<GitHubApi.Users>()
    println(userApi.get("Jerry0Chou"))
//    println(userApi.followers("Jerry0Chou").map { it.login })
}