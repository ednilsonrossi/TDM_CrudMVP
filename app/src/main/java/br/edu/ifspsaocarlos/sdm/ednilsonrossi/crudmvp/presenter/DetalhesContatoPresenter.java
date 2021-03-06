package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.presenter;

import android.content.Context;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface.IDetalhesContato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.dao.ContatoDAO;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.excecoes.CampoNaoEncontradoException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.excecoes.DadoNaoEncontradoException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.excecoes.ObjetoNuloException;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.dominio.Contato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view.DetalhesContatoActivity;

public class DetalhesContatoPresenter implements IDetalhesContato.Presenter{

    private IDetalhesContato.View detalheContatoView;
    private ContatoDAO contatoDAO;

    public DetalhesContatoPresenter(IDetalhesContato.View detalheContatoView) {
        this.detalheContatoView = detalheContatoView;
        Context context = ((DetalhesContatoActivity) detalheContatoView).getApplicationContext();
        contatoDAO = new ContatoDAO(context);
    }


    @Override
    public void apresentaDados(int idContato) {
        Contato contato;

        if(idContato != -1){
            try {
                contato = contatoDAO.recuperate(idContato);
                detalheContatoView.mostraContato(contato.getId(), contato.getApelido(), contato.getNomeCompleto());
            } catch (CampoNaoEncontradoException e) {
                detalheContatoView.contatoInexistente();
                e.printStackTrace();
            } catch (DadoNaoEncontradoException e) {
                detalheContatoView.contatoInexistente();
                e.printStackTrace();
            }
        }else{
            detalheContatoView.contatoInexistente();
        }
    }

    @Override
    public void atualizarContato(int id, String apelido, String nome) {
        Contato contato;
        contato = new Contato(id, apelido, nome);
        try {
            contatoDAO.update(contato);
        } catch (ObjetoNuloException e) {
            e.printStackTrace();
            detalheContatoView.contatoInexistente();
        } catch (DadoNaoEncontradoException e) {
            e.printStackTrace();
            detalheContatoView.contatoInexistente();
        }
        detalheContatoView.contatoAtualizadoComSucesso();
    }

    @Override
    public void apagarContato(int id, String apelido, String nome) {
        Contato contato;
        contato = new Contato(id, apelido, nome);
        try {
            contatoDAO.delete(contato);
        } catch (ObjetoNuloException e) {
            detalheContatoView.contatoInexistente();
            e.printStackTrace();
        } catch (DadoNaoEncontradoException e) {
            detalheContatoView.contatoInexistente();
            e.printStackTrace();
        }
        detalheContatoView.contatoApagadoComSucesso();
    }
}
