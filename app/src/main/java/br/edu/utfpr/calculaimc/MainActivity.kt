package br.edu.utfpr.calculaimc

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {//fim da classe MainActivity

    private lateinit var etPeso: EditText
    private lateinit var etAltura: EditText
    private lateinit var tvResultado: TextView
    private lateinit var btLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        tvResultado = findViewById(R.id.tvResultado)
        btLimpar = findViewById(R.id.btLimpar)


        btLimpar.setOnLongClickListener {
            Toast.makeText(this, getString(R.string.limpar_tela), Toast.LENGTH_SHORT).show()
            false
        }

        btLimpar.setOnClickListener {
            limparTela()
        }


    }//fim do método onCreate()


    fun limparTela() {
        etPeso.setText( "" )
        etAltura.setText( "" )
        tvResultado.setText(getString(R.string.zeros))
        etPeso.requestFocus()
    }

    fun calcularIMC(view: View) {
        //entrada
        val peso = etPeso.text.toString().toDoubleOrNull()
        val altura = etAltura.text.toString().toDoubleOrNull()
        var resultado = 0.0

        if ( peso == null ) {
            etPeso.setError(getString(R.string.erro_peso))
            return
        }

        if ( altura == null ) {
            etAltura.setError(getString(R.string.erro_altura))
            return
        }

        if ( Locale.getDefault().language == "en" ) {
            resultado = 703 * ( peso / altura.pow(2) )
            val nf = NumberFormat.getInstance(Locale.US)
            val df = nf as java.text.DecimalFormat
            tvResultado.text = df.format(resultado)
        } else {
            resultado = peso / altura.pow(2)
            tvResultado.text = "%.2f".format(resultado)
        }


        //processamento


        //saída




    }

}