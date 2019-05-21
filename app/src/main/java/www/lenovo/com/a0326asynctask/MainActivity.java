package www.lenovo.com.a0326asynctask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String path = "http://img2.3lian.com/2014cf/f6/146/d/92.jpg";
    private Button button, button2;
    private ImageView imageView;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("正在下载");
        imageView = findViewById(R.id.iv);
        button = findViewById(R.id.btn);
        button2 = findViewById(R.id.btn2);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                new MyTask().execute(path);
                break;
            case R.id.btn2:
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));


                break;

        }
    }


    private class MyTask extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String url = strings[0];
            Bitmap bitmap = null;
            try {
                URLConnection connection = new URL(url).openConnection();
                SystemClock.sleep(3000);
                InputStream is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            dialog.dismiss();
            imageView.setBackground(null);
            Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(bitmap);
        }
    }

}
