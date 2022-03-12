package com.milesilac.whackamolu;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


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

    private Dialog menuCustomPlay, menuScore, menuCredits;

    private int countdownDialogTimer;


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

        //menuCustomPlay Dialog
        menuCustomPlay = new Dialog(getContext());
        menuCustomPlay.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuCustomPlay.setContentView(R.layout.custom_dialog);
        menuCustomPlay.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(),R.drawable.dialog_background));
        menuCustomPlay.setCancelable(false);

        Button btnCustomPlay = requireView().findViewById(R.id.menubtn2);
        Button btn15sPlay = menuCustomPlay.findViewById(R.id.secs15);
        Button btn30sPlay = menuCustomPlay.findViewById(R.id.secs30);
        Button btn60sPlay = menuCustomPlay.findViewById(R.id.secs60);
        Button btnUntimedPlay = menuCustomPlay.findViewById(R.id.secsNull);
        Button btnCustomBack = menuCustomPlay.findViewById(R.id.btnBack);

        btnCustomPlay.setOnClickListener(v -> menuCustomPlay.show()); //show custom game menu

        btn15sPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();

            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,true);
            args.putInt(SET_TIMER,15);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        }); //choose 15 seconds of play

        btn30sPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();

            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,true);
            args.putInt(SET_TIMER,30);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        }); //choose 30 seconds of play

        btn60sPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();

            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,true);
            args.putInt(SET_TIMER,60);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        }); //choose 1 minute of play

        btnUntimedPlay.setOnClickListener(v -> {
            menuCustomPlay.dismiss();

            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,false);

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        }); //choose untimed play

        btnCustomBack.setOnClickListener(v -> menuCustomPlay.dismiss()); //close custom game menu

        //menuScore Dialog
        menuScore = new Dialog(getContext());
        menuScore.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuScore.setContentView(R.layout.scoreboard_dialog);
        menuScore.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(),R.drawable.dialog_background));
        menuScore.setCancelable(false);

        Button btnScore = requireView().findViewById(R.id.menubtn3);
        TextView highestScoreTimed = menuScore.findViewById(R.id.highestScoreTimed);
        TextView highestScoreUntimed = menuScore.findViewById(R.id.highestScoreUntimed);
        btnCustomBack = menuScore.findViewById(R.id.btnBack);

        highestScoreTimed.setText(MainActivity.savedTimedScore);
        highestScoreUntimed.setText(MainActivity.savedUntimedScore);

        btnScore.setOnClickListener(v -> menuScore.show()); //show scoreboard
        btnCustomBack.setOnClickListener(v -> menuScore.dismiss()); //close scoreboard

        //menuCredits Dialog
        menuCredits = new Dialog(getContext());
        menuCredits.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuCredits.setContentView(R.layout.credits_dialog);
        menuCredits.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(),R.drawable.dialog_background));
        menuCredits.setCancelable(false);

        Button btnCredits = requireView().findViewById(R.id.menubtn4);
        Button btnGit = menuCredits.findViewById(R.id.btnGit);
        btnCustomBack = menuCredits.findViewById(R.id.btnBack);

        btnCredits.setOnClickListener(v -> menuCredits.show()); //show credits

        //view Github in local browser
        btnGit.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MilesIlac/whack-a-molu/tree/basic"));
            startActivity(browserIntent);
        });

        btnCustomBack.setOnClickListener(v -> menuCredits.dismiss()); //close credits


        Button btnQuit = requireView().findViewById(R.id.menubtn5);
        btnQuit.setOnClickListener(v -> requireActivity().finish());

    } //onViewCreated
}