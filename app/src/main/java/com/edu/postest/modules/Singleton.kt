package com.edu.postest.modules

/**
 * Author: Meng
 * Date: 2025/09/04
 * Modify: 2025/09/04
 * Desc: 单例对象声明
 * https://book.kotlincn.net/text/basic-syntax.html
 */
object Singleton {

//    when 表达式
//    when (obj) {
//        1          -> "One"
//        "Hello"    -> "Greeting"
//        is Long    -> "Long"
//        !is String -> "Not a string"
//        else       -> "Unknown"
//    }

//    使用区间（range）
//    val x = 10
//    val y = 9
//    if (x in 1..y+1) {
//        println("fits in range")
//    }

//    只读 list
//    val list = listOf("a", "b", "c")
//    val items = setOf("apple", "banana", "kiwifruit")
//    只读 map
//    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
//    访问 map 条目
//    println(map["key"])
//    map["key"] = value
//    遍历 map 或者 pair 型 list
//    for ((k, v) in map) {
//        println("$k -> $v")
//    }
//    k 与 v 可以是任何适宜的名称，例如 name 与 age。
//
//    区间迭代
//    for (i in 1..100) { …… }  // 闭区间：包含 100
//    for (i in 1..< 100) { …… } // 左闭右开区间：不包含 100
//    for (x in 2..10 step 2) { …… }
//    for (x in 10 downTo 1) { …… }
//    (1..10).forEach { …… }
//    for (index in items.indices) {
//        println("item at $index is ${items[index]}")
//    }
//    延迟属性
//    val p: String by lazy { // 该值仅在首次访问时计算
//        // 计算该字符串
//    }

//    println(files?.size ?: "empty") // 如果 files 为 null，那么输出“empty”
// 如需在代码块中计算更复杂的备用值，请使用 `run`
//    val filesSize = files?.size ?: run {
//      val someSize = getSomeSize()
//      someSize * 2
//    }

//    if not null 执行代码
//    val value = ……
//    value?.let {
//        …… // 如果非空会执行这个代码块
//    }

//    返回 when 表达式
//    fun transform(color: String): Int {
//        return when (color) {
//            "Red" -> 0
//            "Green" -> 1
//            "Blue" -> 2
//            else -> throw IllegalArgumentException("Invalid color param value")
//        }
//    }

//    try-catch 表达式
//    fun test() {
//        val result = try {
//            count()
//        } catch (e: ArithmeticException) {
//            throw IllegalStateException(e)
//        }
//
//        // 使用 result
//    }

//    if 表达式
//    val y = if (x == 1) {
//        "one"
//    } else if (x == 2) {
//        "two"
//    } else {
//        "other"
//    }
//    返回类型为 Unit 的方法的构建器风格用法
//    fun arrayOfMinusOnes(size: Int): IntArray {
//        return IntArray(size).apply { fill(-1) }
//    }

//    对一个对象实例调用多个方法 （with）
//    class Turtle {
//        fun penDown()
//        fun penUp()
//        fun turn(degrees: Double)
//        fun forward(pixels: Double)
//    }
//
//    val myTurtle = Turtle()
//    with(myTurtle) { // 画一个 100 像素的正方形
//        penDown()
//        for (i in 1..4) {
//            forward(100.0)
//            turn(90.0)
//        }
//        penUp()
//    }

//    配置对象的属性（apply）
//    val myRectangle = Rectangle().apply {
//        length = 4
//        breadth = 5
//        color = 0xFAFAFA
//    }
//    这对于配置未出现在对象构造函数中的属性非常有用。

//    交换两个变量
//    var a = 1
//    var b = 2
//    a = b.also { b = a }
}