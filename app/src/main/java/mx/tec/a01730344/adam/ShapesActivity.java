package mx.tec.a01730344.adam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShapesActivity extends AppCompatActivity {

    ShapesModelNVL1 model;
    ImageButton ibPauseShapes1;
    ImageView ivNVL1Figure1;
    ImageView ivNVL1Figure2;
    ImageView ivNVL1Figure3;
    ImageView ivNVL1Drag1;
    ImageView ivNVL1Drag2;
    ImageView ivNVL1Drag3;
    ImageView ivLife1SH;
    ImageView ivLife2SH;
    ImageView ivLife3SH;
    TextView tvShapesNVL1;
    TextView tvScoreShapesNVL1;
    Button btnContinueNVL1;
    Button btnCheckNVL1;
    Button btnRestartNVL1;
    String strText;
    String strScore;
    int score = 0;
    int lives = 3;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_layout);

        user.loadProfiles();
        model = new ShapesModelNVL1();
        ibPauseShapes1 = findViewById(R.id.ibPauseShapes1);
        ivNVL1Figure1 = findViewById(R.id.ivNVL1Figure1);
        ivNVL1Figure2 = findViewById(R.id.ivNVL1Figure2);
        ivNVL1Figure3 = findViewById(R.id.ivNVL1Figure3);
        ivNVL1Drag1 = findViewById(R.id.ivNVL1Drag1);
        ivNVL1Drag2 = findViewById(R.id.ivNVL1Drag2);
        ivNVL1Drag3 = findViewById(R.id.ivNVL1Drag3);
        ivLife1SH = findViewById(R.id.ivLife1SH);
        ivLife2SH = findViewById(R.id.ivLife2SH);
        ivLife3SH = findViewById(R.id.ivLife3SH);
        tvShapesNVL1 = findViewById(R.id.tvShapesNVL1);
        tvScoreShapesNVL1 = findViewById(R.id.tvScoreShapesNVL1);
        btnContinueNVL1 = findViewById(R.id.btnContinueNVL1);
        btnCheckNVL1 = findViewById(R.id.btnCheckNVL1);
        btnRestartNVL1 = findViewById(R.id.btnRestartNVL1);

        ivNVL1Figure1.setOnDragListener(new myDragListener());
        ivNVL1Figure2.setOnDragListener(new myDragListener());
        ivNVL1Figure3.setOnDragListener(new myDragListener());
        ivNVL1Drag1.setOnTouchListener(new myClickListener());
        ivNVL1Drag2.setOnTouchListener(new myClickListener());
        ivNVL1Drag3.setOnTouchListener(new myClickListener());


        ibPauseShapes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flPauseShapes1, new PauseFragment()).commit();
            }
        });

        btnContinueNVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lives == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("game", 2);
                    bundle.putInt("score", score);
                    if (user.getCurrentUserScoreF() < score) {
                        bundle.putBoolean("high", true);
                    } else {
                        bundle.putBoolean("high", false);
                    }
                    GameOverFragment fragment = new GameOverFragment();
                    fragment.setArguments(bundle);
                    user.updateScore(score,user.getCurrentUserNumber(),2);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.flPauseShapes1, fragment).commit();
                }
                else if (model.state) {
                    hideSequence();
                }
                else {
                    model.startNewRound();
                    setLayoutAttributes();
                }
            }
        });

        btnCheckNVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int correctAnswers = 0;
                int figure1Tag = Integer.parseInt(ivNVL1Figure1.getTag().toString());
                int figure2Tag = Integer.parseInt(ivNVL1Figure2.getTag().toString());
                int figure3Tag = Integer.parseInt(ivNVL1Figure3.getTag().toString());
                if (model.figures.get(0).equals(model.drags.get(figure1Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL1Figure1.animate().alpha(0f).setDuration(500);
                    ivNVL1Figure1.setEnabled(true);
                    if (figure1Tag == 0) {
                        ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag1.setEnabled(true);
                    }
                    if (figure1Tag == 1) {
                        ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag2.setEnabled(true);
                    }
                    if (figure1Tag == 2) {
                        ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag3.setEnabled(true);
                    }
                }
                if (model.figures.get(1).equals(model.drags.get(figure2Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL1Figure2.animate().alpha(0f).setDuration(500);
                    ivNVL1Figure2.setEnabled(true);
                    if (figure2Tag == 0) {
                        ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag1.setEnabled(true);
                    }
                    if (figure2Tag == 1) {
                        ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag2.setEnabled(true);
                    }
                    if (figure2Tag == 2) {
                        ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag3.setEnabled(true);
                    }
                }
                if (model.figures.get(2).equals(model.drags.get(figure3Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL1Figure3.animate().alpha(0f).setDuration(500);
                    ivNVL1Figure3.setEnabled(true);
                    if (figure3Tag == 0) {
                        ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag1.setEnabled(true);
                    }
                    if (figure3Tag == 1) {
                        ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag2.setEnabled(true);
                    }
                    if (figure3Tag == 2) {
                        ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL1Drag3.setEnabled(true);
                    }
                }
                if (correctAnswers == 3) {
                    strText = "¡Muy bien!";
                    btnContinueNVL1.setEnabled(true);
                    btnRestartNVL1.setEnabled(false);
                    score += lives * 10;
                    strScore = "Puntuación: " + String.valueOf(score);
                    tvScoreShapesNVL1.setText(strScore);
                }
                else {
                    lives --;
                    if (lives == 2) {
                        ivLife3SH.setImageResource(R.drawable.ic_adam_dead);
                    }
                    if (lives == 1) {
                        ivLife2SH.setImageResource(R.drawable.ic_adam_dead);
                    }
                    strText = "¡Intenta de nuevo!";
                    if (lives == 0) {
                        ivLife1SH.setImageResource(R.drawable.ic_adam_dead);
                        strText = "¡Juego terminado!";
                        ivNVL1Figure1.setEnabled(false);
                        ivNVL1Figure2.setEnabled(false);
                        ivNVL1Figure3.setEnabled(false);
                        ivNVL1Figure1.setAlpha(0f);
                        ivNVL1Figure2.setAlpha(0f);
                        ivNVL1Figure3.setAlpha(0f);
                        ivNVL1Figure1.setImageResource(model.figures.get(0));
                        ivNVL1Figure2.setImageResource(model.figures.get(1));
                        ivNVL1Figure3.setImageResource(model.figures.get(2));
                        ivNVL1Figure1.animate().alpha(1f).setDuration(2000);
                        ivNVL1Figure2.animate().alpha(1f).setDuration(2000);
                        ivNVL1Figure3.animate().alpha(1f).setDuration(2000);
                        ivNVL1Drag1.setVisibility(View.INVISIBLE);
                        ivNVL1Drag2.setVisibility(View.INVISIBLE);
                        ivNVL1Drag3.setVisibility(View.INVISIBLE);
                        ivNVL1Drag1.setEnabled(false);
                        ivNVL1Drag2.setEnabled(false);
                        ivNVL1Drag3.setEnabled(false);
                        btnRestartNVL1.setEnabled(false);
                        btnContinueNVL1.setEnabled(true);
                    }
                }
                btnCheckNVL1.setEnabled(false);
                tvShapesNVL1.setText(strText);
            }
        });

        btnRestartNVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivNVL1Figure1.animate().alpha(0f).setDuration(500);
                ivNVL1Figure2.animate().alpha(0f).setDuration(500);
                ivNVL1Figure3.animate().alpha(0f).setDuration(500);
                ivNVL1Figure1.setEnabled(true);
                ivNVL1Figure2.setEnabled(true);
                ivNVL1Figure3.setEnabled(true);
                ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
                ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
                ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
                ivNVL1Drag1.setEnabled(true);
                ivNVL1Drag2.setEnabled(true);
                ivNVL1Drag3.setEnabled(true);
                btnCheckNVL1.setEnabled(false);
            }
        });

        model.startNewRound();
        setLayoutAttributes();

    }

    private void setLayoutAttributes() {
        ivNVL1Figure1.setAlpha(0f);
        ivNVL1Figure2.setAlpha(0f);
        ivNVL1Figure3.setAlpha(0f);
        ivNVL1Drag1.setVisibility(View.GONE);
        ivNVL1Drag2.setVisibility(View.GONE);
        ivNVL1Drag3.setVisibility(View.GONE);
        btnCheckNVL1.setEnabled(false);
        btnRestartNVL1.setEnabled(false);
        strText = "¡Ve las posiciones!";
        tvShapesNVL1.setText(strText);
        ivNVL1Figure1.setImageResource(model.figure1Resource);
        ivNVL1Figure2.setImageResource(model.figure2Resource);
        ivNVL1Figure3.setImageResource(model.figure3Resource);
        ivNVL1Figure1.animate().alpha(1f).setDuration(500);
        ivNVL1Figure2.animate().alpha(1f).setDuration(500);
        ivNVL1Figure3.animate().alpha(1f).setDuration(500);
        ivNVL1Drag1.setImageResource(model.drag1Resource);
        ivNVL1Drag2.setImageResource(model.drag2Resource);
        ivNVL1Drag3.setImageResource(model.drag3Resource);
    }

    private void hideSequence() {
        ivNVL1Figure1.setEnabled(true);
        ivNVL1Figure2.setEnabled(true);
        ivNVL1Figure3.setEnabled(true);
        ivNVL1Drag1.setEnabled(true);
        ivNVL1Drag2.setEnabled(true);
        ivNVL1Drag3.setEnabled(true);
        ivNVL1Figure1.animate().alpha(0f).setDuration(500);
        ivNVL1Figure2.animate().alpha(0f).setDuration(500);
        ivNVL1Figure3.animate().alpha(0f).setDuration(500);
        btnRestartNVL1.setEnabled(true);
        btnContinueNVL1.setEnabled(false);
        strText = "¡Ponlas en su lugar!";
        tvShapesNVL1.setText(strText);
        ivNVL1Drag1.setVisibility(View.VISIBLE);
        ivNVL1Drag2.setVisibility(View.VISIBLE);
        ivNVL1Drag3.setVisibility(View.VISIBLE);
        ivNVL1Drag1.setAlpha(0f);
        ivNVL1Drag2.setAlpha(0f);
        ivNVL1Drag3.setAlpha(0f);
        ivNVL1Drag1.animate().alpha(1f).setDuration(1000);
        ivNVL1Drag2.animate().alpha(1f).setDuration(1000);
        ivNVL1Drag3.animate().alpha(1f).setDuration(1000);
        model.state = false;
    }

    private void setCheckBtnEnablement() {
        if (ivNVL1Figure1.getAlpha() == 1f && ivNVL1Figure2.getAlpha() == 1f && ivNVL1Figure3.getAlpha() == 1f) {
            btnCheckNVL1.setEnabled(true);
        }
    }

    private static class myClickListener implements View.OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            if(v.getAlpha() == 1f) {
                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                ClipData dragData = new ClipData((CharSequence) v.getTag(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(dragData, shadowBuilder, v, 0);
                return true;
            }
            else {
                return false;
            }
        }
    }

    private class myDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent dragEvent) {
            ImageView receiverView = (ImageView) v;
            final int action = dragEvent.getAction();
            switch(action) {
                case DragEvent.ACTION_DRAG_STARTED:
                case DragEvent.ACTION_DRAG_ENTERED:
                    return true;
                case DragEvent.ACTION_DROP:
                    int dragIndex = Integer.parseInt(String.valueOf(dragEvent.getClipDescription().getLabel()));
                    receiverView.setImageResource(model.drags.get(dragIndex));
                    receiverView.setAlpha(1f);
                    receiverView.setEnabled(false);
                    receiverView.setTag(dragIndex);
                    if (dragIndex == 0) {
                        ivNVL1Drag1.animate().alpha(0f).setDuration(1000);
                        ivNVL1Drag1.setEnabled(false);
                    }
                    if (dragIndex == 1) {
                        ivNVL1Drag2.animate().alpha(0f).setDuration(1000);
                        ivNVL1Drag2.setEnabled(false);
                    }
                    if (dragIndex == 2) {
                        ivNVL1Drag3.animate().alpha(0f).setDuration(1000);
                        ivNVL1Drag3.setEnabled(false);
                    }
                    setCheckBtnEnablement();
                    return true;
                default:
                    break;
            }
            return false;
        }
    }

    @Override
    public void onBackPressed() {
    }

}