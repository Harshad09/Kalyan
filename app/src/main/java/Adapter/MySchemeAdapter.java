package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schemes.MySchemePerticularScheme;
import com.example.schemes.R;

import java.util.List;

import Model.MySchemeModel;

public class MySchemeAdapter extends RecyclerView.Adapter<MySchemeAdapter.MySchemeAdapterViewHolder> {

    private Context mcontext;
    private List<MySchemeModel> entity;


    public MySchemeAdapter(Context mcontext, List<MySchemeModel> entity) {
        this.mcontext = mcontext;
        this.entity = entity;

    }

    @NonNull
    @Override
    public MySchemeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rootView = LayoutInflater.from(mcontext).inflate(R.layout.myscheme_feed,parent,false);
        return new MySchemeAdapterViewHolder(rootView,mcontext,entity);


    }

    @Override
    public void onBindViewHolder(@NonNull MySchemeAdapterViewHolder holder, int position) {
        MySchemeModel educationModel = entity.get(position);
        holder.scheme_name.setText(educationModel.getName());
        holder.department_name.setText(educationModel.getDepartment());
    }


    @Override
    public int getItemCount() {
        return entity.size();
    }



    public class MySchemeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView scheme_name,department_name;
        Context context;
        List<MySchemeModel> entity;

        public MySchemeAdapterViewHolder(@NonNull View itemView,Context mcontext,List<MySchemeModel> entity) {
            super(itemView);
            scheme_name = itemView.findViewById(R.id.my);
            department_name = itemView.findViewById(R.id.mySchemedepartment);
            itemView.setOnClickListener(this);
            this.context = mcontext;
            this.entity = entity;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MySchemePerticularScheme.class);
            intent.putExtra("name",entity.get(getAdapterPosition()).getName());
            intent.putExtra("department",entity.get(getAdapterPosition()).getDepartment());
            intent.putExtra("overview",entity.get(getAdapterPosition()).getOverview());
            intent.putExtra("benefits",entity.get(getAdapterPosition()).getBenefits());
            intent.putExtra("documentRequired",entity.get(getAdapterPosition()).getDocumentRequired());
            intent.putExtra("link",entity.get(getAdapterPosition()).getLink());
            context.startActivity(intent);

        }
    }
}

