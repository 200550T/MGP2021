package com.example.mgp2021;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class GameOverDialog extends DialogFragment{
    public static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        IsShown = true;
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Game Over");
        builder.setMessage("Your score was: " + RenderTextEntity.score + "!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //StateManager.Instance.ChangeState("Mainmenu");
                        GamePage.Instance.ChangeToMenu();
                        IsShown = false;
                    }
                });
/*                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        StateManager.Instance.ChangeState("Mainmenu");
                        IsShown = false;
                    }
                });*/
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
