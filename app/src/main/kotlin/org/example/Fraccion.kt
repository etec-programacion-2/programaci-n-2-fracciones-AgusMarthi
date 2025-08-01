package org.example

class Fraccion(private var _numerador : Int,private var _denominador: Int) {

    var numerador: Int = _numerador
        get() = field
        set(value) { _numerador = value }

    var denominador: Int = _denominador
        get() = field
        set(value) { _denominador = value }


    init { if (denominador == 0){
        throw IllegalArgumentException("El denominador no puede ser cero")
    }}

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

    fun mostrar(){
        println(this.toString())
    }

}
