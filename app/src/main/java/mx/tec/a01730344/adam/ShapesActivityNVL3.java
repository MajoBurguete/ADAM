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

public class ShapesActivityNVL3 extends AppCompatActivity {

    ShapesModelNVL3 model;
    ImageButton ibPauseShapes3;
    ImageView ivNVL3Figure1;
    ImageView ivNVL3Figure2;
    ImageView ivNVL3Figure3;
    ImageView ivNVL3Figure4;
    ImageView ivNVL3Figure5;
    ImageView ivNVL3Drag1;
    ImageView ivNVL3Drag2;
    ImageView ivNVL3Drag3;
    ImageView ivNVL3Drag4;
    ImageView ivNVL3Drag5;
    ImageView ivLife1SH3;
    ImageView ivLife2SH3;
    ImageView ivLife3SH3;
    TextView tvShapesNVL3;
    TextView tvScoreShapesNVL3;
    Button btnContinueNVL3;
    Button btnCheckNVL3;
    Button btnRestartNVL3;
    String strText;
    String strScore;
    int score = 0;
    int lives = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_layout_nvl_3);

        model = new ShapesModelNVL3();
        ibPauseShapes3 = findViewById(R.id.ibPauseShapes3);
        ivNVL3Figure1 = findViewById(R.id.ivNVL3Figure1);
        ivNVL3Figure2 = findViewById(R.id.ivNVL3Figure2);
        ivNVL3Figure3 = findViewById(R.id.ivNVL3Figure3);
        ivNVL3Figure4 = findViewById(R.id.ivNVL3Figure4);
        ivNVL3Figure5 = findViewById(R.id.ivNVL3Figure5);
        ivNVL3Drag1 = findViewById(R.id.ivNVL3Drag1);
        ivNVL3Drag2 = findViewById(R.id.ivNVL3Drag2);
        ivNVL3Drag3 = findViewById(R.id.ivNVL3Drag3);
        ivNVL3Drag4 = findViewById(R.id.ivNVL3Drag4);
        ivNVL3Drag5 = findViewById(R.id.ivNVL3Drag5);
        ivLife1SH3 = findViewById(R.id.ivLife1SH3);
        ivLife2SH3 = findViewById(R.id.ivLife2SH3);
        ivLife3SH3 = findViewById(R.id.ivLife3SH3);
        tvShapesNVL3 = findViewById(R.id.tvShapesNVL3);
        tvScoreShapesNVL3 = findViewById(R.id.tvScoreShapesNVL3);
        btnContinueNVL3 = findViewById(R.id.btnContinueNVL3);
        btnCheckNVL3 = findViewById(R.id.btnCheckNVL3);
        btnRestartNVL3 = findViewById(R.id.btnRestartNVL3);

        ivNVL3Figure1.setOnDragListener(new myDragListener());
        ivNVL3Figure2.setOnDragListener(new myDragListener());
        ivNVL3Figure3.setOnDragListener(new myDragListener());
        ivNVL3Figure4.setOnDragListener(new myDragListener());
        ivNVL3Figure5.setOnDragListener(new myDragListener());
        ivNVL3Drag1.setOnTouchListener(new myClickListener());
        ivNVL3Drag2.setOnTouchListener(new myClickListener());
        ivNVL3Drag3.setOnTouchListener(new myClickListener());
        ivNVL3Drag4.setOnTouchListener(new myClickListener());
        ivNVL3Drag5.setOnTouchListener(new myClickListener());


        ibPauseShapes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.flPauseShapes3, new PauseFragment()).commit();
            }
        });

        btnContinueNVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.state) {
                    hideSequence();
                }
                else {
                    model.startNewRound();
                    setLayoutAttributes();
                }
            }
        });

        btnCheckNVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int correctAnswers = 0;
                int figure1Tag = Integer.parseInt(ivNVL3Figure1.getTag().toString());
                int figure2Tag = Integer.parseInt(ivNVL3Figure2.getTag().toString());
                int figure3Tag = Integer.parseInt(ivNVL3Figure3.getTag().toString());
                int figure4Tag = Integer.parseInt(ivNVL3Figure4.getTag().toString());
                int figure5Tag = Integer.parseInt(ivNVL3Figure5.getTag().toString());
                if (model.figures.get(0).equals(model.drags.get(figure1Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure1.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure1.setEnabled(true);
                    if (figure1Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure1Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure1Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure1Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure1Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                if (model.figures.get(1).equals(model.drags.get(figure2Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure2.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure2.setEnabled(true);
                    if (figure2Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure2Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure2Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure2Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure2Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                if (model.figures.get(2).equals(model.drags.get(figure3Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure3.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure3.setEnabled(true);
                    if (figure3Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure3Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure3Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure3Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure3Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                if (model.figures.get(3).equals(model.drags.get(figure4Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure4.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure4.setEnabled(true);
                    if (figure4Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure4Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure4Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure4Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure4Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                if (model.figures.get(4).equals(model.drags.get(figure5Tag))) {
                    correctAnswers ++;
                }
                else {
                    ivNVL3Figure5.animate().alpha(0f).setDuration(500);
                    ivNVL3Figure5.setEnabled(true);
                    if (figure5Tag == 0) {
                        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(true);
                    }
                    if (figure5Tag == 1) {
                        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(true);
                    }
                    if (figure5Tag == 2) {
                        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(true);
                    }
                    if (figure5Tag == 3) {
                        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(true);
                    }
                    if (figure5Tag == 4) {
                        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(true);
                    }
                }
                if (correctAnswers == 5) {
                    strText = "¡Muy bien!";
                    btnContinueNVL3.setEnabled(true);
                    btnRestartNVL3.setEnabled(false);
                    score += lives * 10 * 3;
                    strScore = "Puntuación: " + score;
                    tvScoreShapesNVL3.setText(strScore);
                }
                else {
                    lives --;
                    if (lives == 2) {
                        ivLife3SH3.setImageResource(R.drawable.ic_adam_dead);
                    }
                    if (lives == 1) {
                        ivLife2SH3.setImageResource(R.drawable.ic_adam_dead);
                    }
                    if (lives == 0) {
                        ivLife1SH3.setImageResource(R.drawable.ic_adam_dead);
                        Intent toGameOver = new Intent(ShapesActivityNVL3.this, HomeActivity.class);
                        startActivity(toGameOver);
                    }
                    strText = "¡Intenta de nuevo!";
                }
                btnCheckNVL3.setEnabled(false);
                tvShapesNVL3.setText(strText);
            }
        });

        btnRestartNVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivNVL3Figure1.animate().alpha(0f).setDuration(500);
                ivNVL3Figure2.animate().alpha(0f).setDuration(500);
                ivNVL3Figure3.animate().alpha(0f).setDuration(500);
                ivNVL3Figure4.animate().alpha(0f).setDuration(500);
                ivNVL3Figure5.animate().alpha(0f).setDuration(500);
                ivNVL3Figure1.setEnabled(true);
                ivNVL3Figure2.setEnabled(true);
                ivNVL3Figure3.setEnabled(true);
                ivNVL3Figure4.setEnabled(true);
                ivNVL3Figure5.setEnabled(true);
                ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
                ivNVL3Drag1.setEnabled(true);
                ivNVL3Drag2.setEnabled(true);
                ivNVL3Drag3.setEnabled(true);
                ivNVL3Drag4.setEnabled(true);
                ivNVL3Drag5.setEnabled(true);
                btnCheckNVL3.setEnabled(false);
            }
        });

        model.startNewRound();
        setLayoutAttributes();

    }

    private void setLayoutAttributes() {
        ivNVL3Figure1.setAlpha(0f);
        ivNVL3Figure2.setAlpha(0f);
        ivNVL3Figure3.setAlpha(0f);
        ivNVL3Figure4.setAlpha(0f);
        ivNVL3Figure5.setAlpha(0f);
        ivNVL3Drag1.setVisibility(View.GONE);
        ivNVL3Drag2.setVisibility(View.GONE);
        ivNVL3Drag3.setVisibility(View.GONE);
        ivNVL3Drag4.setVisibility(View.GONE);
        ivNVL3Drag5.setVisibility(View.GONE);
        btnCheckNVL3.setEnabled(false);
        btnRestartNVL3.setEnabled(false);
        strText = "¡Ve las posiciones!";
        tvShapesNVL3.setText(strText);
        ivNVL3Figure1.setImageResource(model.figure1Resource);
        ivNVL3Figure2.setImageResource(model.figure2Resource);
        ivNVL3Figure3.setImageResource(model.figure3Resource);
        ivNVL3Figure4.setImageResource(model.figure4Resource);
        ivNVL3Figure5.setImageResource(model.figure5Resource);
        ivNVL3Figure1.animate().alpha(1f).setDuration(500);
        ivNVL3Figure2.animate().alpha(1f).setDuration(500);
        ivNVL3Figure3.animate().alpha(1f).setDuration(500);
        ivNVL3Figure4.animate().alpha(1f).setDuration(500);
        ivNVL3Figure5.animate().alpha(1f).setDuration(500);
        ivNVL3Drag1.setImageResource(model.drag1Resource);
        ivNVL3Drag2.setImageResource(model.drag2Resource);
        ivNVL3Drag3.setImageResource(model.drag3Resource);
        ivNVL3Drag4.setImageResource(model.drag4Resource);
        ivNVL3Drag5.setImageResource(model.drag5Resource);
    }

    private void hideSequence() {
        ivNVL3Figure1.setEnabled(true);
        ivNVL3Figure2.setEnabled(true);
        ivNVL3Figure3.setEnabled(true);
        ivNVL3Figure4.setEnabled(true);
        ivNVL3Figure5.setEnabled(true);
        ivNVL3Drag1.setEnabled(true);
        ivNVL3Drag2.setEnabled(true);
        ivNVL3Drag3.setEnabled(true);
        ivNVL3Drag4.setEnabled(true);
        ivNVL3Drag5.setEnabled(true);
        ivNVL3Figure1.animate().alpha(0f).setDuration(500);
        ivNVL3Figure2.animate().alpha(0f).setDuration(500);
        ivNVL3Figure3.animate().alpha(0f).setDuration(500);
        ivNVL3Figure4.animate().alpha(0f).setDuration(500);
        ivNVL3Figure5.animate().alpha(0f).setDuration(500);
        btnRestartNVL3.setEnabled(true);
        btnContinueNVL3.setEnabled(false);
        strText = "¡Ponlas en su lugar!";
        tvShapesNVL3.setText(strText);
        ivNVL3Drag1.setVisibility(View.VISIBLE);
        ivNVL3Drag2.setVisibility(View.VISIBLE);
        ivNVL3Drag3.setVisibility(View.VISIBLE);
        ivNVL3Drag4.setVisibility(View.VISIBLE);
        ivNVL3Drag5.setVisibility(View.VISIBLE);
        ivNVL3Drag1.setAlpha(0f);
        ivNVL3Drag2.setAlpha(0f);
        ivNVL3Drag3.setAlpha(0f);
        ivNVL3Drag4.setAlpha(0f);
        ivNVL3Drag5.setAlpha(0f);
        ivNVL3Drag1.animate().alpha(1f).setDuration(1000);
        ivNVL3Drag2.animate().alpha(1f).setDuration(1000);
        ivNVL3Drag3.animate().alpha(1f).setDuration(1000);
        ivNVL3Drag4.animate().alpha(1f).setDuration(1000);
        ivNVL3Drag5.animate().alpha(1f).setDuration(1000);
        model.state = false;
    }

    private void setCheckBtnEnablement() {
        if (ivNVL3Figure1.getAlpha() == 1f && ivNVL3Figure2.getAlpha() == 1f && ivNVL3Figure3.getAlpha() == 1f && ivNVL3Figure4.getAlpha() == 1f && ivNVL3Figure5.getAlpha() == 1f) {
            btnCheckNVL3.setEnabled(true);
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
                        ivNVL3Drag1.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag1.setEnabled(false);
                    }
                    if (dragIndex == 1) {
                        ivNVL3Drag2.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag2.setEnabled(false);
                    }
                    if (dragIndex == 2) {
                        ivNVL3Drag3.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag3.setEnabled(false);
                    }
                    if (dragIndex == 3) {
                        ivNVL3Drag4.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag4.setEnabled(false);
                    }
                    if (dragIndex == 4) {
                        ivNVL3Drag5.animate().alpha(0f).setDuration(1000);
                        ivNVL3Drag5.setEnabled(false);
                    }
                    setCheckBtnEnablement();
                    return true;
                default:
                    break;
            }
            return false;
        }
    }

}