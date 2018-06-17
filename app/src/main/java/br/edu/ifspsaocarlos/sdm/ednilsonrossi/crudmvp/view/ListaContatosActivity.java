package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface.IRecyclerViewOnClickListener;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.R;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view.adapter.ContatoAdapter;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.presenter.ListaContatoPresenter;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface.IListaContato;

public class ListaContatosActivity extends AppCompatActivity implements IListaContato.View, IRecyclerViewOnClickListener {

    private IListaContato.Presenter presenter;
    private RecyclerView recyclerView;
    private ContatoAdapter contatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        presenter = new ListaContatoPresenter(this);

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
        this.contatoAdapter.setRecyclerViewOnClickListener(this);
    }

    @Override
    public void exibirDetalhesContato(int id) {
        Intent in = new Intent(this, DetalhesContatoActivity.class);
        in.putExtra("id", id);
        startActivityForResult(in, 1);
    }


    public void novoContato(View view){
        Intent in = new Intent(this, CadastraContatoActivity.class);
        startActivityForResult(in, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0){
            presenter.atualizarDadosAdapter(contatoAdapter);
        }else{
            if (requestCode == 1){
                if(resultCode == 0)
                    presenter.atualizarDadosAdapter(contatoAdapter);
            }
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        //Abrir janela com detalhes do contato
        presenter.recuperaObjetoAdapter(contatoAdapter, position);
    }
}
