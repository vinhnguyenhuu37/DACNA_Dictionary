package com.example.vinhdknow.yandexdictionary.Fragment;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.example.vinhdknow.yandexdictionary.R;

public class Fragment_Translate extends Fragment {

    private static final String API_KEY = "trnsl.1.1.20141126T151929Z.2028746c57ef2cb5.29f3fed6a7b663d81c68ca53a58f5eb5e0077b5b";

    public static final String SRC_LANG = "src_lang";
    public static final String TARGET_LANG = "target_lang";
    public static final String SRC_TEXT = "src_text";
    public static final String TRANSLATION = "translation";

    Spinner SpinSrc,SpinTar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_translate,container,false);
        // Array language choices
        String [] values = {"English","Vietnamese"};
        SpinSrc=(Spinner)v.findViewById(R.id.spinner_SrcLang);
        SpinTar=(Spinner)v.findViewById(R.id.spinner_TarLang);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        SpinSrc.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        SpinTar.setAdapter(adapter2);
        return v;
    }
}
