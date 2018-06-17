package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.R;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface.IDetalhesContato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.presenter.DetalhesContatoPresenter;

public class DetalhesContatoActivity extends AppCompatActivity implements IDetalhesContato.View{

    private TextView idTextView;
    private EditText nomeEditText;
    private EditText apelidoEditText;

    private IDetalhesContato.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_contato);

        idTextView = (TextView) findViewById(R.id.tv_id_act_detalhe);
        nomeEditText = (EditText) findViewById(R.id.et_nome_completo_act_detalhe);
        apelidoEditText = (EditText) findViewById(R.id.et_apelido_act_detalhe);

        presenter = new DetalhesContatoPresenter(this);

        Intent in = getIntent();
        int id = in.getIntExtra("id", -1);

        presenter.apresentaDados(id);
    }

    public void atualizaContato(View view){
        int id = Integer.parseInt(idTextView.getText().toString());
        String nome = nomeEditText.getText().toString();
        String apelido = apelidoEditText.getText().toString();

        presenter.atualizarContato(id, apelido, nome);
    }

    public void apagaContato(View view){
        int id = Integer.parseInt(idTextView.getText().toString());
        String nome = nomeEditText.getText().toString();
        String apelido = apelidoEditText.getText().toString();

        presenter.apagarContato(id, apelido, nome);
    }

    @Override
    public void contatoInexistente() {
        Toast.makeText(this, "Contato informado não existe.", Toast.LENGTH_SHORT).show();
        finalizar(-1);
    }

    @Override
    public void mostraContato(int id, String apelido, String nome) {
        idTextView.setText(String.valueOf(id));
        nomeEditText.setText(nome);
        apelidoEditText.setText(apelido);
    }

    @Override
    public void contatoAtualizadoComSucesso() {
        Toast.makeText(this, "Contato atualizado com sucesso.", Toast.LENGTH_SHORT).show();
        finalizar(0);
    }

    @Override
    public void contatoApagadoComSucesso() {
        Toast.makeText(this, "Contato apagado com sucesso.", Toast.LENGTH_SHORT).show();
        finalizar(0);
    }

    private void finalizar(int code){
        setResult(code);
        finish();
    }
}
