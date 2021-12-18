package com.example.crudsqlitedasar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class FromActivity extends AppCompatActivity {
    private EditText etUser;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etNomorKontak;
    private Button btnSimpan;
    private DatabaseHandler db = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from);

        etUser = findViewById(R.id.editTextUser);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);
        etNomorKontak = findViewById(R.id.editTextKontak);
        btnSimpan = findViewById(R.id.buttonSimpan);

        Intent intent = getIntent();
        String aksi = intent.getStringExtra("aksi");
        Integer idUser = intent.getIntExtra("id_user", 0);
        String namaUser = intent.getStringExtra("nama_user");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        String nomorkontak = intent.getStringExtra("nomor_kontak");

        Spinner spinner = (Spinner) findViewById(R.id.spinnerProdi);
        //  Buatlah ArrayAdapter untuk memanggil data spinner Program Studi
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dataProdi, android.R.layout.simple_spinner_item);
        // Layout dasar yang ditampilkan ketika data prodi setelah dipilih
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Adapter ke Spinner
        spinner.setAdapter(adapter);

        setData(namaUser, email, password, nomorkontak);
        
        validasiInput();

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aksi.equalsIgnoreCase("input")) {

                    insert();
                } else {
                    update(idUser);
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setData(String namaUser, String email, String password, String nomorkontak) {
        etUser.setText(namaUser);
        etEmail.setText(email);
        etPassword.setText(password);
        etNomorKontak.setText(nomorkontak);
    }

    private void validasiInput() {
    }

    private void update(Integer idUser) {
        User u = new User();
        u.setNamaUser(etUser.getText().toString());
        u.setEmail(etEmail.getText().toString());
        u.setPassword(etPassword.getText().toString());
        u.setNomorkontak(etNomorKontak.getText().toString());
        u.setIdUser(idUser);
        db.updateUser(u);
    }

    private void insert() {
        User u = new User();
        u.setNamaUser(etUser.getText().toString());
        u.setEmail(etEmail.getText().toString());
        u.setPassword(etPassword.getText().toString());
        u.setNomorkontak(etNomorKontak.getText().toString());
        db.addUser(u);
    }
}