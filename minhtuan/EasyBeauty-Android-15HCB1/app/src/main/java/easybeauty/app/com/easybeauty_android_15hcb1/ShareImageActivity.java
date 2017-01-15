package easybeauty.app.com.easybeauty_android_15hcb1;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ShareImageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_image);
        mButtonShare = (Button) findViewById(R.id.buttonShare);
        mButtonShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonShare:
                Intent shareImage = new Intent();
                shareImage.setAction(Intent.ACTION_SEND);
                shareImage.setType("image/*");
                //TODO change if share image on sdcard
//                String imagePath = Environment.getExternalStorageDirectory()+ "/example.png";
//                File imageFileToShare = new File(imagePath);
//                Uri imageUri = Uri.fromFile(imageFileToShare);

                //example share image drawable
                Uri imageUri = null;
                try {
                    imageUri = Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(),
                            BitmapFactory.decodeResource(getResources(), R.drawable.tung_son), null, null));
                } catch (NullPointerException e) {
                }
                shareImage.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(shareImage, null));
                break;
            default:
                break;
        }
    }
}
