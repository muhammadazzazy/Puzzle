package com.muhammadazzazy.puzzle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;
import android.widget.CompoundButton;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static Puzzle puzzle = new Puzzle(false);
    private static int moves = 0;
    private static boolean flag = false;

    private final Button[][] buttons = new Button[4][4];

    public static final int[][] buttonIds = {{R.id.a1, R.id.a2, R.id.a3, R.id.a4}, {R.id.b1, R.id.b2, R.id.b3, R.id.b4},
            {R.id.c1, R.id.c2, R.id.c3, R.id.c4}, {R.id.d1, R.id.d2, R.id.d3, R.id.d4}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < 4; i++) {
            int[][] board = puzzle.queryBoardData();
            for(int j = 0; j < 4; j++) {
                Button button = findViewById(buttonIds[i][j]);
                if(board[i][j] == 0){
                    button.setText("");
                } else {
                    button.setText(String.format(Locale.getDefault(), "%d", board[i][j]));
                }
            }
        }
        if(puzzle.checkSorted()) {
            showMessageBox();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = findViewById(buttonIds[i][j]);
                final int ROW = i;
                final int COL = j;
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String resourceName = getResources().getResourceEntryName(buttonIds[ROW][COL]);
                        System.out.println(resourceName);
                        if(puzzle.validateMove(resourceName)) {
                            System.out.println("Valid move!");
                            System.out.println(puzzle.arr[0] + " " + puzzle.arr[1]);
                            String temp = buttons[ROW][COL].getText().toString();
                            buttons[ROW][COL].setText("");
                            buttons[puzzle.arr[0]][puzzle.arr[1]].setText(temp);
                            puzzle.updateBoardData(resourceName);
                            moves++;
                            System.out.println(temp);
                            System.out.println("Moves:" + moves);
                            if(puzzle.checkSorted()) {
                                showMessageBox();
                            }
                        }
                    }
                });
            }
        }

        Button start = findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moves = 0;
                System.out.println("Moves: " + moves);
                puzzle = new Puzzle(flag);
                final int[][] board = puzzle.queryBoardData();
                for(int i = 0; i < 4; i++) {
                    for(int j = 0; j < 4; j++) {
                        Button button = findViewById(buttonIds[i][j]);
                        if(board[i][j] == 0) {
                            button.setText("");
                        } else {
                            button.setText(String.format(Locale.getDefault(), "%d", board[i][j]));
                        }
                    }
                }
                if(puzzle.checkSorted()) {
                    showMessageBox();
                }
            }
        });

        CheckBox test = findViewById(R.id.test);
        test.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flag = isChecked;
            }
        });
    }

    public void showMessageBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Board is ordered!");
        builder.setMessage("Congratulations! You finished it in " + moves + " moves");
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}