//package com.example.smartt;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class Adapter extends RecyclerView.Adapter<Adapter.HolderView>  {
//    ArrayList arrayList = new ArrayList();
//
//    public Adapter(ArrayList arrayList) {
//        this.arrayList = arrayList;
//    }
//
//    @NonNull
//    @Override
//    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sensors,parent,false);
//        return new HolderView(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull HolderView holder, int position) {
//        SensorsValue sensorsValue = (SensorsValue) arrayList.get(position);
//        holder.temperature1.setText(String.format("Temperature : %s",sensorsValue.getTemperature()));
//        holder.humidity1.setText(String.format("Humidity : %s",sensorsValue.getHumidity()));
//        holder.water1.setText(String.format("Water : %s",sensorsValue.getWater()));
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return arrayList.size();
//    }
//
//    class HolderView extends RecyclerView.ViewHolder {
//        TextView temperature1, humidity1,water1;
//        public HolderView(@NonNull View itemView) {
//            super(itemView);
//            temperature1  = itemView.findViewById(R.id.Temperature);
//            humidity1  = itemView.findViewById(R.id.Humidity);
//            water1  = itemView.findViewById(R.id.Water);
//        }
//    }
//}
