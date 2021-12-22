package com.example.letsgo;

import com.example.letsgo.model.Annonce;
import com.squareup.picasso.Picasso;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter  extends BaseAdapter {

    private List<Annonce> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext,  List<Annonce> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
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
            holder.flagView = (ImageView) convertView.findViewById(R.id.annonceImageList);
            holder.countryNameView = (TextView) convertView.findViewById(R.id.textView3);
            holder.populationView = (TextView) convertView.findViewById(R.id.textView10);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Annonce annonce = this.listData.get(position);
        holder.countryNameView.setText(annonce.getTitre());
        holder.populationView.setText(annonce.getDescription());
        Picasso.with(context).load(annonce.getImages_url()).resize(100,100).into(holder.flagView);


//        int imageId = this.getMipmapResIdByName(annonce.getImages_url());

//        holder.flagView.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
//    public int getMipmapResIdByName(String resName)  {
//        String pkgName = context.getPackageName();
//        // Return 0 if not found.
//        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
//        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
//        return resID;
//    }

    static class ViewHolder {
        ImageView flagView;
        TextView countryNameView;
        TextView populationView;
    }

}