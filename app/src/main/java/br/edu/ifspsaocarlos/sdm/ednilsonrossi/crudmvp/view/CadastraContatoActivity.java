package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.R;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.presenter.PresenterCadastraContato;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.task.ICadastraContato;

public class CadastraContatoActivity extends AppCompatActivity implements ICadastraContato.View{

    private EditText nomeEditText;
    private EditText apelidoEditText;

    private ICadastraContato.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_contato);

        presenter = new PresenterCadastraContato(this);

        nomeEditText = (EditText) findViewById(R.id.et_nome_completo_act_cadastra);
        apelidoEditText = (EditText) findViewById(R.id.et_apelido_act_cadastra);
    }

    public void salvarContato(View view){
        String nome, apelido;

        nome = nomeEditText.getText().toString();
        apelido = apelidoEditText.getText().toString();

        presenter.gravarContato(apelido, nome);

        finish();
    }

    @Override
    public void cadastroComSucesso() {
        Toast.makeText(this, "Contato cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cadastroSemSucesso() {
        Toast.makeText(this, "Erro ao cadastrar o contato.", Toast.LENGTH_SHORT).show();
    }
}
