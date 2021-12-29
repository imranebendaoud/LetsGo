package com.example.letsgo;

import com.example.letsgo.model.Annonce;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter  extends BaseAdapter implements Filterable {

    private List<Annonce> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Annonce> listDataFiltred;


    public CustomListAdapter(Context aContext,  List<Annonce> listData) {
        this.context = aContext;
        this.listData = listData;
        this.listDataFiltred= listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listDataFiltred.size();
    }

    @Override
    public Object getItem(int position) {
        return listDataFiltred.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_list_annonces, null);
            holder = new ViewHolder();
            holder.annonceImage = (ImageView) convertView.findViewById(R.id.annonceImageList);
            holder.annonceTitle = (TextView) convertView.findViewById(R.id.textView3);
            holder.annonceDescription = (TextView) convertView.findViewById(R.id.textView10);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Annonce annonce = this.listDataFiltred.get(position);
        holder.annonceTitle.setText("Title : "+ annonce.getTitre());
        holder.annonceDescription.setText("Description : " + annonce.getDescription());
        Picasso.with(context).load(annonce.getImages_url()).resize(100,100).into(holder.annonceImage);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = listData.size();
                    filterResults.values = listData;

                }else{
                    List<Annonce> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(Annonce itemsModel:listData){
                        if(itemsModel.getTitre().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listDataFiltred = (List<Annonce>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


    static class ViewHolder {
        ImageView annonceImage;
        TextView annonceTitle;
        TextView annonceDescription;
    }

}