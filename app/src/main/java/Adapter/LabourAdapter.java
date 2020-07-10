package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.schemes.Labour;
import com.example.schemes.LabourPerticularScheme;
import com.example.schemes.R;

import java.util.ArrayList;
import java.util.List;

import Model.LabourModel;

public class LabourAdapter extends Adapter <LabourAdapter.LabourAdapterViewHolder> implements Filterable {

    private Context mcontext;
    private List<LabourModel> entity;
    private List<LabourModel> entityFilter;

    public LabourAdapter(Context mcontext, List<LabourModel> entity) {
        this.mcontext = mcontext;
        this.entity = entity;
        entityFilter = new ArrayList<>(entity);
    }

    @NonNull
    @Override
    public LabourAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rootView = LayoutInflater.from(mcontext).inflate(R.layout.labour_feed,parent,false);
        return new LabourAdapterViewHolder(rootView,mcontext,entity);


    }

    @Override
    public void onBindViewHolder(@NonNull LabourAdapterViewHolder holder, int position) {
        LabourModel labourModel = entity.get(position);
        holder.scheme_name.setText(labourModel.getName());
    }


    @Override
    public int getItemCount() {
        return entity.size();
    }

    public void setEntityFilter(List<LabourModel> entity) {
        this.entityFilter = new ArrayList<>(entity);
        notifyDataSetChanged();
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<LabourModel> filteredList = new ArrayList<>();
            if (constraint.equals(null) || constraint.length() == 0)
            {
//                Toast.makeText(mcontext, "null", Toast.LENGTH_SHORT).show();
                filteredList.addAll(entityFilter);
            }
            else {
                String input = constraint.toString().toLowerCase().trim();
                for (LabourModel item: entity) {
                    if (item.getName().toLowerCase().contains(input) ) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            entity.clear();
            entity.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class LabourAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView scheme_name;
        Context context;
        List<LabourModel> entity;

        public LabourAdapterViewHolder(@NonNull View itemView,Context mcontext,List<LabourModel> entity) {
            super(itemView);
            scheme_name = itemView.findViewById(R.id.scheme);
            itemView.setOnClickListener(this);
            this.context = mcontext;
            this.entity = entity;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, LabourPerticularScheme.class);
            intent.putExtra("scheme",entity.get(getAdapterPosition()).getName());
            intent.putExtra("info",entity.get(getAdapterPosition()).getInfo());
            intent.putExtra("link",entity.get(getAdapterPosition()).getLink());
            context.startActivity(intent);

        }
    }
}
