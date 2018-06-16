package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.R;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.adapter.ContatoAdapter;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.presenter.PresenterContato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.task.IListaContato;

public class ListaContatosActivity extends AppCompatActivity implements IListaContato.View{

    private IListaContato.Presenter presenter;
    private RecyclerView recyclerView;
    private ContatoAdapter contatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        presenter = new PresenterContato(this);

        presenter.popularDadosAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.rv_lista_contatos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contatoAdapter);


    }

    public void criarContatoAdapter(List list){
        this.contatoAdapter = new ContatoAdapter(this, list);
    }

    public void novoContato(View view){
        Intent in = new Intent(this, CadastraContatoActivity.class);
        startActivity(in);
    }
}
