package com.example.mgp2021;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.app.DialogFragment;

public class ExitConfirmDialog extends DialogFragment {

    public static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        IsShown = true;
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User triggered exit
                        //GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIspaused());
                        System.exit(0);
                        IsShown = false;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the exit
                        IsShown = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
