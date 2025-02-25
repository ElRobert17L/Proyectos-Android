package mx.com.rlr.seccion_06_fragments_lab.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import mx.com.rlr.seccion_06_fragments_lab.Adapters.MailAdapter;
import mx.com.rlr.seccion_06_fragments_lab.Models.Mail;
import mx.com.rlr.seccion_06_fragments_lab.R;
import mx.com.rlr.seccion_06_fragments_lab.Utils.Util;

public class ListFragment  extends Fragment {

    private ListView listView;
    private List<Mail> mails;
    private MailAdapter adapter;

    private OnFragmentInteractionListener mListener;


    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        listView = (ListView) view.findViewById(R.id.listViewMails);
        mails = Util.getDummyData();
        adapter = new MailAdapter(getContext(), R.layout.list_view_mail, mails);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mListener.onListClick(mails.get(position));
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onListClick(Mail mail);
    }
}