package com.milesilac.whackemoji;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.DialogFragment;

public class MenuCreditsDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog menuCredits = new Dialog(getContext());
        menuCredits.requestWindowFeature(Window.FEATURE_NO_TITLE);
        menuCredits.setContentView(R.layout.credits_dialog);
        menuCredits.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(),R.drawable.dialog_background));
        menuCredits.setCancelable(false);

        Button btnGit = menuCredits.findViewById(R.id.btnGit);
        Button btnCustomBack = menuCredits.findViewById(R.id.btnBack);

        //view Github in local browser
        btnGit.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/MilesIlac/whack-a-molu/tree/basic"));
            startActivity(browserIntent);
        });

        btnCustomBack.setOnClickListener(v -> menuCredits.dismiss()); //close credits

        return menuCredits;


//        return super.onCreateDialog(savedInstanceState);
    }

    public static String TAG = "MenuCreditsDialog";

}
