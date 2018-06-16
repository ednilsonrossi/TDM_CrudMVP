package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.presenter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.dao.ContatoDAO;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.excecoes.DadoNaoEncontradoException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.Contato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.task.IListaContato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view.ListaContatosActivity;

public class PresenterContato implements IListaContato.Presenter{

    private IListaContato.View listaContatoView;
    private ContatoDAO contatoDAO;


    public PresenterContato(IListaContato.View listaContatoView){
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
}
