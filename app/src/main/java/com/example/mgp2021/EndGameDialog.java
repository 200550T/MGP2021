package com.example.mgp2021;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class EndGameDialog extends DialogFragment
{
    public static boolean IsShown = false;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        IsShown = true;
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("You Win!");
        builder.setMessage("Congratulations! You defeated the Glitch and saved cyberspace!\nYour score was: " + RenderTextEntity.score + "!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GamePage.Instance.ChangeToMenu();
                        StateManager.Instance.ChangeState("Mainmenu");
                        //AudioManager.Instance.StopAudio(R.raw.shoot);
                        IsShown = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
