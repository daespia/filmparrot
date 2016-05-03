package filmparrot.movil.informatica.filmparrot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Bso;


public class BsoViewFragment extends Fragment {


    public BsoViewFragment() {
    }

    public static BsoViewFragment newInstance(int e) {
        BsoViewFragment fragment = new BsoViewFragment();
        Bundle args = new Bundle();
        args.putInt("idBso",e);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bso_view, container, false);

        int idbso = getArguments().getInt("idBso");
        Bso e = (Bso) Utils.fachada.getElementoPorId(idbso);

        TextView compositor  = (TextView) view.findViewById(R.id.compositorText);
        compositor.setText(e.getCompositor());

        TextView year  = (TextView) view.findViewById(R.id.yearText);
        year.setText(""+e.getAnio());




        return view;
    }

}
