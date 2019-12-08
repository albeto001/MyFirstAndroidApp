package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btS, btR, btM, btD, btI, btP, btC;
    public String ops = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = findViewById(R.id.editText);
        bt0 = findViewById(R.id.b0);
        bt1 = findViewById(R.id.b1);
        bt2 = findViewById(R.id.b2);
        bt3 = findViewById(R.id.b3);
        bt4 = findViewById(R.id.b4);
        bt5 = findViewById(R.id.b5);
        bt6 = findViewById(R.id.b6);
        bt7 = findViewById(R.id.b7);
        bt8 = findViewById(R.id.b8);
        bt9 = findViewById(R.id.b9);
        btS = findViewById(R.id.bS);
        btR = findViewById(R.id.bR);
        btM = findViewById(R.id.bM);
        btD = findViewById(R.id.bD);
        btI = findViewById(R.id.bI);
        btP = findViewById(R.id.bP);
        btC = findViewById(R.id.bC);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("1");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("2");
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("3");
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("4");
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("5");
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("6");
            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("7");
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("8");
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("9");
            }
        });
        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("0");
            }
        });
        btP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp(".");
            }
        });

        btD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("/");
            }
        });
        btM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("*");
            }
        });
        btS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("+");
            }
        });
        btR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOp("-");
            }
        });
        btC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ops = "";
                et1.setText("");
            }
        });
    }

    public void addOp(String op){
        String last, aux;
        last = (ops.equals("")) ? "0" : ops.substring(ops.length() -1);
        if(!op.matches("[0-9]") && op != ".") {
            if(!ops.equals("")) {
                if(!last.matches("[0-9]")){
                    ops = ops.substring(0, ops.length() - 1) + op;
                    aux =  et1.getText().toString();
                    et1.setText(aux.substring(0,aux.length() - 1) + op);
                }else {
                    ops += "," + op;
                    et1.setText(et1.getText() + op);
                }
            }
        }
        else{
            ops += last.matches("[0-9]") ? op : "," + op;
            et1.setText(et1.getText() + op);
        }

    }

    public void operar(View v){
        String[] opera;
        ArrayList<String> resultado;
        int a = 0;
        if(!ops.equals("")) {
            String last = ops.substring(ops.length() - 1);
            if(!last.matches("[0-9]")) {
                ops = ops.substring(0, ops.length() - 2);
                et1.setText(et1.getText().toString().substring(0,et1.getText().toString().length()-1));
            }
            opera = ops.split(",");
            ArrayList<String> operaList = new ArrayList<>();
            for(int i = 0; i< opera.length; i++){
                operaList.add(opera[i]);
            }

            calculadora(operaList);



        }
        else{
            et1.setText("0");
        }

    }

    public void calculadora(ArrayList<String> operaList){
        if(operaList.size() == 1){
            et1.setText(operaList.get(0));
            //Toast.makeText(this, operaList.get(0), Toast.LENGTH_SHORT).show();
            return;
        }
        float b, c;
        String o, o2;
        ArrayList<String> auxOpera = new ArrayList<>(operaList);
        String[] operaciones = {"*", "/", "-", "+"};
        for(int i = 0; i< operaciones.length; i++){
            o = operaciones[i];
            for(int x = 1; x < operaList.size(); x+=2){
                o2 = operaList.get(x);
                //Toast.makeText(this, o2 + "" , Toast.LENGTH_SHORT).show();
                if(o.equals(o2)){
                    //Toast.makeText(this, "asada" , Toast.LENGTH_SHORT).show();
                    switch (o){
                        case "*":
                            //se valida que no existan diviciones anteriores
                            if(x-3 > 0){
                                if(operaList.get(x-2).equals("/")){
                                    b = Float.parseFloat(operaList.get(x - 3));
                                    c = Float.parseFloat(operaList.get(x - 1));
                                    c = b / c;
                                    auxOpera.set(x-3, c + "");
                                    auxOpera.remove(x-1);
                                    auxOpera.remove(x-2);
                                    calculadora(auxOpera);
                                    return;
                                }
                            }
                            b = Float.parseFloat(operaList.get(x - 1));
                            c = Float.parseFloat(operaList.get(x + 1));
                            c = c * b;
                            auxOpera.set(x-1, c + "");
                            auxOpera.remove(x);
                            auxOpera.remove(x);
                            calculadora(auxOpera);
                            return;
                        case "/":
                            b = Float.parseFloat(operaList.get(x - 1));
                            c = Float.parseFloat(operaList.get(x + 1));
                            c = b / c;
                            auxOpera.set(x-1, c + "");
                            auxOpera.remove(x);
                            auxOpera.remove(x);
                            calculadora(auxOpera);
                            return;
                        case "+":
                            b = Float.parseFloat(operaList.get(x - 1));
                            c = Float.parseFloat(operaList.get(x + 1));
                            c = c + b;
                            auxOpera.set(x-1, c + "");
                            auxOpera.remove(x);
                            auxOpera.remove(x);
                            calculadora(auxOpera);
                            return;
                        case "-":
                            b = Float.parseFloat(operaList.get(x - 1));
                            c = Float.parseFloat(operaList.get(x + 1));
                            c = b - c;
                            auxOpera.set(x-1, c + "");
                            auxOpera.remove(x);
                            auxOpera.remove(x);
                            calculadora(auxOpera);
                            return;
                    }
                }
            }

            System.out.println(operaList);

        }
    }

}
