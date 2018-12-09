package com.example.crazynet.simulationtask;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.table)
    TableLayout tableLayout;
    @BindView(R.id.my_table)
    TableLayout mtableLayout;

    ArrayList<customer> arrayList = new ArrayList<>();

    int LastTime ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.start)
    public void start()
    {
        Random rand = new Random();
        for(int i = 0 ;i<4;i++){
            arrayList.add(new customer(rand.nextInt(11),rand.nextInt(11)));
        }
        Collections.sort(arrayList,new customerSorter());
        calcTurn();
    }

    public void calcTurn() // to calculate the time of start and end of each cutomer
    {

        // set values of first customer
        arrayList.get(0).setStart(arrayList.get(0).getCome());
        arrayList.get(0).setWait(0);
        arrayList.get(0).setEnd(arrayList.get(0).getPeriod() + arrayList.get(0).getCome());

        // set values of other cutomers
        for(int i = 1 ; i< arrayList.size() ; i++){
            if (arrayList.get(i).getCome() >= arrayList.get(i-1).getEnd())
            {
                // in case of customer arrive and the teller is empty
                arrayList.get(i).setWait(0);
                arrayList.get(i).setStart(arrayList.get(i).getCome());
                arrayList.get(i).setEnd(  ( arrayList.get(i).getCome() + arrayList.get(i).getPeriod() )  );
            } else {

                // in case of customer arrive and the teller is not empty
                arrayList.get(i).setWait( arrayList.get(i-1).getEnd() - arrayList.get(i).getCome() );
                arrayList.get(i).setStart( arrayList.get(i-1).getEnd() );
                arrayList.get(i).setEnd( arrayList.get(i).getStart() + arrayList.get(i).getPeriod() );
            }
        }

        // get last minute in the bank
        LastTime = arrayList.get((arrayList.size()-1)).getEnd();

        showTable();
        showMyTable();
    }

    public void showTable(){
        tableLayout.setVisibility(View.VISIBLE);

        for(int i = 0 ; i<= LastTime ; i++){
            TableRow row = new TableRow(this);
            TextView time = new TextView(this);
            TextView c1 = new TextView(this);
            TextView c2 = new TextView(this);
            TextView c3 = new TextView(this);
            TextView c4 = new TextView(this);


            time.setText(String.valueOf(i));
            boolean flag = false;

            for (int j =0 ; j < 4 ;j++)
            {
                if(arrayList.get(j).getCome() <= i && arrayList.get(j).getEnd() > i){
                    switch (j){
                        case 0:
                            c1.setWidth(10);
                            c1.setHeight(50);
                            if(!flag) {
                                c1.setBackgroundColor(getResources().getColor(R.color.black));
                                flag = true;
                            }else {
                                c1.setBackgroundColor(getResources().getColor(R.color.red));
                            }
                            break;
                        case 1 :
                            c2.setWidth(10);
                            c2.setHeight(50);
                            if(!flag) {
                                c2.setBackgroundColor(getResources().getColor(R.color.black));
                                flag = true;
                            }else {
                                c2.setBackgroundColor(getResources().getColor(R.color.red));
                            }
                            break;
                        case 2 :
                            c3.setWidth(10);
                            c3.setHeight(50);
                            if(!flag) {
                                c3.setBackgroundColor(getResources().getColor(R.color.black));
                                flag = true;
                            }else {
                                c3.setBackgroundColor(getResources().getColor(R.color.red));
                            }
                            break;
                        case 3 :
                            c4.setWidth(10);
                            c4.setHeight(50);
                            if(!flag) {
                                c4.setBackgroundColor(getResources().getColor(R.color.black));
                                flag = true;
                            }else {
                                c4.setBackgroundColor(getResources().getColor(R.color.red));
                            }
                            break;
                        default:
                            break;
                    }

                }

            }

            row.addView(time);
            row.addView(c1);
            row.addView(c2);
            row.addView(c3);
            row.addView(c4);

            tableLayout.addView(row);

        }
    }

    public void showMyTable()
    {
        mtableLayout.setVisibility(View.VISIBLE);
        for(int i = 0 ; i< arrayList.size() ; i++){
            TableRow row = new TableRow(this);
            TextView customer = new TextView(this);
            TextView come = new TextView(this);
            TextView period = new TextView(this);
            TextView start = new TextView(this);
            TextView end = new TextView(this);
            TextView wait = new TextView(this);

            customer.setText(String.valueOf(i+1));
            come.setText(String.valueOf(arrayList.get(i).getCome()));
            period.setText(String.valueOf(arrayList.get(i).getPeriod()));
            start.setText(String.valueOf(arrayList.get(i).getStart()));
            end.setText(String.valueOf(arrayList.get(i).getEnd()));
            wait.setText(String.valueOf(arrayList.get(i).getWait()));

            row.addView(customer);
            row.addView(come);
            row.addView(period);
            row.addView(start);
            row.addView(end);
            row.addView(wait);

            mtableLayout.addView(row);

        }


    }
}


