package org.example

import javax.print.attribute.standard.MediaSize

class Fraccion(private var _numerador : Int,private var _denominador: Int) {

    var numerador: Int = _numerador
        get() = field
        set(value) {
            _numerador = value
        }

    var denominador: Int = _denominador
        get() = field
        set(value) {
            _denominador = value
        }


    init {
        if (denominador == 0) {
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
    }

    override fun toString(): String {
        return ("$_numerador/$_denominador")
    }

    private fun simplificar(): Fraccion {
        val mcd = calcularMCD(kotlin.math.abs(_numerador), kotlin.math.abs(_denominador))
        val numeradorSimplificado = _numerador / mcd
        val denominadorSimplificado = _denominador / mcd

        // Manejar signos: si el denominador es negativo, pasar el signo al numerador
        return if (denominadorSimplificado < 0) {
            Fraccion(-numeradorSimplificado, -denominadorSimplificado)
        } else {
            Fraccion(numeradorSimplificado, denominadorSimplificado)
        }
    }

    private fun calcularMCD(a: Int, b: Int): Int {
        return if (b == 0) a else calcularMCD(b, a % b)
    }

    operator fun plus(otra: Fraccion): Fraccion {
        val sumnumerador: Int = this._numerador * otra._denominador + otra._numerador * this._denominador
        val sumdenominador: Int = this._denominador * otra._denominador
        return Fraccion(sumnumerador, sumdenominador).simplificar() // Se simplifica automáticamente en init
    }

    operator fun minus(otra: Fraccion): Fraccion {
        val resnumerador: Int = this._numerador * otra._denominador - otra._numerador * this._denominador
        val resdenominador: Int = this._denominador * otra._denominador
        return Fraccion(resnumerador, resdenominador).simplificar() // Se simplifica automáticamente en init
    }

    operator fun times(otra: Fraccion): Fraccion {
        val mulnumerador: Int = this._numerador * otra._numerador
        val muldenominador: Int = otra._denominador * this._denominador
        return Fraccion(mulnumerador, muldenominador).simplificar()
    }

    operator fun div(otra: Fraccion): Fraccion {
        val divnumerador: Int = this._numerador * otra._denominador
        val divdenominador: Int = this._denominador * otra._numerador
        return (if (divdenominador != 0) {
            Fraccion(divnumerador, divdenominador).simplificar()
        } else {
            println("el denominador no puede ser cero")
        }) as Fraccion
    }

    operator fun compareTo(otra: Fraccion): Int{
        val diferencia = this._numerador * otra._denominador - otra._numerador * this._denominador
        return when {
            diferencia < 0 -> -1
            diferencia > 0 -> 1
            else -> 0
        }
    }

    override operator fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other !is Fraccion) return false
        val diferencia = this._numerador * other._denominador - other._numerador * this._denominador
        return diferencia == 0
    }

    override fun hashCode(): Int {
        val mcd = calcularMCD(kotlin.math.abs(_numerador), kotlin.math.abs(_denominador))
        val numSimp = _numerador / mcd
        val denSimp = _denominador / mcd
        return 31 * numSimp + denSimp
    }

    fun esMayor(otra: Fraccion): Boolean{
        val thisresultado : Int = this._numerador / this._denominador
        val otraresultado : Int = otra._numerador / otra._denominador
        var thismayor : Boolean
        if (thisresultado < otraresultado){
            thismayor = false
        } else {
            thismayor = true
        }
        return thismayor
    }

    fun esMenor(otra: Fraccion): Boolean{
        val thisresultado : Int = this._numerador / this._denominador
        val otraresultado : Int = otra._numerador / otra._denominador
        var thismenor : Boolean
        if (thisresultado > otraresultado){
            thismenor = false
        } else {
            thismenor = true
        }
        return thismenor
    }

    fun aDecimal(): Double{
        val resultado : Double = _numerador.toDouble() / _denominador.toDouble()
        return resultado
    }

    fun mostrar(){
        println(this.toString())
    }

    companion object{  // Creo un objeto asosciado a la clase
        fun desdeDecimal(decimal: Double): Fraccion{
            if(decimal == 0.0) return Fraccion(0,1) // Por si el decimal es 0
            val esNegativo = decimal < 0 // Identifica si el decimal es negativo
            val decimalPositivo = kotlin.math.abs(decimal) // Valor absoluto del decimal
            var x = decimalPositivo // Valor actual decimal
            var a = x.toInt() // Paso x a entero

            // Si es un numero entero, lo devuelvo con denominador 1
            if (a.toDouble() == x) {
                return if (esNegativo) Fraccion(-a, 1) else Fraccion(a, 1)
            }

            var p0 = 1      // Primer numerador
            var q0 = 0      // Primer nenominador
            var p1 = a      // Segundo numerador
            var q1 = 1      // Segundo nenominador
            val bucles = 50 // Para que no se ejecute infinitamente
            val precision = 1e-15 // precisión adecuada para la aproximación

            for (i in 0 until bucles) {
                x = 1.0 / (x - a) // Tomo la parte fraccionaria invertida
                a = x.toInt() // Nueva parte entera

                val p2 = a * p1 + p0 // Nuevo numerador
                val q2 = a * q1 + q0 // Nuevo denominador

                // Verifico si la aproxiamción es precisa
                if (kotlin.math.abs(decimalPositivo - p2.toDouble() / q2.toDouble()) < precision) {
                    return if (esNegativo) Fraccion(-p2, q2) else Fraccion(p2, q2)
                }
                // Actualizo los valores para que continúe el bucle
                p0 = p1
                q0 = q1
                p1 = p2
                q1 = q2
            }
            // Retorno la fracción
            return if (esNegativo) Fraccion(-p1, q1) else Fraccion(p1, q1)
        }
    }
}
