package cl.inacap.aplicacionconciertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.aplicacionconciertos.dao.EventosDAO;
import cl.inacap.aplicacionconciertos.dao.EventosDAOLista;
import cl.inacap.aplicacionconciertos.dto.Evento;

public class MainActivity extends AppCompatActivity {

    private Button fechaBtn;
    private EventosDAO eventosDAO = new EventosDAOLista();

    private int dia, mes, anio ;
    private Spinner generoSpn;
    private Spinner calificacionSpn;

    private EditText nombreTxt;
    private EditText fechaTxt;
    private EditText valorTxt;
    Button registrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registrar = (Button) findViewById(R.id.registrarBtn);

        this.nombreTxt = findViewById(R.id.nombreTxt);
        this.fechaTxt = findViewById(R.id.fechaTxt);
        this.generoSpn = findViewById(R.id.generoSpn);
        this.valorTxt = findViewById(R.id.valorTxt);
        this.calificacionSpn = findViewById(R.id.calificacionSpn);



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> errores = new ArrayList<>();
                int calificacionInt=0;
                int valorInt=0;
                String nombreStr = nombreTxt.getText().toString().trim();
                if(nombreStr.isEmpty()){
                    errores.add("-El nombre no puede estar vacio");
                }

                String fechaStr = fechaTxt.getText().toString().trim();
                if(fechaStr.isEmpty()){
                    errores.add("-Debe seleccionar una fecha");
                }
                String generoStr = generoSpn.getSelectedItem().toString();
                String valorStr = valorTxt.getText().toString().trim();
                try{
                    valorInt = Integer.parseInt(valorStr);
                }catch (NumberFormatException ex){
                    errores.add("-el valor debe ser un numero");
                }
                String calificacionStr = calificacionSpn.getSelectedItem().toString().trim();
                try{
                    calificacionInt = Integer.parseInt(calificacionStr);
                }catch (NumberFormatException ex){
                    errores.add("-La calificacion debe ser un numero");
                }

                if(errores.isEmpty()){
                    Evento e = new Evento();
                    e.setNombreArtista(nombreStr);
                    e.setFecha(fechaStr);
                    e.setGenero(generoStr);
                    e.setValorEntrada(valorInt);
                    e.setCalificacion(calificacionInt);
                    eventosDAO.add(e);
                }else{
                    mostrarErrores(errores);
                }

            }
        });













        this.generoSpn = findViewById(R.id.generoSpn);
        this.calificacionSpn = findViewById(R.id.calificacionSpn);


        //
        ArrayList<String> generosList = new ArrayList<String>();
        generosList.add("Rock");
        generosList.add("Jazz");
        generosList.add("Pop");
        generosList.add("Reggaeton");
        generosList.add("Salsa");
        generosList.add("Metal");
        //

        //
        ArrayList<String> calificacionList = new ArrayList<>();
        calificacionList.add("1");
        calificacionList.add("2");
        calificacionList.add("3");
        calificacionList.add("4");
        calificacionList.add("5");
        calificacionList.add("6");
        calificacionList.add("7");

        //

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,generosList);
        generoSpn.setAdapter(adapter);
        generoSpn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Toast.makeText(parent.getContext(),"Seleccionado"+parent.getItemAtPosition(position).toString(),
                                Toast.LENGTH_LONG).show();
            }
        });

        ArrayAdapter<CharSequence> adapterC = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,calificacionList);
        calificacionSpn.setAdapter(adapterC);




        fechaBtn = (Button)findViewById(R.id.fechaBtn);
        fechaTxt = (EditText)findViewById(R.id.fechaTxt);
        fechaBtn.setOnClickListener((View.OnClickListener) this);

    }

    private void mostrarErrores(List<String> errores) {

        String mensaje ="";
        for(String e: errores){
            mensaje+= "-"+ e + "\n";
        }
        AlertDialog.Builder alertBuilder =  new AlertDialog.Builder();
        alertBuilder.setTitle("Error de validacion")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",null)
                .create()
                .show();



    }

    public void onClick (View v){
        if(v==fechaBtn){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            anio=c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    fechaTxt.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            },anio,mes,dia);

            datePickerDialog.show();

        }

    }
}