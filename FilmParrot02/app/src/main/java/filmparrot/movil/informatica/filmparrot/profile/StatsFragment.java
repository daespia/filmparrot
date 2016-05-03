package filmparrot.movil.informatica.filmparrot.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Usuario;

public class StatsFragment extends Fragment {

    Usuario u;
    BarChart chart, chart2;

    public StatsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stats, container, false);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        u = Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null));

        ((TextView) v.findViewById(R.id.averageText)).setText("" + u.getMediaPuntuaciones());
        ((TextView) v.findViewById(R.id.votesText)).setText("" + u.getPuntuaciones().size());
        ((TextView) v.findViewById(R.id.reviewsText)).setText("" + u.getNumCriticas());
        ((TextView) v.findViewById(R.id.listsText)).setText("" + u.getListas().size());

        chart = (BarChart) v.findViewById(R.id.chart);
        chart2 = (BarChart) v.findViewById(R.id.chart2);

        crearGraficos();

        return v;
    }

    private void crearGraficos() {
        HashMap<String, Integer> porTipo = u.getVotacionesPorTipo();
        HashMap<String, Integer> porPais = u.getVotacionesPorPaises();

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();
        ArrayList<String> labels1 = new ArrayList<String>();
        ArrayList<String> labels2 = new ArrayList<String>();

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

        BarDataSet dataset1 = new BarDataSet(entries1, null);
        BarData data1 = new BarData(labels1, dataset1);
        data1.setDrawValues(false);

        BarDataSet dataset2 = new BarDataSet(entries2, null);
        BarData data2 = new BarData(labels2, dataset2);
        data2.setDrawValues(false);

        chart.setData(data1);
        chart2.setData(data2);
        chart.animateY(2000);
        chart2.animateY(2000);

        chart.getXAxis().setLabelsToSkip(0);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setTextSize(10);
        chart.setDescription("");
        chart.setScaleEnabled(false);

        chart2.getXAxis().setLabelsToSkip(0);
        chart2.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart2.getXAxis().setTextSize(10);
        chart2.setDescription("");
        chart2.setScaleEnabled(false);
    }
}
