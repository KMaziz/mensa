package com.example.mensas.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.mensas.CheckedListener;
import com.example.mensas.R;
import com.example.mensas.database.Mensa;

import java.util.ArrayList;
import java.util.List;

public class MensaRecyclerViewAdapter extends RecyclerView.Adapter<MensaRecyclerViewAdapter.MensaViewHolder> {


    private static final String TAG = "MensaRecyclerViewAdapter";
    Exception e;
    private List<Mensa> mensen;
    public OnMensaListener mOnMensaListener;
    public List<Mensa> favoritenMensa;
    // on click
    // mcontext to delete
   public  Context mContext;
//    Dialog myDialog;
    public Activity activity;

    public MensaRecyclerViewAdapter(Context mContext, List<Mensa> mensen, OnMensaListener onMensaListener, Activity activity) {
        this.mContext = mContext;
        this.mensen = mensen;
        this.mOnMensaListener = onMensaListener;
        this.activity=activity;
    }

    @NonNull
    @Override
    public MensaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item_mensa, parent, false);

        return new MensaViewHolder(view, mOnMensaListener);
    }
    Exception exception;
    @Override
    public void onBindViewHolder(final MensaViewHolder holder, final int position) {
        Mensa mensa = this.mensen.get(position);
        favoritenMensa = new ArrayList<>();
        holder.mensa_name.setText(mensa.getmName());
        holder.mensa_address.setText(mensa.getmAddr() + " Position: " + String.valueOf(position));
        holder.fav_button.setOnCheckedChangeListener(null);

        if (mensa.getIsFavorite()){
            holder.fav_button.setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.favorite_icon_filled));
            holder.fav_button.setChecked(true);
        }else {
            holder.fav_button.setBackgroundDrawable(ContextCompat.getDrawable(activity, R.drawable.favorite_icon_unfilled));
            holder.fav_button.setChecked(false);


        }
        holder.fav_button.setOnCheckedChangeListener(new CheckedListener(holder,mensen,favoritenMensa,position,this,this.activity));



    }



    @Override
    public int getItemCount() {
        return mensen.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class MensaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mensa_name;
        public TextView mensa_address;
        public ConstraintLayout mensa_item;
        // fav Button
        public ToggleButton fav_button;
        OnMensaListener onMensaListener;

        public MensaViewHolder(View itemView, OnMensaListener onMensaListener) {
            super(itemView);
            //
            mensa_item = (ConstraintLayout) itemView.findViewById(R.id.mensa_item);
            mensa_name = (TextView) itemView.findViewById(R.id.mensa_name);
            mensa_address = (TextView) itemView.findViewById(R.id.mensa_address);

            fav_button = (ToggleButton) itemView.findViewById(R.id.fav_button);


            this.onMensaListener = onMensaListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMensaListener.onMensaClick(getAdapterPosition());
        }
    }

    // make the canteens clickable
    public interface OnMensaListener {
        void onMensaClick(int position);
    }


//    public void saveButtonState(boolean pressed) {
//        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean("TOGGLE_BUTTON_STATE", pressed);
//        editor.commit();
//    }
}
