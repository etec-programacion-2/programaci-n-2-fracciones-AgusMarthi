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

    fun mostrar(){
        println(this.toString())
    }
    
    }
