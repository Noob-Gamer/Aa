package com.example.lottery.ui.mybet;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyBetViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MyBetViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Mybet fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
