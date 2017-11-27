package com.regangunhouse.garden;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlantInfoFragment extends Fragment {


    public PlantInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plant_info, container, false);

        Bundle bundle = getArguments();

        String plant = bundle.getString("PlantName");

        ImageView iv  = (ImageView) view.findViewById(R.id.image);

        iv.setImageResource(R.drawable.corn);

        // Inflate the layout for this fragment
        return view;
    }

}
