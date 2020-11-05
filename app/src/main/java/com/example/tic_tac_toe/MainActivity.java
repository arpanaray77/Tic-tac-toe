package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 0;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean gameisOn = true;
    int winPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    String winner = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tapped = Integer.parseInt(counter.getTag().toString());
        Log.i("info", counter.getTag().toString());
        if (gameState[tapped] == 2 && gameisOn) {
            gameState[tapped] = player;
            counter.setTranslationY(-1500);
            if (player == 0) {
                counter.setImageResource(R.drawable.cross);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                player = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] pos : winPositions) {
                if (gameState[pos[0]] == gameState[pos[1]] && gameState[pos[1]] == gameState[pos[2]] && gameState[pos[0]] != 2) {
                    gameisOn=false;
                    if (player == 0) {
                        winner = "second";
                       // Toast.makeText(this, "Winner : second Player", Toast.LENGTH_SHORT).show();
                    } else {
                        winner = "first";
                       // Toast.makeText(this, "Winner : first Player ", Toast.LENGTH_SHORT).show();
                    }
                    Button playAgainBtn = (Button) findViewById(R.id.button);
                    TextView txt = (TextView) findViewById(R.id.textView);
                    txt.setText(winner + " player has won!!");
                    playAgainBtn.setVisibility(View.VISIBLE);
                    txt.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view) {
        Button playAgainBtn = (Button) findViewById(R.id.button);
        TextView txt = (TextView) findViewById(R.id.textView);
        playAgainBtn.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2 ;
        }
        player = 0;
        gameisOn = true;
    }
}
