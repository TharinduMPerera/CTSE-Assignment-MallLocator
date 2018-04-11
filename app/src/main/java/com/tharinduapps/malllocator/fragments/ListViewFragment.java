package com.tharinduapps.malllocator.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tharinduapps.malllocator.R;
import com.tharinduapps.malllocator.activities.MainActivity;
import com.tharinduapps.malllocator.adapters.RecyclerAdapter;
import com.tharinduapps.malllocator.database.DBHelper;

/**
 * Created by tharindu on 3/23/18.
 */

public class ListViewFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView noDataText;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = view.findViewById(R.id.mall_list);
        noDataText = view.findViewById(R.id.noDataText);
        dbHelper = new DBHelper(getActivity());
        layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        initList();

        return  view;
    }

    private void initList(){
        if(dbHelper.getAllMalls().size()>1){
            recyclerView.setVisibility(View.VISIBLE);
            noDataText.setVisibility(View.GONE);
            adapter = new RecyclerAdapter(getActivity());
            recyclerView.setAdapter(adapter);
        } else{
            recyclerView.setVisibility(View.GONE);
            noDataText.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (MainActivity.didDBChange) {
            initList();
        }
    }
}
