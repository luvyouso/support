package demo.dialogfragment;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Date;

public class MainActivity extends AppCompatActivity implements AlertDialogFragment2.Communicator {
    TextView txtMsg;
    Button btnCustomDialog;
    Button btnAlertDialog;
    Button btnDialogFragment;
    Context activityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityContext = this;

        txtMsg = (TextView) findViewById(R.id.txtMsg);
        btnAlertDialog = (Button) findViewById(R.id.btn_alert_dialog1);
        btnCustomDialog = (Button) findViewById(R.id.btn_custom_dialog);
        btnDialogFragment = (Button) findViewById(R.id.btn_alert_dialog2);

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMyAlertDialog();
            }
        });

        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialogBox();
            }
        });

        btnDialogFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMyAlertDialogFragment();
            }
        });




    }

    private void showMyAlertDialog() {
        DialogFragment dialogFragment = AlertDialogFragment1
                .newInstance(R.string.terminator);
        dialogFragment.show(getFragmentManager(), "MYDIALOGFRAGMENT1");
    }// MakeAlertDialogBox

    private void showCustomDialogBox() {
        android.app.FragmentManager manager=getFragmentManager();
        AlertDialogFragment2 dialog=new AlertDialogFragment2();
        dialog.show(manager,"MYDIALOGFRAGMENT1");
    }

    // -----------------------------------------------------------------------
    private void showMyAlertDialogFragment() {
        DialogFragment dialogFragment = MyAlertDialogFragment
                .newInstance(R.string.title);
        dialogFragment.show(getFragmentManager(), "MYDIALOGFRAGMENT1");
    }


    public void doPositiveClick(Date time) {
        txtMsg.setText("POSITIVE - DialogFragment picked @ " + time);
    }

    public void doPositiveClick(String msg) {
        txtMsg.setText(msg);
    }

    public void doNegativeClick(Date time) {
        txtMsg.setText("NEGATIVE - DialogFragment picked @ " + time);

    }

    public void doNegativeClick(String msg) {
        txtMsg.setText(msg);
    }

    public void doNeutralClick(Date time) {
        txtMsg.setText("NEUTRAL - DialogFragment picked @ " + time);

    }

    public void doNeutralClick(String msg) {
        txtMsg.setText(msg);
    }

    @Override
    public void onDialogMessage(String message){
        txtMsg.setText(message);
    }
}
