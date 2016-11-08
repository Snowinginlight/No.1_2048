package minework.No1_2048;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static int Direction = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;
    private static boolean changedFlag = false;
    private static int NowScore = 0;
    private static int scoreTop = 0;
    private static int MyChangeFlag = 0;

    private HashMap<String, Integer> NumMap = new HashMap<String, Integer>();
    private HashMap<TextView, RelativeLayout> TextToCube = new HashMap<TextView, RelativeLayout>();
    private GestureDetector mGestureDetector;
    private final int[] createOne = {2, 2, 2, 2, 4};
    private TextView[][] TextLeftSet;
    private TextView[][] TextUpSet;
    private TextView[][] TextRightSet;
    private TextView[][] TextDownSet;
    private List<TextView> RemainList;
    private TextView Score;
    private TextView HighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetector(this, new MyGestureDetector());
        init();
        getScore();
        createTwo();
    }

    private void init() {

        RelativeLayout cube_1_1 = (RelativeLayout) findViewById(R.id.cube_1_1);
        RelativeLayout cube_1_2 = (RelativeLayout) findViewById(R.id.cube_1_2);
        RelativeLayout cube_1_3 = (RelativeLayout) findViewById(R.id.cube_1_3);
        RelativeLayout cube_1_4 = (RelativeLayout) findViewById(R.id.cube_1_4);
        RelativeLayout cube_2_1 = (RelativeLayout) findViewById(R.id.cube_2_1);
        RelativeLayout cube_2_2 = (RelativeLayout) findViewById(R.id.cube_2_2);
        RelativeLayout cube_2_3 = (RelativeLayout) findViewById(R.id.cube_2_3);
        RelativeLayout cube_2_4 = (RelativeLayout) findViewById(R.id.cube_2_4);
        RelativeLayout cube_3_1 = (RelativeLayout) findViewById(R.id.cube_3_1);
        RelativeLayout cube_3_2 = (RelativeLayout) findViewById(R.id.cube_3_2);
        RelativeLayout cube_3_3 = (RelativeLayout) findViewById(R.id.cube_3_3);
        RelativeLayout cube_3_4 = (RelativeLayout) findViewById(R.id.cube_3_4);
        RelativeLayout cube_4_1 = (RelativeLayout) findViewById(R.id.cube_4_1);
        RelativeLayout cube_4_2 = (RelativeLayout) findViewById(R.id.cube_4_2);
        RelativeLayout cube_4_3 = (RelativeLayout) findViewById(R.id.cube_4_3);
        RelativeLayout cube_4_4 = (RelativeLayout) findViewById(R.id.cube_4_4);

        Score = (TextView) findViewById(R.id.now_score);
        HighScore = (TextView) findViewById(R.id.high_score);

        TextView num_1_1 = (TextView) findViewById(R.id.num_1_1);
        TextView num_1_2 = (TextView) findViewById(R.id.num_1_2);
        TextView num_1_3 = (TextView) findViewById(R.id.num_1_3);
        TextView num_1_4 = (TextView) findViewById(R.id.num_1_4);
        TextView num_2_1 = (TextView) findViewById(R.id.num_2_1);
        TextView num_2_2 = (TextView) findViewById(R.id.num_2_2);
        TextView num_2_3 = (TextView) findViewById(R.id.num_2_3);
        TextView num_2_4 = (TextView) findViewById(R.id.num_2_4);
        TextView num_3_1 = (TextView) findViewById(R.id.num_3_1);
        TextView num_3_2 = (TextView) findViewById(R.id.num_3_2);
        TextView num_3_3 = (TextView) findViewById(R.id.num_3_3);
        TextView num_3_4 = (TextView) findViewById(R.id.num_3_4);
        TextView num_4_1 = (TextView) findViewById(R.id.num_4_1);
        TextView num_4_2 = (TextView) findViewById(R.id.num_4_2);
        TextView num_4_3 = (TextView) findViewById(R.id.num_4_3);
        TextView num_4_4 = (TextView) findViewById(R.id.num_4_4);

        TextLeftSet = new TextView[][]{{num_1_1, num_1_2, num_1_3, num_1_4}, {num_2_1, num_2_2, num_2_3, num_2_4},
                {num_3_1, num_3_2, num_3_3, num_3_4}, {num_4_1, num_4_2, num_4_3, num_4_4}};
        TextUpSet = new TextView[][]{{num_1_1, num_2_1, num_3_1, num_4_1}, {num_1_2, num_2_2, num_3_2, num_4_2},
                {num_1_3, num_2_3, num_3_3, num_4_3}, {num_1_4, num_2_4, num_3_4, num_4_4}};
        TextRightSet = new TextView[][]{{num_1_4, num_1_3, num_1_2, num_1_1}, {num_2_4, num_2_3, num_2_2, num_2_1},
                {num_3_4, num_3_3, num_3_2, num_3_1}, {num_4_4, num_4_3, num_4_2, num_4_1}};
        TextDownSet = new TextView[][]{{num_4_1, num_3_1, num_2_1, num_1_1}, {num_4_2, num_3_2, num_2_2, num_1_2},
                {num_4_3, num_3_3, num_2_3, num_1_3}, {num_4_4, num_3_4, num_2_4, num_1_4}};

        TextToCube.put(num_1_1, cube_1_1);
        TextToCube.put(num_1_2, cube_1_2);
        TextToCube.put(num_1_3, cube_1_3);
        TextToCube.put(num_1_4, cube_1_4);
        TextToCube.put(num_2_1, cube_2_1);
        TextToCube.put(num_2_2, cube_2_2);
        TextToCube.put(num_2_3, cube_2_3);
        TextToCube.put(num_2_4, cube_2_4);
        TextToCube.put(num_3_1, cube_3_1);
        TextToCube.put(num_3_2, cube_3_2);
        TextToCube.put(num_3_3, cube_3_3);
        TextToCube.put(num_3_4, cube_3_4);
        TextToCube.put(num_4_1, cube_4_1);
        TextToCube.put(num_4_2, cube_4_2);
        TextToCube.put(num_4_3, cube_4_3);
        TextToCube.put(num_4_4, cube_4_4);

        NumMap.put("2", R.drawable.corners_bg_00002);
        NumMap.put("4", R.drawable.corners_bg_00004);
        NumMap.put("8", R.drawable.corners_bg_00008);
        NumMap.put("16", R.drawable.corners_bg_00016);
        NumMap.put("32", R.drawable.corners_bg_00032);
        NumMap.put("64", R.drawable.corners_bg_00064);
        NumMap.put("128", R.drawable.corners_bg_00128);
        NumMap.put("256", R.drawable.corners_bg_00256);
        NumMap.put("512", R.drawable.corners_bg_00512);
        NumMap.put("1024", R.drawable.corners_bg_01024);
        NumMap.put("2048", R.drawable.corners_bg_02048);
        NumMap.put("4096", R.drawable.corners_bg_04096);
        NumMap.put("8192", R.drawable.corners_bg_08192);
        NumMap.put("16384", R.drawable.corners_bg_16384);
    }

    private TextView[] changeCube(TextView[] Text) {
        List<String> oldText = new ArrayList<String>();
        List<String> newText = new ArrayList<String>();
        List<String> jundgeText = new ArrayList<String>();
        ;
        for (TextView oneText : Text) {
            oldText.add(oneText.getText().toString());
            jundgeText.add(oneText.getText().toString());
        }
        while (oldText.contains("")) {
            oldText.remove("");
        }
        MyChangeFlag = oldText.size();
        for (int i = 0; i < MyChangeFlag; i++) {
            if (oldText.size() > 1) {
                if (oldText.get(0).equals(oldText.get(1))) {
                    newText.add(String.valueOf(Integer.parseInt(oldText.get(0)) + Integer.parseInt(oldText.get(1))));
                    NowScore += (Integer.parseInt(oldText.get(0)) + Integer.parseInt(oldText.get(1)));
                    Score.setText(String.valueOf(NowScore));
                    if (NowScore > scoreTop) {
                        scoreTop = NowScore;
                        HighScore.setText(String.valueOf(scoreTop));
                        saveScore(scoreTop);
                    }
                    oldText.remove(1);
                    oldText.remove(0);
                    MyChangeFlag = oldText.size();
                } else {
                    newText.add(oldText.get(0));
                    oldText.remove(0);
                }
            }
            if (oldText.size() == 1) {
                newText.add(oldText.get(0));
                oldText.remove(0);
            }
        }
        while (newText.size() < 4) {
            newText.add("");
        }
        if (!jundgeText.toString().equals(newText.toString())) {
            changedFlag = true;
        }
        for (int i = 0; i < 4; i++) {
            Text[i].setText(newText.get(i));
            if (Text[i].getText() != "") {
                TextToCube.get(Text[i]).setBackgroundResource(NumMap.get(Text[i].getText()));
            } else {
                TextToCube.get(Text[i]).setBackgroundResource(R.drawable.corners_bg);
            }
        }
        return Text;
    }

    private void rules(int Dir) {
        switch (Dir) {
            case LEFT:
                changedFlag = false;
                for (TextView[] Text : TextLeftSet) {
                    Text = changeCube(Text);
                }
                if (changedFlag) {
                    createOne();
                }
                gameOver();
                break;
            case RIGHT:
                changedFlag = false;
                for (TextView[] Text : TextRightSet) {
                    Text = changeCube(Text);
                }
                if (changedFlag) {
                    createOne();
                }
                gameOver();
                break;
            case UP:
                changedFlag = false;
                for (TextView[] Text : TextUpSet) {
                    Text = changeCube(Text);
                }
                if (changedFlag) {
                    createOne();
                }
                gameOver();
                break;
            case DOWN:
                changedFlag = false;
                for (TextView[] Text : TextDownSet) {
                    Text = changeCube(Text);
                }
                if (changedFlag) {
                    createOne();
                }
                gameOver();
                break;
            default:
                return;
        }
    }

    private void createOne() {
        RemainList = null;
        RemainList = new ArrayList<TextView>();
        for (Iterator iterator = TextToCube.keySet().iterator(); iterator.hasNext(); ) {
            TextView textView = (TextView) iterator.next();
            if (textView.getText() == "") {
                RemainList.add(textView);
            }
        }

        TextView newOne = RemainList.get((int) (Math.random() * RemainList.size()));
        newOne.setText(String.valueOf(createOne[new Random().nextInt(createOne.length)]));
        TextToCube.get(newOne).setBackgroundResource(NumMap.get(newOne.getText()));

    }

    private void createTwo() {
        TextView newOne = TextLeftSet[(int) (Math.random() * 4)][(int) (Math.random() * 4)];
        newOne.setText(String.valueOf(createOne[new Random().nextInt(createOne.length)]));
        TextToCube.get(newOne).setBackgroundResource(NumMap.get(newOne.getText()));

        TextView newTwo = TextLeftSet[(int) (Math.random() * 4)][(int) (Math.random() * 4)];
        newTwo.setText(String.valueOf(createOne[new Random().nextInt(createOne.length)]));
        TextToCube.get(newTwo).setBackgroundResource(NumMap.get(newTwo.getText()));
    }

    private void saveScore(int higherScore) {
        SharedPreferences.Editor editor = getSharedPreferences("score", MODE_PRIVATE).edit();
        editor.putInt("highscore", higherScore);
        editor.commit();
    }

    private void getScore() {
        SharedPreferences pref = getSharedPreferences("score", MODE_PRIVATE);
        scoreTop = pref.getInt("highscore", 0);
        HighScore.setText(String.valueOf(scoreTop));
    }

    private void gameOver() {
        boolean overFlags = true;
        RemainList = null;
        RemainList = new ArrayList<TextView>();
        for (Iterator iterator = TextToCube.keySet().iterator(); iterator.hasNext(); ) {
            TextView textView = (TextView) iterator.next();
            if (textView.getText() == "") {
                RemainList.add(textView);
            }
        }
        if (RemainList.size() == 0) {
            for (TextView[] Text : TextLeftSet) {
                for (int i = 0; i < 3; i++) {
                    if (Text[i].getText().toString().equals(Text[i + 1].getText().toString())) {
                        overFlags = false;
                    }
                }
            }
            for (TextView[] Text : TextUpSet) {
                for (int i = 0; i < 3; i++) {
                    if (Text[i].getText().toString().equals(Text[i + 1].getText().toString())) {
                        overFlags = false;
                    }
                }
            }

            if (overFlags) {
                AlertDialog.Builder gameOver = new AlertDialog.Builder(this);
                gameOver.setTitle("GameOver").setCancelable(false).setMessage("score:" + NowScore + "\nplay again ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                for (TextView Num : TextToCube.keySet()) {
                                    Num.setText("");
                                    TextToCube.get(Num).setBackgroundResource(R.drawable.corners_bg);
                                }
                                createTwo();
                                NowScore = 0;
                                Score.setText(String.valueOf(NowScore));
                            }
                        })
                        .setNegativeButton("Exit", null).show();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private class MyGestureDetector implements GestureDetector.OnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float minMove = 20;         //最小滑动距离
            float minVelocity = 0;      //最小滑动速度
            float beginX = e1.getX();
            float endX = e2.getX();
            float beginY = e1.getY();
            float endY = e2.getY();

            if (beginX - endX > minMove && Math.abs(velocityX) > minVelocity && Math.abs(velocityX) > Math.abs(velocityY)) {   //左滑
                rules(LEFT);
            } else if (endX - beginX > minMove && Math.abs(velocityX) > minVelocity && Math.abs(velocityX) > Math.abs(velocityY)) {   //右滑
                rules(RIGHT);
            } else if (beginY - endY > minMove && Math.abs(velocityY) > minVelocity && Math.abs(velocityY) > Math.abs(velocityX)) {   //上滑
                rules(UP);
            } else if (endY - beginY > minMove && Math.abs(velocityY) > minVelocity && Math.abs(velocityY) > Math.abs(velocityX)) {   //下滑
                rules(DOWN);
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
        }
    }

    private void animation() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
