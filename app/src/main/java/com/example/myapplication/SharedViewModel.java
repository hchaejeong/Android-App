package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
public class SharedViewModel extends ViewModel{
    public MutableLiveData<Integer> radioGroup1CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup2CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup3CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup4CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup5CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup6CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup7CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup8CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup9CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup10CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup11CheckedId = new MutableLiveData<>();
    public MutableLiveData<Integer> radioGroup12CheckedId = new MutableLiveData<>();

    public MutableLiveData<Integer> getRadioGroup1CheckedId() {
        return radioGroup1CheckedId;
    }
    public void setRadioGroup1CheckedId(int checkedId) {
        radioGroup1CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup2CheckedId() {
        return radioGroup2CheckedId;
    }
    public void setRadioGroup2CheckedId(int checkedId) {
        radioGroup2CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup3CheckedId() {
        return radioGroup3CheckedId;
    }
    public void setRadioGroup3CheckedId(int checkedId) {
        radioGroup3CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup4CheckedId() {
        return radioGroup4CheckedId;
    }
    public void setRadioGroup4CheckedId(int checkedId) {
        radioGroup4CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup5CheckedId() {
        return radioGroup5CheckedId;
    }
    public void setRadioGroup5CheckedId(int checkedId) {
        radioGroup5CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup6CheckedId() {
        return radioGroup6CheckedId;
    }
    public void setRadioGroup6CheckedId(int checkedId) {
        radioGroup6CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup7CheckedId() {
        return radioGroup7CheckedId;
    }
    public void setRadioGroup7CheckedId(int checkedId) {
        radioGroup7CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup8CheckedId() {
        return radioGroup8CheckedId;
    }
    public void setRadioGroup8CheckedId(int checkedId) {
        radioGroup8CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup9CheckedId() {
        return radioGroup9CheckedId;
    }
    public void setRadioGroup9CheckedId(int checkedId) {
        radioGroup9CheckedId.setValue(checkedId);
    }
    public MutableLiveData<Integer> getRadioGroup10CheckedId() {
        return radioGroup10CheckedId;
    }
    public void setRadioGroup10CheckedId(int checkedId) {radioGroup10CheckedId.setValue(checkedId);}
    public MutableLiveData<Integer> getRadioGroup11CheckedId() {
        return radioGroup11CheckedId;
    }
    public void setRadioGroup11CheckedId(int checkedId) {radioGroup11CheckedId.setValue(checkedId);}
    public MutableLiveData<Integer> getRadioGroup12CheckedId() {
        return radioGroup12CheckedId;
    }
    public void setRadioGroup12CheckedId(int checkedId) {radioGroup12CheckedId.setValue(checkedId);}


    private int question1,question2,question3,question4,question5,question6;
    private int question7,question8,question9,question10,question11,question12;

    public int getData_1() {return question1;}
    public int getData_2() {return question2;}
    public int getData_3() {return question3;}
    public int getData_4() {return question4;}
    public int getData_5() {return question5;}
    public int getData_6() {return question6;}
    public int getData_7() {return question7;}
    public int getData_8() {return question8;}
    public int getData_9() {return question9;}
    public int getData_10() {return question10;}
    public int getData_11() {return question11;}
    public int getData_12() {return question12;}


    public void setData_1(int data){question1=data;}
    public void setData_2(int data){question2=data;}
    public void setData_3(int data){question3=data;}
    public void setData_4(int data){question4=data;}
    public void setData_5(int data){question5=data;}
    public void setData_6(int data){question6=data;}
    public void setData_7(int data){question7=data;}
    public void setData_8(int data){question8=data;}
    public void setData_9(int data){question9=data;}
    public void setData_10(int data){question10=data;}
    public void setData_11(int data){question11=data;}
    public void setData_12(int data){question12=data;}


}
