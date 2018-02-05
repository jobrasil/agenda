package com.brasil.joao.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.brasil.joao.agenda.dao.AlunoDAO;
import com.brasil.joao.agenda.modelo.Aluno;

import java.util.List;

//toda aplicação precisa de uma tela, em android tel é : activity
//essa heranca por meio dessa classe : AppCompatActivity determina que nossa classe vai ser uma tela

public class ListaAlunosActivity extends AppCompatActivity {


    // vai rodar esse metodo quando o android tiver criando a tela do celular
    //criou a tela e carregou, ele passa a chamar o oncreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //a classe R é uma classe que referencia a todos os recursos
        //dentro da pasta res, dentro do res/layout temos nossa view
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);

        Button novoAluno = (Button) findViewById(R.id.novo_aluno);
        novoAluno.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //utilizar o android para isso
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                //passar p android
                startActivity(intentVaiProFormulario);
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */
}