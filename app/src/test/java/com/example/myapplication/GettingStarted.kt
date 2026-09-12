package com.example.myapplication

class Greeter {
    fun sayHello() {
        println("Hello from a class!")
    }
}

fun main(args: Array<String>) {
    val greeter = Greeter()
    greeter.sayHello()
}
