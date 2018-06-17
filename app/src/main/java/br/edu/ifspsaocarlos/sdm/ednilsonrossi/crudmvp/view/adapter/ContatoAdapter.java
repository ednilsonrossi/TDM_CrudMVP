package br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.Interface.IRecyclerViewOnClickListener;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.R;
import br.edu.ifspsaocarlos.sdm.ednilsonrossi.crudmvp.model.dominio.Contato;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ViewHolder>{

    private List<Contato> contatoList;
    private LayoutInflater inflater;
    private IRecyclerViewOnClickListener recyclerViewOnClickListener;

    public ContatoAdapter(Context context, List<Contato> contatoList){
        this.contatoList = contatoList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void atualizarDados(List<Contato> contatoList){
        this.contatoList = contatoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.celula_lista, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.apelidoTextView.setText(contatoList.get(position).getApelido());
        holder.nomeTextView.setText(contatoList.get(position).getNomeCompleto().trim());
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    public void setRecyclerViewOnClickListener(IRecyclerViewOnClickListener r){
        recyclerViewOnClickListener = r;
    }

    public Contato getObject(int position){
        return contatoList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nomeTextView;
        public TextView apelidoTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            nomeTextView = (TextView) itemView.findViewById(R.id.tv_nome);
            apelidoTextView = (TextView) itemView.findViewById(R.id.tv_apelido);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(recyclerViewOnClickListener != null){
                recyclerViewOnClickListener.onClickListener(view, getAdapterPosition());
            }
        }
    }
}
