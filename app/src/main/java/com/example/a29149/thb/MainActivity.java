package com.example.a29149.thb;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a29149.thb.adapter.MultiItemImplAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;

    private EditText input;
    private EditText output;

    private TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Integer> integerList = new ArrayList<>();
        integerList.add(0);
        integerList.add(1);
        integerList.add(0);
        integerList.add(1);
        integerList.add(0);
        integerList.add(1);
        integerList.add(0);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(0);
        integerList.add(0);
        integerList.add(0);
        integerList.add(0);
        integerList.add(0);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new MultiItemImplAdapter(recyclerView, MainActivity.this, integerList));


        radioGroup = (RadioGroup) findViewById(R.id.rg);
        input = (EditText) findViewById(R.id.input);
        output = (EditText) findViewById(R.id.output);
        change = (TextView) findViewById(R.id.change);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.btt:
                        if (input.getText().toString().equals(""))
                            Toast.makeText(MainActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();

                        output.setText(Convert.binConvertToTen(input.getText().toString()));

                        break;
                    case R.id.ttb:
                        if (input.getText().toString().equals(""))
                            Toast.makeText(MainActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();

                        output.setText(Convert.tenConvertToBin(input.getText().toString()));
                        break;
                    case R.id.htt:
                        if (input.getText().toString().equals(""))
                            Toast.makeText(MainActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();

                        output.setText(Convert.hexConvertToTen(input.getText().toString()));
                        break;
                    case R.id.tth:
                        if (input.getText().toString().equals(""))
                            Toast.makeText(MainActivity.this, "输入不能为空！", Toast.LENGTH_SHORT).show();

                        output.setText(Convert.tenConvertToBin(input.getText().toString()));
                        break;
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = input.getText().toString();
                input.setText(output.getText().toString());
                output.setText(temp);
            }
        });

    }
}
