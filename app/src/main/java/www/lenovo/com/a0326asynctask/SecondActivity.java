package www.lenovo.com.a0326asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private ProgressBar progressBar;
    MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();
    }

    private void init() {
        textView = findViewById(R.id.tv);
        button = findViewById(R.id.btn);
        progressBar = findViewById(R.id.probar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTask = new MyTask();
                myTask.execute();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myTask!=null&&myTask.getStatus()==AsyncTask.Status.RUNNING){
            myTask.cancel(true);
        }

    }

    private class MyTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int a = values[0];
            progressBar.setProgress(a);
            textView.setText(a + "%");
            if (a == 100) {
                Toast.makeText(getApplicationContext(), "下载成功", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i <= 100; i++) {
                if (myTask.isCancelled()){
                    break;
                }
                publishProgress(i);
                SystemClock.sleep(1000);
                Log.e("---------" + i, "---------");

            }


            return null;
        }
 
    }
}
