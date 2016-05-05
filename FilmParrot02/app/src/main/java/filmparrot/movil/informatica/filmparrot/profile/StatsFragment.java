package filmparrot.movil.informatica.filmparrot.profile;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Usuario;

public class StatsFragment extends Fragment {

    Usuario usuario;
    BarChart bar_chart1, bar_chart2;
    LineChart line_chart;
    private int tag;

    public StatsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        usuario = Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null));

        ((TextView) v.findViewById(R.id.averageText)).setText("" + usuario.getMediaPuntuaciones());
        ((TextView) v.findViewById(R.id.votesText)).setText("" + usuario.getPuntuaciones().size());
        ((TextView) v.findViewById(R.id.reviewsText)).setText("" + usuario.getNumCriticas());
        ((TextView) v.findViewById(R.id.listsText)).setText("" + usuario.getListas().size());

        line_chart = (LineChart) v.findViewById(R.id.line_chart);
        bar_chart1 = (BarChart) v.findViewById(R.id.bar_chart1);
        bar_chart2 = (BarChart) v.findViewById(R.id.bar_chart2);

        crearGraficos();

        return v;
    }

    private void crearGraficos() {

        List<Double> historiaMedias = usuario.getHistoriaMedias();
        HashMap<String, Integer> porTipo = usuario.getVotacionesPorTipo();
        HashMap<String, Integer> porPais = usuario.getVotacionesPorPaises();

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        ArrayList<Entry> entries3 = new ArrayList<>();

        ArrayList<String> labels1 = new ArrayList<String>();
        ArrayList<String> labels2 = new ArrayList<String>();
        ArrayList<String> labels3 = new ArrayList<String>();


        int i = 0;

        for (Map.Entry<String, Integer> entry : porTipo.entrySet()) {
            entries1.add(new BarEntry(entry.getValue(), i));
            labels1.add(entry.getKey());
            i++;
        }

        i = 0;

        for (Map.Entry<String, Integer> entry : porPais.entrySet()) {
            entries2.add(new BarEntry(entry.getValue(), i));
            labels2.add(entry.getKey());
            i++;
        }


        i = 0;

        for (Double d: historiaMedias) {
            entries3.add(new Entry(d.floatValue(), i));
            labels3.add(""+i);
            i++;
        }

        BarDataSet dataset1 = new BarDataSet(entries1, "Votos por tipo de elemento");
        dataset1.setHighLightColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        BarData data1 = new BarData(labels1, dataset1);
        data1.setDrawValues(false);

        BarDataSet dataset2 = new BarDataSet(entries2, "Votos por país de procedencia");
        dataset2.setHighLightColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        BarData data2 = new BarData(labels2, dataset2);
        data2.setDrawValues(false);

        LineDataSet dataset3 = new LineDataSet(entries3, "Evolución de la media");
        dataset3.setDrawCubic(true);
        dataset3.setDrawFilled(true);
        dataset3.setDrawCircles(false);
        dataset3.setDrawValues(false);
        LineData data3 = new LineData(labels3, dataset3);

        bar_chart1.setData(data1);
        bar_chart1.animateY(2000, Easing.EasingOption.EaseOutBack);
        bar_chart1.getXAxis().setLabelsToSkip(0);
        bar_chart1.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        bar_chart1.getXAxis().setTextSize(12);
        bar_chart1.setDescription("");
        bar_chart1.setScaleEnabled(false);

        bar_chart2.setData(data2);
        bar_chart2.animateY(2000, Easing.EasingOption.EaseOutBack);
        bar_chart2.getXAxis().setLabelsToSkip(0);
        bar_chart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        bar_chart2.getXAxis().setLabelRotationAngle(345);
        bar_chart2.getXAxis().setTextSize(12);
        bar_chart2.setDescription("");
        bar_chart2.setScaleEnabled(false);

        line_chart.setData(data3);
        line_chart.animateY(2000, Easing.EasingOption.EaseOutBack);
        line_chart.getXAxis().setDrawLabels(false);
        line_chart.getXAxis().setDrawGridLines(false);
        line_chart.setDescription("");
        line_chart.setScaleEnabled(false);

        line_chart.setTag(1);
        bar_chart1.setTag(2);
        bar_chart2.setTag(3);
        registerForContextMenu(line_chart);
        registerForContextMenu(bar_chart1);
        registerForContextMenu(bar_chart2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Guardar en galería");
        tag = (int) v.getTag();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(!Utils.hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, getActivity())){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        } else saveToGallery();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch (permsRequestCode){
            case 200:
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if(writeStorage) saveToGallery();
        }
    }

    private void saveToGallery()
    {
        long time = Calendar.getInstance().getTimeInMillis();

        if (tag == 2) bar_chart1.saveToGallery("TypeBars" + time, 100);
        else if (tag == 3) bar_chart2.saveToGallery("CountryBars" + time, 100);
        else if (tag == 1) line_chart.saveToGallery("AverageLine" + time, 100);
        Toast.makeText(getContext(), "La imagen se ha guardado en galería", Toast.LENGTH_SHORT).show();
    }
}
