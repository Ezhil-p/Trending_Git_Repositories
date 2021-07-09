package com.example.trendinggitrepositories;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {
    private ArrayList<Data>list;
    private ArrayList<Data>backup;

    private RecyclerViewClick recyclerViewClick;
    public Adapter(ArrayList<Data>list,RecyclerViewClick recyclerViewClick)
    {

        this.list = list;
        backup=new ArrayList<>(list);
       this.recyclerViewClick=recyclerViewClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String adapter_bind_name=list.get(position).getName();
        String adapter_bind_desc=list.get(position).getDescription();
        String adapter_bind_language=list.get(position).getLanguage();
        String adapter_bind_stars=list.get(position).getStars();
        String adapter_bind_languagecolor=list.get(position).getLanguageColor();

holder.setData(adapter_bind_name,adapter_bind_desc,adapter_bind_language,adapter_bind_stars,adapter_bind_languagecolor);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView desc;
        private TextView language;
        private TextView stars;
        private ImageView languagecolor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.list_item_name);

            desc=itemView.findViewById(R.id.list_item_desc);
            language=itemView.findViewById(R.id.list_item_language);
            stars=itemView.findViewById(R.id.list_item_stars);
            languagecolor = (ImageView)itemView.findViewById(R.id.list_item_languagecolor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    recyclerViewClick.onItemclick(getAdapterPosition());

                }
            });

        }

        public void setData(String adapter_bind_name,String adapter_bind_desc,String adapter_bind_language,String adapter_bind_stars,String adapter_bind_languagecolor) {
            name.setText(adapter_bind_name);
            desc.setText(adapter_bind_desc);
            language.setText(adapter_bind_language);
            stars.setText(adapter_bind_stars);
           languagecolor.setColorFilter(Color.parseColor(adapter_bind_languagecolor));



        }
    }

    @Override
    public Filter getFilter() {

        return filter;
    }
    Filter filter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
           ArrayList<Data>filtereddata=new ArrayList<>();
           if(keyword.toString().isEmpty())
           {
               filtereddata.addAll(backup);
           }
           else {
               for(Data obj:backup)
               {
                   if(obj.getName().toString().toLowerCase().contains(keyword.toString().toLowerCase())||
                           obj.getDescription().toString().toLowerCase().contains(keyword.toString().toLowerCase())
                   ||obj.getLanguage().toString().toLowerCase().contains(keyword.toString().toLowerCase())||
                           obj.getStars().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                   {
                       filtereddata.add(obj);
                   }
               }
           }
           FilterResults result=new FilterResults();
           result.values=filtereddata;
           return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<Data>)results.values);
            notifyDataSetChanged();


        }
    };



}
