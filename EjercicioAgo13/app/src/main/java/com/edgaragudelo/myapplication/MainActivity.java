package com.edgaragudelo.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.edgaragudelo.myapplication.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupButtons();
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });
    }

    private void setupButtons() {
        binding.IdBtnConsultar.setOnClickListener(v -> mostrarDatos());
        binding.IdBtnEliminar.setOnClickListener(v -> limpiarDatos());
    }

    private void mostrarDatos() {
        PersonalData data = leerDatos();
        if (data == null) return; // ya mostró Toast

        binding.txvOutNombre.setText("Nombre: " + data.nombre);
        binding.txvOutApellido.setText("Apellido: " + data.apellido);
        binding.txvOutFechaDeNacimiento.setText("Fecha: " + data.fechaNac);
        binding.txvOutGenero.setText("Género: " + data.genero);
        binding.txvOutNumeroDocumento.setText("Documento: " + data.documento);
        binding.txvOutCorreo.setText("Correo: " + data.correo);
        binding.txvOutTelefono.setText("Teléfono: " + data.telefono);
        binding.txvOutCiudad.setText("Ciudad: " + data.ciudad);
        binding.txvOutProfesion.setText("Profesión: " + data.profesion);
        binding.txvOutEstudios.setText("Estudios: " + data.nivelEstudio);
    }

    public class PersonalData {
        public final String nombre;
        public final String apellido;
        public final String fechaNac;
        public final String genero;
        public final String documento;
        public final String correo;
        public final String telefono;
        public final String ciudad;
        public final String profesion;
        public final String nivelEstudio;

        public PersonalData(String nombre, String apellido, String fechaNac,
                            String genero, String documento, String correo,
                            String telefono, String ciudad, String profesion,
                            String nivelEstudio) {
            this.nombre       = nombre;
            this.apellido     = apellido;
            this.fechaNac     = fechaNac;
            this.genero       = genero;
            this.documento    = documento;
            this.correo       = correo;
            this.telefono     = telefono;
            this.ciudad       = ciudad;
            this.profesion    = profesion;
            this.nivelEstudio = nivelEstudio;
        }
    }
    @Nullable
    private PersonalData leerDatos() {
        String nombre       = binding.IdEdtNombre.getText().toString().trim();
        String apellido     = binding.IdEdtApellido.getText().toString().trim();
        String fechaNac     = binding.IdEdtFechaDeNacimiento.getText().toString().trim();
        String genero       = binding.IdEdtGenero.getText().toString().trim();
        String documento    = binding.IdEdtNumeroDocumento.getText().toString().trim();
        String correo       = binding.IdEdtCorreo.getText().toString().trim();
        String telefono     = binding.IdEdtTelefono.getText().toString().trim();
        String ciudad       = binding.IdEdtCiudad.getText().toString().trim();
        String profesion    = binding.IdEdtProfesion.getText().toString().trim();
        String nivelEstudio = binding.IdEdtNivelEstudio.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || fechaNac.isEmpty() ||
                genero.isEmpty() || documento.isEmpty() || correo.isEmpty() ||
                telefono.isEmpty() || ciudad.isEmpty() || profesion.isEmpty() ||
                nivelEstudio.isEmpty()) {

            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return null;
        }
        return new PersonalData(nombre, apellido, fechaNac, genero, documento,
                correo, telefono, ciudad, profesion, nivelEstudio);
    }
    private void limpiarDatos() {
        List<EditText> inputs = List.of(
                binding.IdEdtNombre,
                binding.IdEdtApellido,
                binding.IdEdtFechaDeNacimiento,
                binding.IdEdtGenero,
                binding.IdEdtNumeroDocumento,
                binding.IdEdtCorreo,
                binding.IdEdtTelefono,
                binding.IdEdtCiudad,
                binding.IdEdtProfesion,
                binding.IdEdtNivelEstudio
        );

        List<TextView> outputs = List.of(
                binding.txvOutNombre,
                binding.txvOutApellido,
                binding.txvOutFechaDeNacimiento,
                binding.txvOutGenero,
                binding.txvOutNumeroDocumento,
                binding.txvOutCorreo,
                binding.txvOutTelefono,
                binding.txvOutCiudad,
                binding.txvOutProfesion,
                binding.txvOutEstudios
        );

        for (EditText e : inputs) e.setText("");
        for (TextView t : outputs) t.setText("—");

        // opcional: devolver foco
        binding.IdEdtNombre.requestFocus();
    }
    
}