package com.example.textmaze;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    int turn;

    ArrayList<Player> player = new ArrayList<>(); // Player 객체를 담을 ArrayList
    Room[][] map = new Room[5][4];  // Room객체를 담을 배열(5행 4열) <= 아직 Room객체를 생성하지는 않았음
    Player p = new Player(2, 2);    // Player 객체 생성(매개변수 x좌표 : 2, y좌표: 2)

    TextView mainTv;
    EditText mainEt;
    TextView posTv;
    Button submitBt;
    Button upBt;
    Button downBt;
    Button leftBt;
    Button rightBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTv = findViewById(R.id.main_tv);
        mainEt = findViewById(R.id.main_et);
        posTv = findViewById(R.id.pos_tv);
        submitBt = findViewById(R.id.submit_bt);
        upBt = findViewById(R.id.up_bt);
        downBt = findViewById(R.id.down_bt);
        leftBt = findViewById(R.id.left_bt);
        rightBt = findViewById(R.id.right_bt);

        init();

        p = player.get(turn);
        posTv.setText(p.name + " 현재 위치 [" + p.posX + "][" + p.posY + "]");

        upBt.setOnClickListener(this);
        downBt.setOnClickListener(this);
        leftBt.setOnClickListener(this);
        rightBt.setOnClickListener(this);


        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mainEt.getText().toString();
                if (str.equals("apple")) {
                    p.redKey = true;
//                    mainTv.setText("정답! 레드키 획득!~");
                    Toast toast = Toast.makeText(getApplicationContext(), "레드키 획득", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, -100);
                    toast.show();
                } else {
//                    mainTv.setText("땡!");
                    Toast toast = Toast.makeText(getApplicationContext(), "땡", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, -100);
                    toast.show();
                }
                nextTurn();
            }
        });
    }

    @Override
    public void onClick(View v) {
        // 상,하,좌,우 버튼 클릭 시
        if (v.getId() == R.id.up_bt) {
            p.up(map, mainTv);
        } else if (v.getId() == R.id.down_bt) {
            p.down(map, mainTv);
        } else if (v.getId() == R.id.left_bt) {
            p.left(map, mainTv);
        } else if (v.getId() == R.id.right_bt) {
            p.right(map, mainTv);
        }

        // 버튼 처리 후
        if (map[p.posX][p.posY].evetType == 1) {
            mainTv.setText("apple 뜻?");
        } else if (map[p.posX][p.posY].evetType == 2) {
            p.blueKey = true;
            mainTv.setText("정답! 블루키 획득!~");
            nextTurn();
        } else if (map[p.posX][p.posY].evetType == 3) {
            mainTv.setText("끝");
        } else {
            nextTurn();
        }
    }

    private void nextTurn() {
        turn++;
        if (player.size() == turn) {
            turn = 0;
        }
        p = player.get(turn);
        posTv.setText(p.name + " 현재 위치 [" + p.posX + "][" + p.posY + "]");
    }

    private void init() {
        turn = 0;

        // 맵 세팅(for루프로 2차원 배열(5행4열)을 순회하면서 Room객체를 생성)
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new Room();
            }
        }

        map[0][0].right = 1;
        map[1][0].left = 1;
        map[1][0].right = 1;
        map[2][0].left = 1;
        map[2][0].right = 1;
        map[2][0].down = 1;
        map[3][0].left = 1;
        map[3][0].right = 1;
        map[4][0].left = 1;

        map[2][1].up = 1;
        map[2][1].down = 1;

        map[0][2].down = 2;
        map[2][2].up = 1;
        map[2][2].down = 1;
        map[3][2].down = 1;
        map[2][2].right = 1;
        map[3][2].left = 1;
        map[4][2].down = 3;

        map[0][3].up = 2;
        map[0][3].right = 1;
        map[1][3].left = 1;
        map[1][3].right = 1;
        map[2][3].up = 1;
        map[2][3].right = 1;
        map[2][3].left = 1;
        map[3][3].right = 1;
        map[3][3].left = 1;
        map[4][3].up = 3;
        map[4][3].left = 1;

        //이벤트 세팅
        map[4][0].evetType = 1;
        map[0][2].evetType = 2;
        map[4][2].evetType = 3;

        Player p1 = new Player(2, 2);
        p1.name = "Player1";
        Player p2 = new Player(2, 2);
        p2.name = "Player2";
        player.add(p1);
        player.add(p2);
    }
}