package com.example.caculate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import java.math.*;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt0,btadd,btde,btc,btchu,bts,bte,btclear,btdian,btjian;
    EditText input;
    boolean clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        btdian=(Button)findViewById(R.id.button20);//给上面定义的按钮与xml文件创建的按钮对应
        btclear= (Button)findViewById(R.id.button);
        bt7=(Button)findViewById(R.id.button5);
        bt8=(Button)findViewById(R.id.button6);
        bt9=(Button)findViewById(R.id.button7);
        bt1=(Button) findViewById(R.id.button13);
        bt2=(Button) findViewById(R.id.button14);
        bt3=(Button) findViewById(R.id.button15);
        bt4=(Button)findViewById(R.id.button9);
        bt5=(Button) findViewById(R.id.button10);
        bt6=(Button) findViewById(R.id.button11);
        bt0=(Button) findViewById(R.id.button19);
        btadd=(Button) findViewById(R.id.button2);
        btjian=(Button)findViewById(R.id.button3);
        btc=(Button) findViewById(R.id.button8);
        btchu=(Button) findViewById(R.id.button12);
        bts=(Button) findViewById(R.id.button17);
        bte=(Button) findViewById(R.id.button21);
        input=(EditText) findViewById(R.id.input);
        btde=(Button) findViewById(R.id.button4);
        bt1.setOnClickListener(this);//给每个按钮创建监听事件
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt0.setOnClickListener(this);
        btadd.setOnClickListener(this);
        btde.setOnClickListener(this);
        btc.setOnClickListener(this);
        btchu.setOnClickListener(this);
        bts.setOnClickListener(this);
        bte.setOnClickListener(this);
        btclear.setOnClickListener(this);
        btdian.setOnClickListener(this);
        btjian.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)//创建点击事件函数来判断点击出现的情况
            {
                String inputs=input.getText().toString();
                switch (view.getId())//数字
                {
                    case R.id.button13:
                    case R.id.button14:
                    case R.id.button15:
                    case R.id.button9:
                    case R.id.button10:
                    case R.id.button11:
                    case R.id.button5:
                    case R.id.button6:
                    case R.id.button7:
                    case R.id.button19:
                    case R.id.button20:
                if(clear)
                {
                    clear=false;
                }
                input.setText(inputs+((Button)view).getText());
                break;
            case R.id.button2://功能按钮
            case R.id.button3:
            case R.id.button8:
            case R.id.button12:
            case R.id.button17:
               if(clear)
               {
                   clear=false;
               }
               input.setText(inputs+""+((Button)view).getText()+"");
               break;
            case R.id.button4://删除一个
                if(clear)
                {
                    clear=false;
                }
                else if(inputs!=null ||inputs.equals(""))
                {
                    input.setText(inputs.substring(0,inputs.length()-1));
                }
                break;
            case R.id.button://清除
                input.setText("0");
                break;
            case R.id.button21://获得结果
                String c=input.getText().toString();
                addblock(c);
                getResult();
                break;
        }
    }

    private void addblock(String s)//添加空格（空格能够将操作数和运算符分割开）
    {
        int n=s.length();
        int count;
        char test;
        char []t=new char[20];
        test=s.charAt(0);
        if(test=='+'||test=='-'||test=='*'||test=='/'||test=='√')
        {
             StringBuilder sb=new StringBuilder(s);
             sb.insert(0,' ');
             input.setText(sb);
        }
        else
        {
            for(int i=0;i<n;i++)
            {
                t[i]=s.substring(i,i+1).charAt(0);
            }
            for(int j=0;j<n;j++)
            {
                if(t[j]=='+'||t[j]=='-'||t[j]=='*'||t[j]=='/')
                {
                    StringBuilder sb1=new StringBuilder(s);
                    sb1.insert(j,' ');
                    input.setText(sb1);
                    break;
                }
            }
        }
    }


    private void getResult() {//获取结果
        String content = input.getText().toString();//获取文本框内的内容
        if (content == null || content.equals(""))//考虑特殊情况
            return;
        if (!content.contains(" ")) {
            return;
        }
        if (clear) {
            clear = false;
            return;
        }
        clear = true;
        double result = 0;
        String s1 = content.substring(0, content.indexOf(" "));//获取操作数一
        String operation = content.substring(content.indexOf(" ")+1, content.indexOf(" ") + 2);//获取符号
        String s2 = content.substring(content.indexOf(" ") + 2);//获取操作数二
        if (!s1.equals("") && !s2.equals("")) {
            double sone = Double.parseDouble(s1);
            double stwo = Double.parseDouble(s2);
            if (operation.equals("+")) {//判断运算符不同的各种情况
                result = sone + stwo;
                input.setText(result+"");
            } else if (operation.equals("-")) {
                result = sone - stwo;
                input.setText(result+"");
            } else if (operation.equals("*")) {
                result = sone * stwo;
                input.setText(result+"");
            } else if (operation.equals("/")) {

                result = sone / stwo;
                if(stwo==0)
                {
                    Toast.makeText(this, "除数不能为0", Toast.LENGTH_SHORT).show();
                    result=0;
                    input.setText(result+"");
                }
            }
            if (!s1.contains(".") && !s2.contains(".") && !operation.equals("/")) {
                input.setText(result + "");
            } else {
                input.setText(result + "");
            }
            if (!s1.contains(".") && !s2.contains(".") && operation.equals("/")) {
                int r = (int) result;
                input.setText(r + "");
            } else {
                input.setText(result + "");
            }
        }
        else if(!s1.equals("")&&s2.equals(""))
        {
            input.setText(content);
        }
        else if(s1.equals("")&&!s2.equals(""))//判断操作数是否存在小数点的情况
        {
            double d2=Double.parseDouble(s2);
            if(operation.equals("+"))
            {
                result=0+d2;
            }
            else if(operation.equals("-"))
            {
                result=0-d2;
            }
            else if(operation.equals("*"))
            {
                result=0;
            }
            else if(operation.equals("/"))
            {
                result=0;
            }
            else if(operation.equals("√"))
            {
                if(d2<0)
                {
                    Toast toast=Toast.makeText(this,"负数不能开平方根",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                }
                result=Math.sqrt(d2);
            }
            if(!s1.contains(".")&&!s2.contains("."))
            {

                input.setText(result+"");
            }
            else
            {
                input.setText(result+"");
            }
        }
        else
        {
            input.setText(""+result);
        }

    }

}