package develop.software.mylocation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import develop.software.mylocation.R;
import develop.software.mylocation.model.GpsTracking;

public class AdapterListSimple extends RecyclerView.Adapter<AdapterListSimple.ViewHolder> {




    java.util.List<GpsTracking> data;
    Context context;
    String lokasi;


    public AdapterListSimple(Context context, java.util.List<GpsTracking> data){



        this.data = data;
        this.context = context;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kiri, parent, false);


        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.txtLatitude.setText(data.get(position).getLatitude());
        holder.txtLongitude.setText(data.get(position).getLongitude());

        holder.txtTanggal.setText(data.get(position).getTime());
        holder.txtnama.setText(data.get(position).getUsername());









    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder  {
        public TextView txtLatitude,txtLongitude,txtTanggal, txtnama;

        public LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtLatitude = (TextView) itemView.findViewById(R.id.isiLatitude);
            txtLongitude = (TextView) itemView.findViewById(R.id.isiLongitude);

            txtTanggal = (TextView) itemView.findViewById(R.id.isiTanggal);
            txtnama = (TextView) itemView.findViewById(R.id.isiNama);


            parentLayout = (LinearLayout)itemView.findViewById(R.id.parentLayout);

        }

    }


}
