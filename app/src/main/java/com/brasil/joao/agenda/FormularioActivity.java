package com.brasil.joao.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brasil.joao.agenda.dao.AlunoDAO;
import com.brasil.joao.agenda.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Button botaoSalvar = (Button) findViewById(R.id.formulario_salvar);

        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_alunos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_formulario_ok:
                save();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void save(){
        Aluno aluno = helper.pegarAluno();
        AlunoDAO dao = new AlunoDAO(this);
        dao.insere(aluno);
        dao.close();

        Toast.makeText(FormularioActivity.this, "Aluno: "+aluno.getNome()+" Salvo!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
