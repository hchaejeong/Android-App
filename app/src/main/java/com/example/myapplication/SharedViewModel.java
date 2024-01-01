package com.example.myapplication;

import androidx.lifecycle.ViewModel;
public class SharedViewModel extends ViewModel{

    private int question1,question2,question3,question4,question5,question6,question7,question8;
    private int question9,question10,question11,question12;

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
