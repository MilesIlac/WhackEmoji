package com.milesilac.whackemoji;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.DialogFragment;

public class MenuScoreDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog menuScore = new Dialog(getContext());
        menuScore.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuScore.setContentView(R.layout.scoreboard_dialog);
        menuScore.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(),R.drawable.dialog_background));
        menuScore.setCancelable(false);

        TextView highestScoreTimed = menuScore.findViewById(R.id.highestScoreTimed);
        TextView highestScoreUntimed = menuScore.findViewById(R.id.highestScoreUntimed);
        Button btnCustomBack = menuScore.findViewById(R.id.btnBack);

        highestScoreTimed.setText(MainActivity.savedTimedScore);
        highestScoreUntimed.setText(MainActivity.savedUntimedScore);

        btnCustomBack.setOnClickListener(v -> menuScore.dismiss()); //close scoreboard


        return menuScore;
    }

    public static String TAG = "MenuScoreDialog";
}
