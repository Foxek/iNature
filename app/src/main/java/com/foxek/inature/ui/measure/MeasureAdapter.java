package com.foxek.inature.ui.measure;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.data.database.model.Measure;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeasureAdapter extends RecyclerView.Adapter<MeasureAdapter.ViewHolder> {

    private List<Measure> mMeasure;

    MeasureAdapter(List<Measure> dataset) {
        mMeasure = dataset;
    }

    @Override
    public void onBindViewHolder(MeasureAdapter.ViewHolder holder, int position) {

        holder.measureName.setText(mMeasure.get(position).getName());
        holder.measureValue.setText(mMeasure.get(position).getValue());
        holder.measureIcon.setImageResource(holder.itemView.getResources()
                .getIdentifier("com.foxek.inature:drawable/" + mMeasure.get(position).getIcon(),null,null));
    }

    @Override
    public int getItemCount() {
        return mMeasure.size();
    }

    public void addAllMeasures(List<Measure> measures){
        mMeasure.clear();
        mMeasure.addAll(measures);
        notifyDataSetChanged();
    }
//    public void parseAdvertisingData(byte[] data) {
//        int measure,position = 0;
//        for (int i = 0; i < mMeasure.size()*2; i+=2){
//            measure = ((data[i] & 0xff) << 8) | (data[i+1] & 0xff);
//            mMeasure.get(position).setValue(mMeasure.get(position).parseCayennePayload(measure,mMeasure.get(position).name));
//            position++;
//        }
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public MeasureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_measure, parent, false);
        return new MeasureAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.measure_name)
        TextView measureName;

        @BindView(R.id.measure_value)
        TextView measureValue;

        @BindView(R.id.measure_icon)
        ImageView measureIcon;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
