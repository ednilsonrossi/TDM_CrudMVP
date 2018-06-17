package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface;

import java.util.List;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view.adapter.ContatoAdapter;

public interface IListaContato {

    interface View{
        public void criarContatoAdapter(List list);

        public void exibirDetalhesContato(int id);
    }

    interface Presenter{
        public void popularDadosAdapter();

        public void atualizarDadosAdapter(ContatoAdapter adapter);

        public void recuperaObjetoAdapter(ContatoAdapter adapter, int position);
    }

}
