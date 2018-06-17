package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface;

public interface IDetalhesContato {

    interface View{
        public void contatoInexistente();

        public void mostraContato(int id, String apelido, String nome);

        public void contatoAtualizadoComSucesso();

        public void contatoApagadoComSucesso();
    }

    interface Presenter{
        public void apresentaDados(int idContato);

        public void atualizarContato(int id, String apelido, String nome);

        public void apagarContato(int id, String apelido, String nome);
    }
}
