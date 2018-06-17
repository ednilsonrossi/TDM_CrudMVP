package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view.adapter.ContatoAdapter;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.dao.ContatoDAO;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.excecoes.DadoNaoEncontradoException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.dominio.Contato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface.IListaContato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view.ListaContatosActivity;

public class ListaContatoPresenter implements IListaContato.Presenter{

    private IListaContato.View listaContatoView;
    private ContatoDAO contatoDAO;


    public ListaContatoPresenter(IListaContato.View listaContatoView){
        this.listaContatoView = listaContatoView;
        Context context = (ListaContatosActivity) listaContatoView;
        contatoDAO = new ContatoDAO(context.getApplicationContext());
    }

    @Override
    public void popularDadosAdapter() {
        List<Contato> retorno;
        try {
            retorno = contatoDAO.recuperate();
        } catch (DadoNaoEncontradoException e) {
            retorno = new ArrayList<>();
            e.printStackTrace();
        }
        listaContatoView.criarContatoAdapter(retorno);
    }

    @Override
    public void atualizarDadosAdapter(ContatoAdapter adapter) {
        List<Contato> retorno;
        try {
            retorno = contatoDAO.recuperate();
        } catch (DadoNaoEncontradoException e) {
            retorno = new ArrayList<>();
            e.printStackTrace();
        }
        adapter.atualizarDados(retorno);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recuperaObjetoAdapter(ContatoAdapter adapter, int position) {
        Contato c;
        c = adapter.getObject(position);
        listaContatoView.exibirDetalhesContato(c.getId());
    }


}
