package dank.com.translator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dank.com.translator.R;


public class TranslateFragment extends Fragment {



    public TranslateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        final EditText textToTranslateView = (EditText) view.findViewById(R.id.textToTranslate);
        final Button buttonTranslate = (Button) view.findViewById(R.id.buttonTranslate);
        final TextView translatedView = (TextView) view.findViewById(R.id.translatedText);

        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toTranslate = textToTranslateView.getText().toString();
                String translated = getTranslation(toTranslate);
                translatedView.setText(translated);
            }
        });

        return view;
    }

    // Stub method to retrieve "translated" string.
    // To be replaced with call to Yandex.Translator API.

    public String getTranslation(String string){
        return "Translated: " + string;
    }
}