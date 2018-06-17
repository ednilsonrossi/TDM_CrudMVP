package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.presenter;

import android.content.Context;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.dao.ContatoDAO;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.excecoes.ObjetoNuloException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.Contato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface.ICadastraContato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view.CadastraContatoActivity;

public class PresenterCadastraContato implements ICadastraContato.Presenter{

    private ICadastraContato.View cadastraContatoView;
    private ContatoDAO contatoDAO;

    public PresenterCadastraContato(ICadastraContato.View cadastraContatoView) {
        this.cadastraContatoView = cadastraContatoView;
        Context context = ((CadastraContatoActivity) cadastraContatoView).getApplicationContext();
        contatoDAO = new ContatoDAO(context);
    }

    @Override
    public void gravarContato(String apelido, String nome) {
        Contato contato;
        contato = new Contato(apelido, nome);
        try {
            contatoDAO.create(contato);
            cadastraContatoView.cadastroComSucesso();
        } catch (ObjetoNuloException e) {
            e.printStackTrace();
            cadastraContatoView.cadastroSemSucesso();
        }
    }
}
