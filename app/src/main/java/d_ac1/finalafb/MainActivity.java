package d_ac1.finalafb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity{

    private Double PrecioComb; //Variable del precio del combustible
    private String[] Tips = new String[20]; //Vector de frases para los consejos
    private int iterador;
    public int tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBoxListener();
        SpinnerCreator();
        BotonCalculadora();
        RellenadorConsejos();
        BotonConsejos();
    }

    /**
     *Listener para los checkbox
     */
    private void CheckBoxListener() {
        tips = 0;
        CheckBox Rec = (CheckBox) findViewById(R.id.Rec1);
        Rec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) ++tips;
                else --tips;
                TextView TextLoadingBar = (TextView) findViewById(R.id.BarraCargarText);
                TextLoadingBar.setText(tips + " de 4");
                ProgressBar Loading = (ProgressBar) findViewById(R.id.BarraCargar);
                Loading.setProgress(tips);
            }
        });
        Rec = (CheckBox) findViewById(R.id.Rec2);
        Rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((CheckBox) view).isChecked()) ++tips;
                else --tips;
                TextView TextLoadingBar = (TextView) findViewById(R.id.BarraCargarText);
                TextLoadingBar.setText(tips + " de 4");
                ProgressBar Loading = (ProgressBar) findViewById(R.id.BarraCargar);
                Loading.setProgress(tips);
            }
        });
        Rec = (CheckBox) findViewById(R.id.Rec3);
        Rec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) ++tips;
                else --tips;
                TextView TextLoadingBar = (TextView) findViewById(R.id.BarraCargarText);
                TextLoadingBar.setText(tips + " de 4");
                ProgressBar Loading = (ProgressBar) findViewById(R.id.BarraCargar);
                Loading.setProgress(tips);
            }
        });
        Rec = (CheckBox) findViewById(R.id.Rec4);
        Rec.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) ++tips;
                else --tips;
                TextView TextLoadingBar = (TextView) findViewById(R.id.BarraCargarText);
                TextLoadingBar.setText(tips + " de 4");
                ProgressBar Loading = (ProgressBar) findViewById(R.id.BarraCargar);
                Loading.setProgress(tips);
            }
        });
    }

    /**
     *Rellena el vector de consejos
     */
    private void RellenadorConsejos() {
        iterador = 0;
        Tips[0] = "...la velocidad máxima permitida es de 20Km/h más en un adelantamiento";
        Tips[1] = "...la gasolina está más barata los lunes, por que es el día que revisan " +
                "los precios.";
        Tips[2] = "...el diésel es más barato por que está menos depurado y contiene más impurezas.";
        Tips[3] = "...en caso de accidente, los triángulos deben verse a mas de 100 metros.";
        Tips[4] = "...el airbag es capaz de matarte si no llevas el cinturón, asi que póntelo!";
        Tips[5] = "...llevar las ventanillas bajadas aumenta el consumo, debido a que " +
                "empeora la aerodinámica.";
        Tips[6] = "...una presión baja en las ruedas, además de aumentar el consumo, " +
                "acelera el desgaste de los neumáticos.";
        Tips[7] = "...además, es ideal ajustar la presión según si haremos un viaje cargados o no.";
        Tips[8] = "...si tu coche es turboalimentado, deberías dejarlo en marcha un rato cuando " +
                "llegues a tu destino para enfriar lentamente el turbocompresor y evitar que se " +
                "estropee, ya que en funcionamiento, alcanza picos de hasta 200.000 RPM.";
        Tips[9] = "...los coches de colores raros o chillones, pagan menos seguro" +
                "debido a que son más fáciles de encontrar si son robados.";
        Tips[10] = "...un coche normal consume 6 veces más en primera marcha a 10Km/h" +
                " que en quinta marcha a 80Km/h.";
    }

    /**
     *Crea el boton de los consejos
     */
    private void BotonConsejos(){
        TextView tipInit = (TextView) findViewById(R.id.RandTip);
        tipInit.setText(Tips[0]);
        Button tip = (Button) findViewById(R.id.NewTip);
        tip.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tip = (TextView) findViewById(R.id.RandTip);
                if(iterador == 10) iterador = 0;
                else ++iterador;
                tip.setText(Tips[iterador]);
            }
        });
    }

    /**
     *Crea y rellena el spinner de combustibles
     */
    private void SpinnerCreator(){
        //Spinner filler
        Spinner spinner = (Spinner) findViewById(R.id.SpinnerCombustibles);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.combustibles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //Spinner listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String item = parent.getItemAtPosition(position).toString();
                switch (item){
                    case "Gasolina":
                        PrecioComb = 1.199;
                        break;
                    case "Diésel":
                        PrecioComb = 0.99;
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                //Stub necesario
            }

        });
    }

    /**
     *Creacion y calculos de la calculadora con trato de errores
     */
    private void BotonCalculadora(){
        Button calcula = (Button) findViewById(R.id.Go);
        calcula.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText ET = (EditText) findViewById(R.id.km);
                String aux = ET.getText().toString();
                Double km, cons;
                if (aux.equals("")) {
                    Toast.makeText(getBaseContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                    km = 0.0;
                }
                else { //Comprobar si el double es correcto
                    try {
                        km = Double.parseDouble(aux);
                    }
                    catch(NumberFormatException e) {
                        Toast.makeText(getBaseContext(), "Has escrito mal el campo 'Kilómetros'\n" +
                                "Consejo: Has escrito coma en vez de punto?", Toast.LENGTH_LONG).show();
                        km = 0.0;
                    }
                }
                ET = (EditText) findViewById(R.id.consumo);
                aux = ET.getText().toString();
                if (aux.equals("")) {
                    Toast.makeText(getBaseContext(), "Rellena todos los campos",
                            Toast.LENGTH_LONG).show();
                    cons = 0.0;
                }
                else { //Comprobar si el double es correcto
                    try {
                        cons = Double.parseDouble(aux);
                    }
                    catch(NumberFormatException e) {
                        Toast.makeText(getBaseContext(), "Has escrito mal el campo 'L/100'\n" +
                                "Consejo: Has escrito coma en vez de punto?", Toast.LENGTH_LONG).show();
                        cons = 0.0;
                    }
                }
                cons = (cons/100)*km; //Calculo de combustible total
                TextView result = (TextView) findViewById(R.id.resultado);
                result.setText(String.format("%.2f", (cons)) + "L");
                result = (TextView) findViewById(R.id.resultado2);
                result.setText(String.format("%.2f", (cons*PrecioComb)) + "€");//Calculo gasto total
                result = (TextView) findViewById(R.id.resultado3);
                result.setText(String.format("%.3f", (PrecioComb)) + "€");
            } });
    }
}
