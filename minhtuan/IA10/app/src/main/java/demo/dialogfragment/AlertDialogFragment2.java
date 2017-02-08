package demo.dialogfragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class AlertDialogFragment2 extends DialogFragment implements View.OnClickListener {
    Button btnClose;
    EditText EditText;
    TextView TextView;
    Communicator communicator;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator=(Communicator)activity;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View view=inflater.inflate(R.layout.custom_dialog_layout,null);

        getDialog().setTitle("Custom Dialog Title");

        btnClose=(Button) view.findViewById(R.id.sd_btnClose);
        EditText=(EditText) view.findViewById(R.id.sd_editText1);
        TextView=(TextView) view.findViewById(R.id.sd_textView1);

        TextView.setText("\nMessage line1\nMessage line2\n"
                +"Dismiss: Back btn, Close, or touch outside");

        btnClose.setOnClickListener(this);
        setCancelable(false);
        return view;
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.sd_btnClose)
        {
            communicator.onDialogMessage(EditText.getText().toString());
            dismiss();
        }
    }

    interface Communicator{
        public void onDialogMessage(String message);
    }
}
