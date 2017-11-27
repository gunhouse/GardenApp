//package com.regangunhouse.garden;
//
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class MainFragment extends Fragment {
//
//
//    public MainFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_main, container, false);
//
//        return view;
//    }
//
//}




package com.regangunhouse.garden;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }
    GridView gv;

    Integer[] plants = {R.drawable.bluebonnet, R.drawable.corn, R.drawable.lettuce,
            R.drawable.orchid, R.drawable.potatoes, R.drawable.rose,
            R.drawable.sunflower, R.drawable.tomato, R.drawable.watermelon};

    String[] plantsText = {"Bluebonnets", "Corn", "Lettuce",
            "Orchids", "Potatoes", "Roses",
            "Sunflowers", "Tomatoes", "Watermelon"};

    String[] plantsDescText = {"Texas State Flower", "Also known as Maize", "Something about lettuce",
            "Great Gift", "Last through a famine", "Valentines",
            "Shine Bright", "Pizza sauce", "Seedless is convenient"};

    List<Integer> plantsList = new ArrayList<Integer>(Arrays.asList(plants));
    List<String> plantsTextList = new ArrayList<String>(Arrays.asList(plantsText));
    List<String> plantsDescTextList = new ArrayList<String>(Arrays.asList(plantsDescText));

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button btn = (Button) view.findViewById(R.id.button2);


        gv = view.findViewById(R.id.gv);
        final TextView tv = view.findViewById(R.id.tv);
        final EditText edit = view.findViewById(R.id.button1);




        class ImageAdapter extends BaseAdapter {
            private Context mContext;

            public ImageAdapter(Context mContext) {
                this.mContext = mContext;
            }



            @Override
            public int getCount() {
                return plants.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;

                if (convertView == null) {
                    imageView= new ImageView(mContext);
                    imageView.setLayoutParams(new GridView.LayoutParams(250, 250));

                } else {
                    imageView = (ImageView) convertView;
                }

                imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
                //imageView.setImageResource(plants[position]);
                imageView.setImageResource(plantsList.get(position));
                return imageView;
            }
        }
        final ImageAdapter imageAdapter = new ImageAdapter(getActivity());
        gv.setAdapter(imageAdapter);
        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                plantsList.add(plantsList.size(),R.drawable.bluebonnet);
                plantsTextList.add(plantsTextList.size(), edit.getText().toString());
                plantsDescTextList.add(plantsDescTextList.size(), "Default Description");




                //This is where I am struggling, it updates the 3 list but doesnt refresh the gridview
                final ImageAdapter imageAdapter2 = new ImageAdapter(getActivity());
                gv.setAdapter(imageAdapter2);
                gv.invalidateViews();
                Toast.makeText(getActivity(), plantsDescTextList.get(plantsDescTextList.size()-1), Toast.LENGTH_LONG).show();
            }
        });


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Display the selected/clicked item text and position on TextView
                //  tv.setText(plantsTextList.get(position) + "\n" + plantsDescTextList.get(position));

                String plant = plantsTextList.get(position); // String of the name of the plant

                Bundle bundle = new Bundle();
                bundle.putString("PlantName", plant);

                FragmentManager fragManage = getActivity().getSupportFragmentManager();
                FragmentTransaction fragTran = fragManage.beginTransaction();

                PlantInfoFragment pif = new PlantInfoFragment();
                pif.setArguments(bundle);

                fragTran.replace(R.id.fragment_container,pif);
                fragTran.commit();

            }
        });

        return view;
    }



}
