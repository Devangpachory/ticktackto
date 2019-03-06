package com.devangpachory.ticktackto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 is blue and red is 1

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningposition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activeplayer = 0;
    boolean gameactive = true;
    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedcounter] == 2 && gameactive) {

            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.o);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.circletick);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winning : winningposition) {
                if (gamestate[winning[0]] == gamestate[winning[1]] && gamestate[winning[1]] == gamestate[winning[2]] && gamestate[winning[0]] != 2) {
                    gameactive = false;
                    String winner = "";
                    if (activeplayer == 0) {
                        winner = "red";
                    } else {
                        winner = "blue";
                    }
                    Toast.makeText(this, winner + "has won", Toast.LENGTH_SHORT).show();
                    Button palyagain = (Button) findViewById(R.id.palayagainbutton);
                    TextView winnertext = (TextView) findViewById(R.id.textView);
                    winnertext.setText(winner + " has won");
                    palyagain.setVisibility(View.VISIBLE);
                    winnertext.setVisibility(View.VISIBLE);

                }

            }
        }

    }

    public void playagainm(View view) {
        Button palyagain = (Button) findViewById(R.id.palayagainbutton);
        TextView winnertext = (TextView) findViewById(R.id.textView);
        palyagain.setVisibility(View.INVISIBLE);
        winnertext.setVisibility(View.INVISIBLE);
        GridLayout gridlayout = (GridLayout) findViewById(R.id.gridlayout);
        for (int i=0; i< gridlayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridlayout.getChildAt(i);
            counter.setImageDrawable(null);

        }
        for (int i = 0; i < gamestate.length; i++) {


            gamestate[i] = 2;
        }
        activeplayer = 0;

        gameactive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
