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

public class ShapesActivityNVL2 extends AppCompatActivity {

    ShapesModelNVL2 model;
    ImageButton ibPauseShapes2;
    ImageView ivNVL2Figure1;
    ImageView ivNVL2Figure2;
    ImageView ivNVL2Figure3;
    ImageView ivNVL2Figure4;
    ImageView ivNVL2Drag1;
    ImageView ivNVL2Drag2;
    ImageView ivNVL2Drag3;
    ImageView ivNVL2Drag4;
    ImageView ivLife1SH2;
    ImageView ivLife2SH2;
    ImageView ivLife3SH2;
    TextView tvShapesNVL2;
    TextView tvScoreShapesNVL2;
    Button btnContinueNVL2;
    Button btnCheckNVL2;
    Button btnRestartNVL2;
    String strText;
    String strScore;
    int score = 0;
    int lives = 3;
    User user = new User(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_layout_nvl_2);

        user.loadProfiles();
        model = new ShapesModelNVL2();
        ibPauseShapes2 = findViewById(R.id.ibPauseShapes2);
        ivNVL2Figure1 = findViewById(R.id.ivNVL2Figure1);
        ivNVL2Figure2 = findViewById(R.id.ivNVL2Figure2);
        ivNVL2Figure3 = findViewById(R.id.ivNVL2Figure3);
        ivNVL2Figure4 = findViewById(R.id.ivNVL2Figure4);
        ivNVL2Drag1 = findViewById(R.id.ivNVL2Drag1);
        ivNVL2Drag2 = findViewById(R.id.ivNVL2Drag2);
        ivNVL2Drag3 = findViewById(R.id.ivNVL2Drag3);
        ivNVL2Drag4 = findViewById(R.id.ivNVL2Drag4);
        ivLife1SH2 = findViewById(R.id.ivLife1SH2);
        ivLife2SH2 = findViewById(R.id.ivLife2SH2);
        ivLife3SH2 = findViewById(R.id.ivLife3SH2);
        tvShapesNVL2 = findViewById(R.id.tvShapesNVL2);
        tvScoreShapesNVL2 = findViewById(R.id.tvScoreShapesNVL2);
        btnContinueNVL2 = findViewById(R.id.btnContinueNVL2);
        btnCheckNVL2 = findViewById(R.id.btnCheckNVL2);
        btnRestartNVL2 = findViewById(R.id.btnRestartNVL2);

        ivNVL2Figure1.setOnDragListener(new myDragListener());
        ivNVL2Figure2.setOnDragListener(new myDragListener());
        ivNVL2Figure3.setOnDragListener(new myDragListener());
        ivNVL2Figure4.setOnDragListener(new myDragListener());
        ivNVL2Drag1.setOnTouchListener(new myClickListener());
        ivNVL2Drag2.setOnTouchListener(new myClickListener());
        ivNVL2Drag3.setOnTouchListener(new myClickListener());
        ivNVL2Drag4.setOnTouchListener(new myClickListener());


