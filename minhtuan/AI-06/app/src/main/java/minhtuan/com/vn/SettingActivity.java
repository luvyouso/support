package minhtuan.com.vn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnApDung;
    private RadioButton radioButtonBlack;
    private RadioButton radioButtonWhite;

    private RadioButton radioButtonDauCham;
    private RadioButton radioButtonDauPhay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1.Load layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //2.Read view objects
        LoadSuViews();

        //3.Setup event handlers
        setUpEventHandlers();

        //4.Settings
        Settings();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnAbout:
                gotoAboutScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void gotoAboutScreen() {
        Intent intentA = new Intent(this, AboutActivity.class);
        startActivity(intentA);
    }

    private void LoadSuViews() {
        btnApDung = (Button) findViewById(R.id.btnApDung);
        radioButtonBlack = (RadioButton) findViewById(R.id.radioButtonBlack);
        radioButtonWhite = (RadioButton) findViewById(R.id.radioButtonWhite);
        radioButtonDauCham = (RadioButton) findViewById(R.id.radioButtonDauCham);
        radioButtonDauPhay = (RadioButton) findViewById(R.id.radioButtonDauPhay);
    }

    private void setUpEventHandlers() {
        btnApDung.setOnClickListener(this);
    }

    private void Settings() {
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String text = extra.getString(CalculatorActivity.NUMBER_KEY, "0");
            String[] txt = text.split(",");
            if (txt[0].equals("BLACK")) {
                radioButtonBlack.setChecked(true);
                if (txt[1].equals("DauCham"))
                    radioButtonDauCham.setChecked(true);
                else
                    radioButtonDauPhay.setChecked(true);
            } else {
                radioButtonWhite.setChecked(true);
                if (txt[1].equals("DauCham"))
                    radioButtonDauCham.setChecked(true);
                else
                    radioButtonDauPhay.setChecked(true);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnApDung:
                gotoCalculatorScreen();
                break;
            default:
                break;
        }
    }

    private void gotoCalculatorScreen() {
        Intent intent = new Intent(this, CalculatorActivity.class);

        if (radioButtonBlack.isChecked()) {
            if (radioButtonDauCham.isChecked())
                intent.putExtra(CalculatorActivity.NUMBER_KEY, "BLACK,DauCham");
            else
                intent.putExtra(CalculatorActivity.NUMBER_KEY, "BLACK,DauPhay");
        } else if (radioButtonWhite.isChecked()) {
            if (radioButtonDauCham.isChecked())
                intent.putExtra(CalculatorActivity.NUMBER_KEY, "WHITE,DauCham");
            else
                intent.putExtra(CalculatorActivity.NUMBER_KEY, "WHITE,DauPhay");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
