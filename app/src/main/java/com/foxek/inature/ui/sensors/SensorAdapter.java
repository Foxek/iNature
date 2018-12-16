package com.foxek.inature.ui.sensors;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxek.inature.R;
import com.foxek.inature.data.model.Sensor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {

    private List<Sensor>                  mSensors;
    private PublishSubject<Sensor>        onClickSubject;

    SensorAdapter(List<Sensor> sensors){
        this.mSensors = sensors;
        onClickSubject = PublishSubject.create();
    }

    Observable<Sensor> getPositionClicks(){
        return onClickSubject;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sensor, viewGroup, false);
        return new SensorAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.sensorName.setText(mSensors.get(i).getName());
        viewHolder.sensorType.setText(mSensors.get(i).getType());
        viewHolder.sensorIcon.setImageResource(viewHolder.itemView.getResources()
                .getIdentifier("com.foxek.inature:drawable/" + mSensors.get(i).getIcon(),null,null));
    }

    @Override
    public int getItemCount() {
        return mSensors.size();
    }

    void addAllSensors(List<Sensor> sensors) {
        mSensors.clear();
        mSensors.addAll(sensors);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.sensot_item_name)
        TextView sensorName;

        @BindView(R.id.sensor_item_description)
        TextView sensorType;

        @BindView(R.id.sensor_item_logo)
        ImageView sensorIcon;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @OnClick(R.id.sensor_item_card)
        @Override
        public void onClick(View view) {
            onClickSubject.onNext(mSensors.get(getAdapterPosition()));
        }
    }
}