        ibPauseShapes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flPauseShapes2, new PauseFragment()).commit();
            }
        });

        btnContinueNVL2.setOnClickListener(new View.OnClickListener() {
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
                    ft.replace(R.id.flPauseShapes2, fragment).commit();
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

        btnCheckNVL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int correctAnswers = 0;
                int figure1Tag = Integer.parseInt(ivNVL2Figure1.getTag().toString());
                int figure2Tag = Integer.parseInt(ivNVL2Figure2.getTag().toString());
                int figure3Tag = Integer.parseInt(ivNVL2Figure3.getTag().toString());
                int figure4Tag = Integer.parseInt(ivNVL2Figure4.getTag().toString());
                if (model.figures.get(0).equals(model.drags.get(figure1Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL2Figure1.animate().alpha(0f).setDuration(500);
                    ivNVL2Figure1.setEnabled(true);
                    if (figure1Tag == 0) {
                        ivNVL2Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag1.setEnabled(true);
                    }
                    if (figure1Tag == 1) {
                        ivNVL2Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag2.setEnabled(true);
                    }
                    if (figure1Tag == 2) {
                        ivNVL2Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag3.setEnabled(true);
                    }
                    if (figure1Tag == 3) {
                        ivNVL2Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag4.setEnabled(true);
                    }
                }
                if (model.figures.get(1).equals(model.drags.get(figure2Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL2Figure2.animate().alpha(0f).setDuration(500);
                    ivNVL2Figure2.setEnabled(true);
                    if (figure2Tag == 0) {
                        ivNVL2Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag1.setEnabled(true);
                    }
                    if (figure2Tag == 1) {
                        ivNVL2Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag2.setEnabled(true);
                    }
                    if (figure2Tag == 2) {
                        ivNVL2Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag3.setEnabled(true);
                    }
                    if (figure2Tag == 3) {
                        ivNVL2Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag4.setEnabled(true);
                    }
                }
                if (model.figures.get(2).equals(model.drags.get(figure3Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL2Figure3.animate().alpha(0f).setDuration(500);
                    ivNVL2Figure3.setEnabled(true);
                    if (figure3Tag == 0) {
                        ivNVL2Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag1.setEnabled(true);
                    }
                    if (figure3Tag == 1) {
                        ivNVL2Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag2.setEnabled(true);
                    }
                    if (figure3Tag == 2) {
                        ivNVL2Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag3.setEnabled(true);
                    }
                    if (figure3Tag == 3) {
                        ivNVL2Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag4.setEnabled(true);
                    }
                }
                if (model.figures.get(3).equals(model.drags.get(figure4Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL2Figure4.animate().alpha(0f).setDuration(500);
                    ivNVL2Figure4.setEnabled(true);
                    if (figure4Tag == 0) {
                        ivNVL2Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag1.setEnabled(true);
                    }
                    if (figure4Tag == 1) {
                        ivNVL2Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag2.setEnabled(true);
                    }
                    if (figure4Tag == 2) {
                        ivNVL2Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag3.setEnabled(true);
                    }
                    if (figure4Tag == 3) {
                        ivNVL2Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL2Drag4.setEnabled(true);
                    }
                }
                if (correctAnswers == 4) {
                    strText = "¡Muy bien!";
                    btnContinueNVL2.setEnabled(true);
                    btnRestartNVL2.setEnabled(false);
                    score += lives * 20 * 2;
                    strScore = "Puntaje: " + score;
                    tvScoreShapesNVL2.setText(strScore);
                }
                else {
                    lives --;
                    if (lives == 2) {
                        ivLife3SH2.setImageResource(R.drawable.ic_adam_dead);
                    }
                    if (lives == 1) {
                        ivLife2SH2.setImageResource(R.drawable.ic_adam_dead);
                    }
                    strText = "¡Intenta de nuevo!";
                    if (lives == 0) {
                        ivLife1SH2.setImageResource(R.drawable.ic_adam_dead);
                        strText = "¡Juego terminado!";
                        ivNVL2Figure1.setEnabled(false);
                        ivNVL2Figure2.setEnabled(false);
                        ivNVL2Figure3.setEnabled(false);
                        ivNVL2Figure4.setEnabled(false);
                        ivNVL2Figure1.setAlpha(0f);
                        ivNVL2Figure2.setAlpha(0f);
                        ivNVL2Figure3.setAlpha(0f);
                        ivNVL2Figure4.setAlpha(0f);
                        ivNVL2Figure1.setImageResource(model.figures.get(0));
                        ivNVL2Figure2.setImageResource(model.figures.get(1));
                        ivNVL2Figure3.setImageResource(model.figures.get(2));
                        ivNVL2Figure4.setImageResource(model.figures.get(3));
                        ivNVL2Figure1.animate().alpha(1f).setDuration(2000);
                        ivNVL2Figure2.animate().alpha(1f).setDuration(2000);
                        ivNVL2Figure3.animate().alpha(1f).setDuration(2000);
                        ivNVL2Figure4.animate().alpha(1f).setDuration(2000);
                        ivNVL2Drag1.setVisibility(View.INVISIBLE);
                        ivNVL2Drag2.setVisibility(View.INVISIBLE);
                        ivNVL2Drag3.setVisibility(View.INVISIBLE);
                        ivNVL2Drag4.setVisibility(View.INVISIBLE);
                        ivNVL2Drag1.setEnabled(false);
                        ivNVL2Drag2.setEnabled(false);
                        ivNVL2Drag3.setEnabled(false);
                        ivNVL2Drag4.setEnabled(false);
                        btnRestartNVL2.setEnabled(false);
                        btnContinueNVL2.setEnabled(true);
                    }
                }
                btnCheckNVL2.setEnabled(false);
                tvShapesNVL2.setText(strText);
            }
        });

        btnRestartNVL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivNVL2Figure1.animate().alpha(0f).setDuration(500);
                ivNVL2Figure2.animate().alpha(0f).setDuration(500);
                ivNVL2Figure3.animate().alpha(0f).setDuration(500);
                ivNVL2Figure4.animate().alpha(0f).setDuration(500);
                ivNVL2Figure1.setEnabled(true);
                ivNVL2Figure2.setEnabled(true);
                ivNVL2Figure3.setEnabled(true);
                ivNVL2Figure4.setEnabled(true);
                ivNVL2Drag1.animate().alpha(1f).setDuration(1000);
                ivNVL2Drag2.animate().alpha(1f).setDuration(1000);
                ivNVL2Drag3.animate().alpha(1f).setDuration(1000);
                ivNVL2Drag4.animate().alpha(1f).setDuration(1000);
                ivNVL2Drag1.setEnabled(true);
                ivNVL2Drag2.setEnabled(true);
                ivNVL2Drag3.setEnabled(true);
                ivNVL2Drag4.setEnabled(true);
                btnCheckNVL2.setEnabled(false);
            }
        });

        model.startNewRound();
        setLayoutAttributes();

    }

    private void setLayoutAttributes() {
        ivNVL2Figure1.setAlpha(0f);
        ivNVL2Figure2.setAlpha(0f);
        ivNVL2Figure3.setAlpha(0f);
        ivNVL2Figure4.setAlpha(0f);
        ivNVL2Drag1.setVisibility(View.GONE);
        ivNVL2Drag2.setVisibility(View.GONE);
        ivNVL2Drag3.setVisibility(View.GONE);
        ivNVL2Drag4.setVisibility(View.GONE);
        btnCheckNVL2.setEnabled(false);
        btnRestartNVL2.setEnabled(false);
        strText = "¡Ve las posiciones!";
        tvShapesNVL2.setText(strText);
        ivNVL2Figure1.setImageResource(model.figure1Resource);
        ivNVL2Figure2.setImageResource(model.figure2Resource);
        ivNVL2Figure3.setImageResource(model.figure3Resource);
        ivNVL2Figure4.setImageResource(model.figure4Resource);
        ivNVL2Figure1.animate().alpha(1f).setDuration(500);
        ivNVL2Figure2.animate().alpha(1f).setDuration(500);
        ivNVL2Figure3.animate().alpha(1f).setDuration(500);
        ivNVL2Figure4.animate().alpha(1f).setDuration(500);
        ivNVL2Drag1.setImageResource(model.drag1Resource);
        ivNVL2Drag2.setImageResource(model.drag2Resource);
        ivNVL2Drag3.setImageResource(model.drag3Resource);
        ivNVL2Drag4.setImageResource(model.drag4Resource);
    }

    private void hideSequence() {
        ivNVL2Figure1.setEnabled(true);
        ivNVL2Figure2.setEnabled(true);
        ivNVL2Figure3.setEnabled(true);
        ivNVL2Figure4.setEnabled(true);
        ivNVL2Drag1.setEnabled(true);
        ivNVL2Drag2.setEnabled(true);
        ivNVL2Drag3.setEnabled(true);
        ivNVL2Drag4.setEnabled(true);
        ivNVL2Figure1.animate().alpha(0f).setDuration(500);
        ivNVL2Figure2.animate().alpha(0f).setDuration(500);
        ivNVL2Figure3.animate().alpha(0f).setDuration(500);
        ivNVL2Figure4.animate().alpha(0f).setDuration(500);
        btnRestartNVL2.setEnabled(true);
        btnContinueNVL2.setEnabled(false);
        strText = "¡Ponlas en su lugar!";
        tvShapesNVL2.setText(strText);
        ivNVL2Drag1.setVisibility(View.VISIBLE);
        ivNVL2Drag2.setVisibility(View.VISIBLE);
        ivNVL2Drag3.setVisibility(View.VISIBLE);
        ivNVL2Drag4.setVisibility(View.VISIBLE);
        ivNVL2Drag1.setAlpha(0f);
        ivNVL2Drag2.setAlpha(0f);
        ivNVL2Drag3.setAlpha(0f);
        ivNVL2Drag4.setAlpha(0f);
        ivNVL2Drag1.animate().alpha(1f).setDuration(1000);
        ivNVL2Drag2.animate().alpha(1f).setDuration(1000);
        ivNVL2Drag3.animate().alpha(1f).setDuration(1000);
        ivNVL2Drag4.animate().alpha(1f).setDuration(1000);
        model.state = false;
    }

    private void setCheckBtnEnablement() {
        if (ivNVL2Figure1.getAlpha() == 1f && ivNVL2Figure2.getAlpha() == 1f && ivNVL2Figure3.getAlpha() == 1f && ivNVL2Figure4.getAlpha() == 1f) {
            btnCheckNVL2.setEnabled(true);
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
                        ivNVL2Drag1.animate().alpha(0f).setDuration(1000);
                        ivNVL2Drag1.setEnabled(false);
                    }
                    if (dragIndex == 1) {
                        ivNVL2Drag2.animate().alpha(0f).setDuration(1000);
                        ivNVL2Drag2.setEnabled(false);
                    }
                    if (dragIndex == 2) {
                        ivNVL2Drag3.animate().alpha(0f).setDuration(1000);
                        ivNVL2Drag3.setEnabled(false);
                    }
                    if (dragIndex == 3) {
                        ivNVL2Drag4.animate().alpha(0f).setDuration(1000);
                        ivNVL2Drag4.setEnabled(false);
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