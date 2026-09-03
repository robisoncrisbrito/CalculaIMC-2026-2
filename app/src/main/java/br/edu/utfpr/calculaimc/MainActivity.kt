package br.edu.utfpr.calculaimc

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
            Toast.makeText(this, "Limpar Tela", Toast.LENGTH_SHORT).show()
            false
        }

        btLimpar.setOnClickListener {
            limparTela()
        }

        Log.e( "onCreate()", "msg de erro" )
        Log.i( "onCreate()", "msg de info" )
        Log.w( "onCreate()", "msg de warning" )
        Log.d( "onCreate()", "msg de debug" )
        Log.wtf( "onCreate()", "msg de wtc" )



    }//fim do método onCreate()


    fun limparTela() {
        etPeso.setText( "" )
        etAltura.setText( "" )
        tvResultado.setText("0.0")
        etPeso.requestFocus()
    }

    fun calcularIMC(view: View) {
        //entrada
        val peso = etPeso.text.toString().toDoubleOrNull()
        val altura = etAltura.text.toString().toDoubleOrNull()
        var resultado : Double = 0.0

        if ( peso == null ) {
            etPeso.setError( "O campo peso deve ser preenchido.")
            return
        }

        if ( altura == null ) {
            etAltura.setError( "O campo altura deve ser preenchido.")
            return
        }

        //processamento
        resultado = peso / altura.pow(2)

        //saída
        tvResultado.text = "%.2f".format(resultado)

    }

}