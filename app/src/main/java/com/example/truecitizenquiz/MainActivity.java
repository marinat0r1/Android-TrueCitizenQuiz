package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    // Maybe implement previous Button
    private TextView questionTextView;
    
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            new Question(R.string.capi_jungla, true),
            new Question(R.string.flaconi_spitzname, true),
            new Question(R.string.chica_hamster, false),
            new Question(R.string.mounteverest_sare, false),
            new Question(R.string.question_capi_artist, true),
            new Question(R.string.question_marin_bea, false),
            new Question(R.string.sanda_bici, false)
            // add more ...
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();
                // TODO: Rotate different images here & in Question class
        }
    }

    private void updateQuestion(){
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
        Log.d("CurrentQuestionIndex", valueOf(currentQuestionIndex));
    }

    private void checkAnswer(boolean userAnswer){
        boolean answerIsTrue = questionBank[currentQuestionIndex].getAnswerTrue();

        int toastMessageId;

        if (userAnswer == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.false_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

}