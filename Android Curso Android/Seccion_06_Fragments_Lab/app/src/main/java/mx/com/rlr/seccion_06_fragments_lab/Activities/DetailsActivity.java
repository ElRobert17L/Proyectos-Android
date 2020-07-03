package mx.com.rlr.seccion_06_fragments_lab.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mx.com.rlr.seccion_06_fragments_lab.Fragments.DetailsFragment;
import mx.com.rlr.seccion_06_fragments_lab.Models.Mail;
import mx.com.rlr.seccion_06_fragments_lab.R;

public class DetailsActivity extends AppCompatActivity {

    private String subject;
    private String message;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (getIntent().getExtras() != null) {
            subject = getIntent().getStringExtra("subject");
            message = getIntent().getStringExtra("message");
            email = getIntent().getStringExtra("email");
        }

        Mail mail = new Mail(subject, message, email);

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDetailsMail);
        detailsFragment.renderMail(mail);
    }
}