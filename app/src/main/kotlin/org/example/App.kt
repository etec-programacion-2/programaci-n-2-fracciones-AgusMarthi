package org.example

fun main() {
    val f1 = Fraccion(3,5)
    val f2 = Fraccion(4,6)
    val suma = f1 + f2
    val resta = f1 - f2
    val multiplicacion = f1 * f2
    val division = f1 / f2
    println("suma:$suma, resta:$resta, multiplicación:$multiplicacion, division:$division")
}
