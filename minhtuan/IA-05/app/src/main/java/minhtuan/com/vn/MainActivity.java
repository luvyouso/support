package minhtuan.com.vn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnCallSale;
    private ImageButton btnSendSMS;
    private ImageButton btnSendEmail;
    private ImageButton btnShareText;
    private ImageButton btnGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //1. Load layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. Find view by id
        LoadSuViews();

        //3. Setup event handlers
        setUpEventHandlers();
    }

    private void LoadSuViews() {
        btnCallSale = (ImageButton) findViewById(R.id.btnCallSale);
        btnSendSMS = (ImageButton) findViewById(R.id.btnSendSMS);
        btnSendEmail = (ImageButton) findViewById(R.id.btnSendEmail);
        btnShareText = (ImageButton) findViewById(R.id.btnShareText);
        btnGoogleMap = (ImageButton) findViewById(R.id.btnGoogleMap);
    }

    private void setUpEventHandlers() {
        btnCallSale.setOnClickListener(this);
        btnSendSMS.setOnClickListener(this);
        btnSendEmail.setOnClickListener(this);
        btnGoogleMap.setOnClickListener(this);
        btnShareText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCallSale) {
            goToCallSaleScreen();
        } else if (v == btnSendSMS) {
            goToSendSMSScreen();
        } else if (v == btnSendEmail) {
            goToSendEmailScreen();
        } else if (v == btnShareText) {
            goToShareScreen();
        } else if (v == btnGoogleMap) {
            goToGoogleMap();
        }
    }

    private void goToCallSaleScreen() {
        String myPhoneNumber = "tel:0938030183";

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(myPhoneNumber));

        startActivity(intent);
    }

    private void goToSendSMSScreen() {
        String myPhoneNumber = "smsto:0938030183";

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(myPhoneNumber));
        intent.putExtra("sms_body", "Xin chào! Bộ phận bán hàng");

        startActivity(intent);
    }

    private void goToSendEmailScreen() {
        String emailSubject = "[Khách hàng] Bộ phận bán hàng";
        String emailText = "Xin chào! Bộ phận bán hàng";
        String[] emailReceiverList = {"caybangmuathuqn@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("vnd.android.cursor.dir/email");
        intent.putExtra(Intent.EXTRA_EMAIL, emailReceiverList);
        intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        intent.putExtra(intent.EXTRA_TEXT, emailText);

        startActivity(Intent.createChooser(intent, "To complete action choose:"));
    }

    private void goToShareScreen() {
        String text = "6C Trần Kế Xương, Quận Bình Thạnh, TP. Hồ Chí Minh";

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);

        startActivity(Intent.createChooser(intent, "To complete action choose:"));
    }

    private void goToGoogleMap() {
        String place = "6C Trần Kế Xương, Hồ Chí Minh, Việt Nam";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + place + ""));

        startActivity(intent);
    }
}
