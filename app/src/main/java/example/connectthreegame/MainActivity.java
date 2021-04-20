package example.connectthreegame;

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
    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[][] winningPosition = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},{0,4,8},{2,4,6} };
    int coin = 1;
    boolean gameActive = true;
    public void dropCoin(View view) {
        ImageView counter = (ImageView) view;
        int tapCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tapCounter] == 0 && gameActive) {
            gameState[tapCounter] = coin;
            counter.setTranslationY(-1500);
            if (coin == 1) {
                coin = 2;
                counter.setImageResource(R.drawable.green);
            } else {
                coin = 1;
                counter.setImageResource(R.drawable.blue);
            }
            counter.animate().translationYBy(1500).rotationY(3600).setDuration(1000);
            for (int[] winningpostion : winningPosition) {
                if (gameState[winningpostion[0]] == gameState[winningpostion[1]] && gameState[winningpostion[1]] == gameState[winningpostion[2]] && gameState[winningpostion[0]] != 0) {
                    gameActive = false;
                    String winner ;
                    if (gameState[winningpostion[0]] == 1) {
//                        Toast.makeText(this, "Green Has Won!!", Toast.LENGTH_LONG).show();
                        winner = "Green";
                    } else {
//                        Toast.makeText(this, "Blue Has Won!!", Toast.LENGTH_LONG).show();
                        winner = "Blue";
                    }
                        Button playagain = findViewById(R.id.play_again);
                        TextView WinnertextView = (TextView)findViewById(R.id.myTextView);
                        WinnertextView.setText(winner + " Has Won!!!");
                        playagain.setVisibility(view.VISIBLE);
                        WinnertextView.setVisibility(view.VISIBLE);

                }
            }


        }
    }
    public void playAgain(View view){
        Button playagain = (Button)findViewById(R.id.play_again);
        TextView WinnertextView = (TextView)findViewById(R.id.myTextView);
        playagain.setVisibility(View.INVISIBLE);
        WinnertextView.setVisibility(View.INVISIBLE);
       GridLayout gridLayout = (GridLayout)findViewById(R.id.gridlayout);
        Log.i("Info","Button pressed");
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
      for(int j=0; j< gameState.length; j++){
          gameState[j] = 0;
      }
        coin = 1;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
