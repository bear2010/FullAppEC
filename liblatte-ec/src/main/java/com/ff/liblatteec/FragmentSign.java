package com.ff.liblatteec;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.ff.libbasiccore.BaseFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.regex.Pattern;

public class FragmentSign extends BaseFragment {
    TextInputEditText mName ;
    TextInputEditText mPw ;
    TextInputEditText mPhone ;
    TextInputEditText mEmail ;

    String TAG = FragmentSign.class.getName();

    @Override
    public Object setLayout() {
        return R.layout.layout_sign;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootview) {
        RecyclerView mRecyclerView;
        TextInputLayout mTextInputLayout;
        TextInputEditText mTextInputEditText;
        JSONObject m;

        mName = ((TextInputEditText) rootview.findViewById(R.id.tl_username));
        mPw = ((TextInputEditText) rootview.findViewById(R.id.tl_pw));
        mPhone = ((TextInputEditText) rootview.findViewById(R.id.tl_phone));
        mEmail = ((TextInputEditText) rootview.findViewById(R.id.tv_email));

        MaterialButton mCheck = ((MaterialButton) rootview.findViewById(R.id.btn_check));
        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });

        AppCompatButton mReset = ((AppCompatButton) rootview.findViewById(R.id.btn_reset));
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        Button mNext = ((Button) rootview.findViewById(R.id.btn_Next));
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });
    }

    private void checkInput()
    {
        mName.setError("Please reinput sth",getResources().getDrawable(R.mipmap.ic_launcher));
        mPw.setError("Please reinput sth");
        mPhone.setError("Please reinput sth");

        String mail = mEmail.getText().toString();
        Log.d(TAG, "checkInput: "+mail);
        Log.d(TAG, "checkInput: "+ Patterns.EMAIL_ADDRESS.matcher(mail).matches());
        if (mail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            Toast.makeText(getContext(),"Email is error",Toast.LENGTH_LONG);
        }
    }
    private void reset()
    {
        mName.setError(null);
        mPw.setError(null);
        mPhone.setError(null);
    }
}
