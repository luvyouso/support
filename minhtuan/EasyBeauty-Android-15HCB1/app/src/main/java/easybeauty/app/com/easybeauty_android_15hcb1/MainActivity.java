package easybeauty.app.com.easybeauty_android_15hcb1;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.co.cyberagent.android.gpuimage.GPUImage;

public class MainActivity extends AppCompatActivity {

    GPUImage mGPUImage;

    @InjectView(R.id.glSurfaceView)
    GLSurfaceView glSurfaceView;
    @InjectView(R.id.btnCapture)
    Button btnCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inject view
        ButterKnife.inject(this);
        // init gpu image
        mGPUImage = new GPUImage(this);
        mGPUImage.setGLSurfaceView(glSurfaceView);
        // capture image
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
