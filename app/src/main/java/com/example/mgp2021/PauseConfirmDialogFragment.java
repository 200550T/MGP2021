package com.example.mgp2021;

// Tan Siew Lan

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class PauseConfirmDialogFragment extends DialogFragment {
    public static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        IsShown = true;
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Pause? " +
                "(If game paused, selecting yes will resume game)")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User triggered pause
                        GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                        IsShown = false;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the pause
                        IsShown = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        IsShown = false;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        IsShown = false;
    }
}
