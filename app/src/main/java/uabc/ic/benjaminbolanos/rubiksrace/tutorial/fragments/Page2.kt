package uabc.ic.benjaminbolanos.rubiksrace.tutorial.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text
import uabc.ic.benjaminbolanos.rubiksrace.R


class Page2 : Fragment() {
    private lateinit var texto1: TextView
    private lateinit var texto2: TextView
    private lateinit var texto3: TextView
    private lateinit var boton: MaterialButton
    private lateinit var layout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_page2, container, false)

        texto1 = view.findViewById(R.id.tutorial_p2_texto1)
        texto2 = view.findViewById(R.id.tutorial_p2_texto2)
        texto3 = view.findViewById(R.id.tutorial_p2_texto3)
        boton = view.findViewById(R.id.tutorial_p2_boton)
        layout = view.findViewById(R.id.tutorial_p2_layout)

        return view
    }

    override fun onResume() {
        super.onResume()
        val preferencias = context?.getSharedPreferences("colores", Context.MODE_PRIVATE)
        if (preferencias != null) {
            if(preferencias.contains("color_secundario")) {
                val colorSecundario = preferencias.getInt("color_secundario", R.color.fondo_turquesa)
                val colorPrimario = preferencias.getInt("color_primario", R.color.white)
                cambiarColores(colorPrimario, colorSecundario)
            }
        }
    }

    /**
     * Cambia los colores de las views dependiendo del color primario y secundario
     */
    private fun cambiarColores(colorPrimario: Int, colorSecundario: Int){
        texto1.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }

        texto2.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }

        texto3.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }

        boton.apply {
            setTextColor(colorSecundario)
            backgroundTintList = ColorStateList.valueOf(colorPrimario)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }


        layout.setBackgroundColor(colorSecundario)
    }

}