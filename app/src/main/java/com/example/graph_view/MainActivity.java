package com.example.graph_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.CDATASection;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Runnable mTimer1, mTimer2;
    private final Handler mHandler = new Handler();

    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 5d;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GraphView graphView1 = findViewById(R.id.gv_graph_view_1);
        mSeries1 = new LineGraphSeries<>();
        mSeries1.setColor(Color.rgb(227, 53, 18));
        graphView1.addSeries(mSeries1);

        mSeries2 = new LineGraphSeries<>();
        mSeries2.setColor(Color.rgb(28, 25, 25));
        graphView1.addSeries(mSeries2);

        graphView1.getViewport().setXAxisBoundsManual(true);
        graphView1.getViewport().setMinX(0);
        graphView1.getViewport().setMaxX(100);

        graphView1.getViewport().setScalable(true);
        graphView1.getViewport().setScalableY(true);
        graphView1.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX) {
                    return super.formatLabel(value, true);
                }
                else {
                    return "" + (value/100000);
                }
            }
        });

        graphView1.setTitle("raw graph");
        graphView1.getGridLabelRenderer().setHorizontalAxisTitle("times");
        graphView1.getGridLabelRenderer().setVerticalAxisTitle("values");

        Button btnButton = findViewById(R.id.btn_button);

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSeries2.appendData(new DataPoint(i, getRandom()), true, 100);
                mSeries1.appendData(new DataPoint(i, getRandom()), true, 100);
                i++;
                /*
                mTimer1 = new Runnable() {
                    @Override
                    public void run() {
                        mSeries1.resetData(generateData());
                        mHandler.postDelayed(this, 300);
                    }
                };

                mTimer2 = new Runnable() {
                    int i = 1;
                    int j = 0;
                    @Override
                    public void run() {
                        graph2LastXValue += 1d;
                        if(j % 2 == 0) {
                            mSeries2.appendData(new DataPoint(i, getRandom()), true, 100);
                            mSeries1.appendData(new DataPoint(i, getRandom()), true, 100);
                        }
                        else {
                            mSeries2.appendData(new DataPoint(i, getRandom()), true, 100);
                            mSeries1.appendData(new DataPoint(i, getRandom()), true, 100);
                        }
                        i = i + 1;
                        j++;
                        mHandler.postDelayed(this, 200);
                    }
                };
                mHandler.postDelayed(mTimer2, 1000);


                 */
            }
        });

        //String arrayJson = "{\"birthday\":\"5-5-1993\",\"array\":[{\"test\":\"abc\"}]}";
        //String json = "{\"ResponseCode\":\"1\",\"ResponseMsg\":[{\"Code\":\"CA2305181\",\"Message\":\"Processed successfully\"}]}";
        //String json = "{\"device_uuid\":0x1F,\"payload\":[{\"BPM\":1512687,\"spo2\":1512666}]}";
        //String json = "{\"device_uuid\": \"0xFF\", \"payload\": [{\"Red\": 1512687, \"IR\": 1512667, \"BPM\": 15, \"spO2\": 98, \"originTimestamp\": \"20-02-2020\", \"cpuTimestamp\": 15126688}]}\r\n";
        //try {

        //    JSONObject obj = new JSONObject(json);
        //    Log.d("AndroidLE", "" + obj.getJSONArray("payload").getJSONObject(0).getDouble("Red"));
        //    //Log.d("AndroidLE", "" + obj);
        //    Log.d("AndroidLE", obj.toString());

        //} catch (Throwable t) {
        //    Log.e("AndroidLE", "Could not parse malformed JSON: \"" + json + "\"");
        //}


        /*
        GraphView graphView1 = findViewById(R.id.gv_graph_view_1);
        graphView1.setTitle("Bluetooth data");
        graphView1.getGridLabelRenderer().setVerticalAxisTitle("values");
        graphView1.getGridLabelRenderer().setHorizontalAxisTitle("times");

        mSeries1 = new LineGraphSeries<>(generateData());
        mSeries1.setColor(Color.rgb(227, 53, 18));
        graphView1.addSeries(mSeries1);

        GraphView graphView2 = findViewById(R.id.gv_graph_view_2);
        graphView2.setTitle("Bluetooth data");
        graphView2.getGridLabelRenderer().setVerticalAxisTitle("values");
        graphView2.getGridLabelRenderer().setHorizontalAxisTitle("times");

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graphView2);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"","values"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"", "times"});
        graphView2.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        mSeries2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1,5),
                new DataPoint(2, 3)
        });
        mSeries2.setColor(Color.rgb(227, 53, 18));
        graphView2.addSeries(mSeries2);

        graphView2.getViewport().setXAxisBoundsManual(true);
        graphView2.getViewport().setMinX(0);
        graphView2.getViewport().setMaxX(2000);
        //graphView2.getViewport().setYAxisBoundsManual(true);
        //graphView2.getViewport().setMinY(100000);
        //graphView2.getViewport().setMaxY(300000);



        Button btnButton = findViewById(R.id.btn_button);
        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTimer1 = new Runnable() {
                    @Override
                    public void run() {
                        mSeries1.resetData(generateData());
                        mHandler.postDelayed(this, 300);
                    }
                };

                mTimer2 = new Runnable() {
                    int i = 3;
                    int j = 0;
                    @Override
                    public void run() {
                        graph2LastXValue += 1d;
                        if(j % 2 == 0) {
                            mSeries2.appendData(new DataPoint(i, getRandom()), true, 100);
                        }
                        else {
                            mSeries2.appendData(new DataPoint(i, getRandom()), true, 100);
                        }
                        i = i + 40;
                        j++;
                        mHandler.postDelayed(this, 200);
                    }
                };
                mHandler.postDelayed(mTimer2, 1000);

            }
        });
        */

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelable("series", (Parcelable) mSeries2);
    }

    @Override
    protected void onPause() {
        mHandler.removeCallbacks(mTimer1);
        mHandler.removeCallbacks(mTimer2);
        super.onPause();
    }

    private DataPoint[] generateData() {
        int count = 30;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double f = mRand.nextDouble()*0.15+0.3;
            double y = Math.sin(i*f+2) + mRand.nextDouble()*0.3;
            DataPoint v = new DataPoint(i, y);
            values[i] = v;
        }
        return values;
    }
    int mLastRandom = 200000;
    int mLastRandomM = 150000;
    Random mRand = new Random();
    private int getRandom() {
        int temp = mRand.nextInt(20);
        int result = 0;
        if(temp % 2 == 0) {
            result = mLastRandom + temp;
        }else{
            result = mLastRandomM + temp;
        }
        return result;
    }
}
