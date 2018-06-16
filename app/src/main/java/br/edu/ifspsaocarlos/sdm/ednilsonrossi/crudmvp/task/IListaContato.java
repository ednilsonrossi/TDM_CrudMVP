package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.task;

import java.util.List;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.Contato;

public interface IListaContato {

    interface View{
        public void criarContatoAdapter(List list);
    }

    interface Presenter{
        public void popularDadosAdapter();
    }

}
