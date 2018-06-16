package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.task;

public interface ICadastraContato {
    interface View{
        public void cadastroComSucesso();

        public void cadastroSemSucesso();
    }

    interface Presenter{
        public void gravarContato(String apelido, String nome);
    }
}
