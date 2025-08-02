package org.example

fun main() {
    // Objetos
    val f1 = Fraccion(1, 2)
    val f2 = Fraccion(2,4)
    val d1 = Fraccion.desdeDecimal(-2.4) // Objeto asociado
    // Funciones aplicadas
    val suma = f1 + f2
    val resta = f1 - f2
    val multiplicacion = f1 * f2
    val division = f1 / f2
    val comparar = f1.compareTo(f2)
    val mayor = f1 > f2
    val menor = f1 < f2
    val iguales = f1 == f2
    val adecimal = f1.aDecimal()
    
    println("suma:$suma, resta:$resta, multiplicación:$multiplicacion, division:$division, comparación: $comparar, f1 es mayor:$mayor, f1 es menor:$menor, son iguales:$iguales, en decimal: $adecimal, en fracción:$d1")
}
