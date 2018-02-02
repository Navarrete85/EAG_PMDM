package eag.multimediacamara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class CameraActivity extends AppCompatActivity {


    public static final int TAKE_PICTURE = 1;
    private ImageView imgThmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imgThmb = (ImageView) findViewById(R.id.imgThumb);

        getPicture();

    }

    private void getPicture(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, TAKE_PICTURE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TAKE_PICTURE){

            if(data != null){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgThmb.setImageBitmap(bitmap);

            }
        }
    }
}
