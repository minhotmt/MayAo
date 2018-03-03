package com.example.minhnguyen.mayao.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.minhnguyen.mayao.R;
import com.example.minhnguyen.mayao.model.Server;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;

public class Detail extends AppCompatActivity implements OnChartGestureListener,
        OnChartValueSelectedListener {
    DatabaseReference myref;
    ArrayList<Server> servers = new ArrayList<>();
    LineChart chartCpu,chartRam;
    PieChart chartDisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        chartCpu = findViewById(R.id.chartCpu);
        chartRam = findViewById(R.id.chartRam);
        chartDisk = findViewById(R.id.chartDisk);
        piechart();

        final int id = (int) getIntent().getSerializableExtra("thongtinserver");

        Log.e("Id", "" +id);
        myref= FirebaseDatabase.getInstance().getReference().child("/server");
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.e("key", "" + dataSnapshot.getKey());

                // A new comment has been added, add it to the displayed list
                Server server = dataSnapshot.getValue(Server.class);
                Log.e("Cpu", "" +server.getCpu());
                servers.add(server);
                if (server.getId()==id){
                    Log.e("Cpu", "" +server.getCpu());
                    Log.e("Ram", "" +server.getRam());
                    Log.e("Disk", "" +server.getDisk());

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Server server = dataSnapshot.getValue(Server.class);
                Log.e("Cpu", "" +server.getCpu());
                servers.add(server);
                if (server.getId()==id){
                    Log.e("Cpu", "" +server.getCpu());
                    Log.e("Ram", "" +server.getRam());
                    Log.e("Disk", "" +server.getDisk());
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        };
        myref.addChildEventListener(childEventListener);

        chartRam.setOnChartGestureListener(this);
        chartCpu.setOnChartGestureListener(this);
        chartRam.setOnChartValueSelectedListener(this);
        chartCpu.setOnChartValueSelectedListener(this);
        chartRam.setDrawGridBackground(false);
        chartCpu.setDrawGridBackground(false);

        // add data
        setData();

        // get the legend (only possible after setting data)
        Legend lR = chartRam.getLegend();
        Legend lC = chartCpu.getLegend();

        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        lR.setForm(Legend.LegendForm.LINE);
        lC.setForm(Legend.LegendForm.LINE);

        // no description text
        chartCpu.setDescription("Thời gian");
        chartRam.setDescription("Thời gian");
        YAxis leftAxisC = chartCpu.getAxisLeft();
        YAxis leftAxisR = chartRam.getAxisLeft();
        leftAxisC.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxisR.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines

        leftAxisR.setAxisMaxValue(100f);
        leftAxisC.setAxisMaxValue(100f);
        leftAxisR.setAxisMinValue(0);
        leftAxisC.setAxisMinValue(0);
        //leftAxis.setYOffset(20f);
        leftAxisR.enableGridDashedLine(10f, 10f, 0f);
        leftAxisC.enableGridDashedLine(10f, 10f, 0f);
        leftAxisR.setDrawZeroLine(true);
        leftAxisC.setDrawZeroLine(true);

        // limit lines are drawn behind data (and not on top)
        leftAxisR.setDrawLimitLinesBehindData(true);
        leftAxisC.setDrawLimitLinesBehindData(true);

        chartRam.getAxisRight().setEnabled(false);
        chartCpu.getAxisRight().setEnabled(false);

        chartRam.animateX(2500, Easing.EasingOption.EaseInOutQuart);
        chartCpu.animateX(2500, Easing.EasingOption.EaseInOutQuart);

        //  dont forget to refresh the drawing
        chartRam.invalidate();
        chartCpu.invalidate();

    }

    private void piechart() {
        chartDisk.setUsePercentValues(true);

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));

        PieDataSet dataSet = new PieDataSet(yvalues, "");

        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Còn trống");
        xVals.add("Đã sử dùng");

        PieData data = new PieData(xVals, dataSet);
        // In Percentage term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        chartDisk.setData(data);
        chartDisk.setDescription("Disk");

        //chartDisk.setDrawHoleEnabled(true);
        chartDisk.setTransparentCircleRadius(50f);
        chartDisk.setHoleRadius(60f);

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.DKGRAY);
        chartDisk.setOnChartValueSelectedListener(this);

        chartDisk.animateXY(2000, 2000);
    }

    private ArrayList<String> setXAxisValues(){
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("10");
        xVals.add("20");
        xVals.add("30");
        xVals.add("40");
        xVals.add("50");
        return xVals;
    }

    private ArrayList<Entry> setYAxisValues(){
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        yVals.add(new Entry(60, 0));
        yVals.add(new Entry(48, 1));
        yVals.add(new Entry(70, 2));
        yVals.add(new Entry(80, 3));
        yVals.add(new Entry(65, 4));
        return yVals;
    }

    private void setData() {
        ArrayList<String> xVals = setXAxisValues();

        ArrayList<Entry> yVals = setYAxisValues();

        LineDataSet set1,set2;

        // create a dataset and give it a type
        set1 = new LineDataSet(yVals, "CPU");
        set2 = new LineDataSet(yVals, "Ram");

        set1.setFillAlpha(110);
        set1.setColor(Color.BLACK);
        set1.setCircleColor(Color.BLACK);
        set1.setLineWidth(1f);
        set1.setCircleRadius(3f);
        set1.setDrawCircleHole(false);
        set1.setValueTextSize(9f);
        set1.setDrawFilled(true);

        set2.setFillAlpha(110);
        set2.setColor(Color.BLACK);
        set2.setCircleColor(Color.BLACK);
        set2.setLineWidth(1f);
        set2.setCircleRadius(3f);
        set2.setDrawCircleHole(false);
        set2.setValueTextSize(9f);
        set2.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets1 = new ArrayList<ILineDataSet>();
        ArrayList<ILineDataSet> dataSets2 = new ArrayList<ILineDataSet>();
        dataSets1.add(set1); // add the datasets
        dataSets2.add(set2); // add the datasets

        // create a data object with the datasets
        LineData data1 = new LineData(xVals, dataSets1);
        LineData data2 = new LineData(xVals, dataSets2);
        // set data
        chartCpu.setData(data1);
        chartRam.setData(data2);

    }


    @Override
    public void onChartGestureStart(MotionEvent me,
                                    ChartTouchListener.ChartGesture
                                            lastPerformedGesture) {

        Log.i("Gesture", "START, x: " + me.getX() + ", y: " + me.getY());
    }

    @Override
    public void onChartGestureEnd(MotionEvent me,
                                  ChartTouchListener.ChartGesture
                                          lastPerformedGesture) {

        Log.i("Gesture", "END, lastGesture: " + lastPerformedGesture);

        // un-highlight values after the gesture is finished and no single-tap
        if(lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP)
            // or highlightTouch(null) for callback to onNothingSelected(...)
            chartRam.highlightValues(null);
            chartCpu.highlightValues(null);
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {
        Log.i("LongPress", "Chart longpressed.");
    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {
        Log.i("DoubleTap", "Chart double-tapped.");
    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {
        Log.i("SingleTap", "Chart single-tapped.");
    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2,
                             float velocityX, float velocityY) {
        Log.i("Fling", "Chart flinged. VeloX: "
                + velocityX + ", VeloY: " + velocityY);
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        Log.i("Scale / Zoom", "ScaleX: " + scaleX + ", ScaleY: " + scaleY);
    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {
        Log.i("Translate / Move", "dX: " + dX + ", dY: " + dY);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // we're going to simulate real time with thread that append data to the graph
        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (int i = 0; i < 100; i++) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            addEntry(7);

                        }
                    });

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }
                }
            }
        }).start();
    }

    // add random data to graph
    private void addEntry(int count) {
        LineData data1 = chartCpu.getData();
        LineData data2 = chartRam.getData();

        if (data1 != null) {
            ILineDataSet set = data1.getDataSetByIndex(0);

            data1.addXValue("");
            Random rd = new Random();
            data1.addEntry(new Entry(rd.nextInt(100), set.getEntryCount()), 0);

            // let the chart know it's data has changed
            chartRam.notifyDataSetChanged();
            // c the number of visible entries
            chartRam.setVisibleXRange(0, count);
            // move to the latest entry
            chartRam.moveViewToX(data1.getXValCount() - (count + 1));
        }
        if (data2 != null) {
            ILineDataSet set = data2.getDataSetByIndex(0);

            data2.addXValue("");
            Random rd = new Random();
            data2.addEntry(new Entry(rd.nextInt(100), set.getEntryCount()), 0);

            // let the chart know it's data has changed
            chartCpu.notifyDataSetChanged();
            // limit the number of visible entries
            chartCpu.setVisibleXRange(0, count);
            // move to the latest entry
            chartCpu.moveViewToX(data2.getXValCount() - (count + 1));
        }
    }

}
