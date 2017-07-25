package www.autotextview.com.autotextview;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.Bundle;

import www.autotextview.com.autotextview.View.AutoVerticalScrollTextView;
import www.autotextview.com.autotextview.View.AutoHorizontalScrollTextView;

public class MainActivity extends Activity {

    private int number =0;
    private boolean isRunning=true;

    private String[] strings={"1","2","3","4","5","6","7"};
    private String[] strings2={"4","5","6","7","8","9","0"};
    private String[] strings3={"9","0","1","2","3","4","5"};
    private String string="我的剑，就是你的剑!   俺也是从石头里蹦出来得!    我用双手成就你的梦想!    人在塔在!    犯我德邦者，虽远必诛!    我会让你看看什么叫残忍!    我的大刀早已饥渴难耐了!";

    private AutoVerticalScrollTextView verticalScrollTV,verticalScrollTV2,verticalScrollTV3;
    private AutoHorizontalScrollTextView horizontalScrollTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        horizontalScrollTV = (AutoHorizontalScrollTextView) findViewById(R.id.textview);
        horizontalScrollTV.setText(string);

        verticalScrollTV = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll);
        verticalScrollTV.setText(strings[0]);
        verticalScrollTV2 = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll2);
        verticalScrollTV2.setText(strings[0]);
        verticalScrollTV3 = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll3);
        verticalScrollTV3.setText(strings[0]);

        new Thread(){
            @Override
            public void run() {
                while (isRunning){
                    SystemClock.sleep(400);
                    handler.sendEmptyMessage(199);
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                verticalScrollTV.next();
                verticalScrollTV2.next();
                verticalScrollTV3.next();
                number++;
                verticalScrollTV.setText(strings[number%strings.length]);
                verticalScrollTV2.setText(strings2[number%strings.length]);
                verticalScrollTV3.setText(strings3[number%strings.length]);
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }
}
