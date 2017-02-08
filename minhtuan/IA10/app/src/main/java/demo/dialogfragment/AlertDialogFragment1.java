package demo.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;



public class AlertDialogFragment1 extends DialogFragment {
    String msg="";

    public static AlertDialogFragment1 newInstance(int title) {
        AlertDialogFragment1 frag = new AlertDialogFragment1();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putString("message", "Are you sure that you want to quit?");
        args.putInt("icon", R.drawable.ic_menu_end_conversation);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        int icon = getArguments().getInt("icon");
        String message = getArguments().getString("message");

        return new AlertDialog.Builder(getActivity())
                .setIcon(icon)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                msg = "YES " + Integer.toString(whichButton);
                                ((MainActivity) getActivity())
                                        .doPositiveClick(msg);
                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                msg = "NO " + Integer.toString(whichButton);
                                ((MainActivity) getActivity())
                                        .doNegativeClick(msg);
                            }
                        })
                .setNeutralButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                msg = "CANCEL " + Integer.toString(whichButton);
                                ((MainActivity) getActivity())
                                        .doNeutralClick(msg);
                            }
                        }).create();
    }
}
