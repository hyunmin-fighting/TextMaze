package com.example.textmaze;

import android.widget.TextView;

public class Player {
    int posX;
    int posY;

    String name;
    boolean redKey;
    boolean blueKey;

    // 생성자
    public Player(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public boolean chk(int value, TextView mainTv) {
        boolean result = false;
        if (value == 1) {
            result = true;
        } else if (value == 2) {
            if (redKey == true) {
                result = true;
            } else {
                mainTv.setText("레드 키가 필요하다");
            }
        } else if (value == 3) {
            if (blueKey == true) {
                result = true;
            } else {
                mainTv.setText("블루 키가 필요하다");
            }
        } else {
            mainTv.setText("못 간다 이놈아");
        }
        return result;
    }

    public void right(Room[][] map, TextView mainTv) {
        if (chk(map[posX][posY].right, mainTv)) {
            posX++;
        }
    }

    public void left(Room[][] map, TextView mainTv) {
        if (chk(map[posX][posY].left, mainTv)) {
            posX--;
        }
    }

    public void down(Room[][] map, TextView mainTv) {
        if (chk(map[posX][posY].down, mainTv)) {
            posY++;
        }
    }

    public void up(Room[][] map, TextView mainTv) {
        if (chk(map[posX][posY].up, mainTv)) {
            posY--;
        }
    }

}
