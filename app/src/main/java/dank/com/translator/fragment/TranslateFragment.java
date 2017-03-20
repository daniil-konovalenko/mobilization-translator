package dank.com.translator.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import dank.com.translator.App;
import dank.com.translator.R;
import dank.com.translator.yandextranslale.model.Translation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
                if (!toTranslate.isEmpty()) {
                    Call<Translation> call = App.getApi().translate(toTranslate, "ru");
                    call.enqueue(new Callback<Translation>() {
                        @Override
                        public void onResponse(Call<Translation> call, Response<Translation> response) {
                            if (response.code() == 200) {
                                String translated = response.body().getText().toString();
                                translatedView.setText(translated);
                            }
                            else {
                                translatedView.setText(getString(R.string.error, String.valueOf(response.code()) + response.toString()));
                            }
                        }

                        @Override
                        public void onFailure(Call<Translation> call, Throwable t) {
                            translatedView.setText(getString(R.string.error, t.getMessage()));
                        }
                    });
                }

            }
        });

        return view;
    }

}