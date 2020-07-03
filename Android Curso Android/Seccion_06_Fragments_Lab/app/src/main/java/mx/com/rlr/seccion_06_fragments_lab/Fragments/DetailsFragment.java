package mx.com.rlr.seccion_06_fragments_lab.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import mx.com.rlr.seccion_06_fragments_lab.Models.Mail;
import mx.com.rlr.seccion_06_fragments_lab.R;

public class DetailsFragment extends Fragment {

    private TextView textViewSubject;
    private TextView textViewEmail;
    private TextView textViewMessage;
    private LinearLayout wrapper;


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        textViewSubject = (TextView) view.findViewById(R.id.textViewFragmentSubject);
        textViewEmail = (TextView) view.findViewById(R.id.textViewFragmentSenderName);
        textViewMessage = (TextView) view.findViewById(R.id.textViewFragmentMessage);
        wrapper = (LinearLayout) view.findViewById(R.id.fragmentDetailsMailWrapper);

        // Inflate the layout for this fragment
        return view;
    }

    public void renderMail(Mail mail) {
        wrapper.setVisibility(View.VISIBLE);
        textViewSubject.setText(mail.getSubject());
        textViewEmail.setText(mail.getEmail());
        textViewMessage.setText(mail.getMessage());
    }
}