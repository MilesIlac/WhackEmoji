package com.milesilac.whackamolu;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.DialogFragment;




public class MenuCustomPlayDialogFragment extends DialogFragment {

    private static final String IS_UP = "isUp";
    private static final String IS_TIMED = "isTimed";
    private static final String SET_TIMER = "setTimer";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog menuCustomPlay = new Dialog(getContext());
        menuCustomPlay.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuCustomPlay.setContentView(R.layout.custom_dialog);
        menuCustomPlay.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(),R.drawable.dialog_background));
        menuCustomPlay.setCancelable(false);

        Button btn15sPlay = menuCustomPlay.findViewById(R.id.secs15);
        Button btn30sPlay = menuCustomPlay.findViewById(R.id.secs30);
        Button btn60sPlay = menuCustomPlay.findViewById(R.id.secs60);
        Button btnUntimedPlay = menuCustomPlay.findViewById(R.id.secsNull);
        Button btnCustomBack = menuCustomPlay.findViewById(R.id.btnBack);

        btn15sPlay.setOnClickListener(v -> {
//            menuCustomPlay.dismiss();

            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,true);
            args.putInt(SET_TIMER,15);

            assert getParentFragment() != null;
            getParentFragment().getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        }); //choose 15 seconds of play

        btn30sPlay.setOnClickListener(v -> {
//            menuCustomPlay.dismiss();

            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,true);
            args.putInt(SET_TIMER,30);

            assert getParentFragment() != null;
            getParentFragment().getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        }); //choose 30 seconds of play

        btn60sPlay.setOnClickListener(v -> {
//            menuCustomPlay.dismiss();

            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,true);
            args.putInt(SET_TIMER,60);

            assert getParentFragment() != null;
            getParentFragment().getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        }); //choose 1 minute of play

        btnUntimedPlay.setOnClickListener(v -> {
//            menuCustomPlay.dismiss();

            Bundle args = new Bundle();
            args.putBoolean(IS_UP, true);
            args.putBoolean(IS_TIMED,false);

            assert getParentFragment() != null;
            getParentFragment().getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, GameFragment.class, args)
                    .setReorderingAllowed(true)
                    .commit();
        }); //choose untimed play

        btnCustomBack.setOnClickListener(v -> menuCustomPlay.dismiss()); //close custom game menu

        return menuCustomPlay;
    }

    public static String TAG = "MenuCustomPlayDialog";
}
