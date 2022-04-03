package com.milesilac.whackemoji;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String IS_UP = "isUp";
    private static final String IS_TIMED = "isTimed";
    private static final String SET_TIMER = "setTimer";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getParentFragmentManager();

        Button btnQuickPlay = requireView().findViewById(R.id.menubtn1);
        btnQuickPlay.setOnClickListener(v -> {
            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,true);
            args.putInt(SET_TIMER,15);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        });

        Button btnCustomPlay = requireView().findViewById(R.id.menubtn2);
        btnCustomPlay.setOnClickListener(v -> new MenuCustomPlayDialogFragment().show(getChildFragmentManager(),MenuCustomPlayDialogFragment.TAG));

        Button btnScore = requireView().findViewById(R.id.menubtn3);
        btnScore.setOnClickListener(v -> new MenuScoreDialogFragment().show(getChildFragmentManager(),MenuScoreDialogFragment.TAG));

        Button btnCredits = requireView().findViewById(R.id.menubtn4);
        btnCredits.setOnClickListener(v -> new MenuCreditsDialogFragment().show(getChildFragmentManager(),MenuCreditsDialogFragment.TAG));

        Button btnQuit = requireView().findViewById(R.id.menubtn5);
        btnQuit.setOnClickListener(v -> requireActivity().finish());

    } //onViewCreated
}