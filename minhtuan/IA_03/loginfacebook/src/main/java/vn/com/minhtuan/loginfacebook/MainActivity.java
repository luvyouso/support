package vn.com.minhtuan.loginfacebook;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private EditText mEmailOrPhone;
    private EditText mPassword;
    private LinearLayout mLinearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mLinearlayout = (LinearLayout) findViewById(R.id.IDLinearLayout);
        mEmailOrPhone = (EditText) findViewById(R.id.txtEmailOrPhone);
        mPassword = (EditText) findViewById(R.id.txtPassword);

    }

    public void ShowKeyboardClick(View v) {
        InputMethodManager input = (InputMethodManager) getSystemService((Context.INPUT_METHOD_SERVICE));
        input.showSoftInput(mEmailOrPhone, InputMethodManager.SHOW_IMPLICIT);
        input.showSoftInput(mPassword, InputMethodManager.SHOW_IMPLICIT);
    }

    public void HideKeyboardClick(View v) {
        InputMethodManager input = (InputMethodManager) getSystemService((Context.INPUT_METHOD_SERVICE));
        input.hideSoftInputFromWindow(mLinearlayout.getWindowToken(), 0);
    }
}
