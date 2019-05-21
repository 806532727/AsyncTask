package www.lenovo.com.a0326asynctask;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FourthActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_1, btn_2, btn_3, btn_4, btn_5;
    private ProgressBar pb_1, pb_2, pb_3, pb_4, pb_5;
    MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getSupportActionBar().hide();
        init();
    }

    private void init() {
        btn_1 = findViewById(R.id.btn1);
        btn_2 = findViewById(R.id.btn2);
        btn_3 = findViewById(R.id.btn3);
        btn_4 = findViewById(R.id.btn4);
        btn_5 = findViewById(R.id.btn5);

        pb_1 = findViewById(R.id.pb_1);
        pb_2 = findViewById(R.id.pb_2);
        pb_3 = findViewById(R.id.pb_3);
        pb_4 = findViewById(R.id.pb_4);
        pb_5 = findViewById(R.id.pb_5);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        myTask = new MyTask();

        switch (v.getId()) {
            case R.id.btn1:
                myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 1);
                btn_1.setText("正在下载");
                btn_1.setEnabled(false);

                break;
            case R.id.btn2:
                myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 2);
                btn_2.setText("正在下载");
                btn_2.setEnabled(false);

                break;
            case R.id.btn3:
                myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 3);
                btn_3.setText("正在下载");
                btn_3.setEnabled(false);

                break;
            case R.id.btn4:
                myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 4);
                btn_4.setText("正在下载");
                btn_4.setEnabled(false);

                break;
            case R.id.btn5:
                myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 5);
                btn_5.setText("正在下载");
                btn_5.setEnabled(false);

                break;
        }
    }


    private class MyTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int a = values[1];
            switch (a) {
                case 1:
                    pb_1.setProgress(values[0]);
                    break;
                case 2:
                    pb_2.setProgress(values[0]);
                    break;
                case 3:
                    pb_3.setProgress(values[0]);
                    break;
                case 4:
                    pb_4.setProgress(values[0]);
                    break;
                case 5:
                    pb_5.setProgress(values[0]);
                    break;
            }

            /*if (values[0] == 100) {
                Toast.makeText(getApplicationContext(), "下载成功", Toast.LENGTH_SHORT).show();
            }*/

        }


        @Override
        protected Integer doInBackground(Integer... params) {
            int count = 0;
            int i = params[0];
            for (; count <= 100; count++) {
                if (myTask.isCancelled()) {
                    break;
                }
                publishProgress(count, i);
                SystemClock.sleep(10);
                Log.e("---------" + count, "---------");

            }


            return i;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer) {
                case 1:
                    btn_1.setText("下载完成");
                    btn_1.setEnabled(true);
                    break;
                case 2:
                    btn_2.setText("下载完成");
                    btn_2.setEnabled(true);
                    break;
                case 3:
                    btn_3.setText("下载完成");
                    btn_3.setEnabled(true);
                    break;
                case 4:
                    btn_4.setText("下载完成");
                    btn_4.setEnabled(true);
                    break;
                case 5:
                    btn_5.setText("下载完成");
                    btn_5.setEnabled(true);
                    break;

            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myTask != null && myTask.getStatus() == AsyncTask.Status.RUNNING) {
            myTask.cancel(true);
        }

    }
}
