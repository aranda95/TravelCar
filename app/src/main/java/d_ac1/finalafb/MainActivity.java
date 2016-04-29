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


public class MainActivity extends AppCompatActivity{

    private Double fuelPrice; //Variable for fuel price
    private String[] tips = new String[11]; //Tips vector for DidYouKnow...? Button
    private int tipsIterator;
    public int progressBarStatus; //0(0%) - 4(100%)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCheckBoxListener();
        createAndFillSpinner();
        createCalcFunctions();
        fillTipsVector();
        createDidYouKnowButton();
    }

    /**
     *Recalculation of the progressBar
     */
    private void setProgressBar(CompoundButton compoundButton){
        if (compoundButton.isChecked()) ++progressBarStatus;
        else --progressBarStatus;
        TextView textLoadingBar = (TextView) findViewById(R.id.BarraCargarText);
        textLoadingBar.setText(progressBarStatus + " de 4");
        ProgressBar loading = (ProgressBar) findViewById(R.id.BarraCargar);
        loading.setProgress(progressBarStatus);
    }

    /**
     *Listener for checkboxes
     */
    private void createCheckBoxListener() {
        progressBarStatus = 0;
        CheckBox reminders = (CheckBox) findViewById(R.id.Rec1);
        reminders.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setProgressBar(compoundButton);
            }
        });
        reminders = (CheckBox) findViewById(R.id.Rec2);
        reminders.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setProgressBar(compoundButton);
            }
        });
        reminders = (CheckBox) findViewById(R.id.Rec3);
        reminders.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setProgressBar(compoundButton);
            }
        });
        reminders = (CheckBox) findViewById(R.id.Rec4);
        reminders.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setProgressBar(compoundButton);
            }
        });
    }

    /**
     *Fill the tips vector with some strings to show
     */
    private void fillTipsVector() {
        tipsIterator = 0;
        tips[0] = "...la velocidad máxima permitida es de 20Km/h más en un adelantamiento";
        tips[1] = "...la gasolina está más barata los lunes, por que es el día que revisan " +
                "los precios.";
        tips[2] = "...el diésel es más barato por que está menos depurado y contiene más impurezas.";
        tips[3] = "...en caso de accidente, los triángulos deben verse a mas de 100 metros.";
        tips[4] = "...el airbag es capaz de matarte si no llevas el cinturón, asi que póntelo!";
        tips[5] = "...llevar las ventanillas bajadas aumenta el consumo, debido a que " +
                "empeora la aerodinámica.";
        tips[6] = "...una presión baja en las ruedas, además de aumentar el consumo, " +
                "acelera el desgaste de los neumáticos.";
        tips[7] = "...además, es ideal ajustar la presión según si haremos un viaje cargados o no.";
        tips[8] = "...si tu coche es turboalimentado, deberías dejarlo en marcha un rato cuando " +
                "llegues a tu destino para enfriar lentamente el turbocompresor y evitar que se " +
                "estropee, ya que en funcionamiento, alcanza picos de hasta 200.000 RPM.";
        tips[9] = "...los coches de colores raros o chillones, pagan menos seguro" +
                "debido a que son más fáciles de encontrar si son robados.";
        tips[10] = "...un coche normal consume 6 veces más en primera marcha a 10Km/h" +
                " que en quinta marcha a 80Km/h.";
    }

    /**
     *Create the button which controls the function that shows random Tips
     */
    private void createDidYouKnowButton(){
        TextView tipInit = (TextView) findViewById(R.id.RandTip);
        tipInit.setText(tips[0]);
        Button tip = (Button) findViewById(R.id.NewTip);
        tip.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tip = (TextView) findViewById(R.id.RandTip);
                if(tipsIterator == 10) tipsIterator = 0;
                else ++tipsIterator;
                tip.setText(tips[tipsIterator]);
            }
        });
    }

    /**
     *Create and fill the Spinner with fuel names and the listener which returns the fuel price
     */
    private void createAndFillSpinner(){
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
                        fuelPrice = 1.199;
                        break;
                    case "Diésel":
                        fuelPrice = 0.99;
                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                //Stub necesario
            }

        });
    }

    /**
     *Create and controls the listener which calculate the fuel expenses, and write it in the TextView
     */
    private void createCalcFunctions(){
        Button calcula = (Button) findViewById(R.id.Go);
        calcula.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText calcReader = (EditText) findViewById(R.id.km);
                String aux = calcReader.getText().toString();
                Double km, consuming;
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
                calcReader = (EditText) findViewById(R.id.consumo);
                aux = calcReader.getText().toString();
                if (aux.equals("")) {
                    Toast.makeText(getBaseContext(), "Rellena todos los campos",
                            Toast.LENGTH_LONG).show();
                    consuming = 0.0;
                }
                else { //Comprobar si el double es correcto
                    try {
                        consuming = Double.parseDouble(aux);
                    }
                    catch(NumberFormatException e) {
                        Toast.makeText(getBaseContext(), "Has escrito mal el campo 'L/100'\n" +
                                "Consejo: Has escrito coma en vez de punto?", Toast.LENGTH_LONG).show();
                        consuming = 0.0;
                    }
                }
                consuming = (consuming/100)*km; //Fuel cost function
                TextView result = (TextView) findViewById(R.id.resultado);
                result.setText(String.format("%.2f", (consuming)) + "L");
                result = (TextView) findViewById(R.id.resultado2);
                result.setText(String.format("%.2f", (consuming*fuelPrice)) + "€");//Total expenses on fuel
                result = (TextView) findViewById(R.id.resultado3);
                result.setText(String.format("%.3f", (fuelPrice)) + "€");
            } });
    }
}
