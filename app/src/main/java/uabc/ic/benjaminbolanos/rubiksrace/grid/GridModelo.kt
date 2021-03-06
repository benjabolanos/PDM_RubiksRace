package uabc.ic.benjaminbolanos.rubiksrace.grid

import uabc.ic.benjaminbolanos.rubiksrace.util.Cuadro

/**
 * Clase GridModelo que modela un grid de un jugador de RubiksRace.
 */
class GridModelo() {
    val cuadros:Array<Cuadro> = Array(25){ Cuadro("negro") }
    private val colores:Array<Cuadro> = arrayOf(
        Cuadro("azul"), Cuadro("rojo"), Cuadro("verde"),
        Cuadro("amarillo"), Cuadro("morado"), Cuadro("naranja")
    )
    var movimientos:Int = 0

    /**
     * Constructor que manda a crear un nuevo grid.
     */
    init{
        crearGrid()
    }

    /**
     * Método que recibe dos enteros para intercambiar sus colores. Para que dos cuadros sean intercambiables
     * el primero no debe ser de color negro y el segundo debe estar pegado por alguno de los cuatro lados
     * del primero.
     *
     * @return True si el intercambio fue exitoso
     */
    fun intercambiar(desde:Int, hacia:Int):Boolean{
        var intercambiable = false
        if(cuadros[hacia].nombre == "negro"){
            if(desde % 5 == 0) {
                if (hacia == (desde - 5) || hacia == (desde + 1) || hacia == (desde + 5)) {
                    intercambiable = true
                }
            }
            else if((desde+1) % 5 == 0){
                if(hacia == (desde - 5) || hacia == (desde - 1) || hacia == (desde + 5)) {
                    intercambiable = true
                }
            } else {
                if (hacia == (desde - 5) || hacia == (desde - 1) || hacia == (desde + 1) || hacia == (desde + 5)) {
                    intercambiable = true
                }
            }
        }
        if(intercambiable) {
            val aux = cuadros[hacia]
            cuadros[hacia] = cuadros[desde]
            cuadros[desde] = aux
            movimientos++
        }

        return intercambiable
    }

    /**
     * Método que crea/reinicia el grid del jugador. Crea 24 cuadros de colores, evitando que haya
     * más de 4 veces el mismo color. En el ultimo cuadro, pone su color como negro.
     */
    fun crearGrid(){
        movimientos = 0
        var i = 0
        val contadoresColores:Array<Int> = Array(6){0}
        val rand = java.util.Random()
        while(i < 24){
            val intRandom = rand.nextInt(6)
            if(contadoresColores[intRandom] >= 4){
                i--
            } else {
                cuadros[i] = colores[intRandom]
                contadoresColores[intRandom]++
            }
            i++
        }
        cuadros[i] = Cuadro("negro")
    }

    /**
     * Método que toma los valores de los 9 cuadros que están en el centro del grid y los retorna en un
     * Array.
     * @return un Array<Color> de los 9 cuadros del centro.
     */
    fun getCentro():Array<Cuadro>{
        var j = 0
        val centro:Array<Cuadro> = Array(9){ Cuadro("negro") }
        for(i in 0..24){
            if(i in 6..18 && i%5!=0 && (i+1)%5!=0){
                centro[j] = cuadros[i]
                j++
            }
        }
        return centro
    }
}