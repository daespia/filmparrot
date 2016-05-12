package filmparrot.movil.informatica.filmparrot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;

import filmparrot.movil.informatica.filmparrot.auxiliar.ReleasesAdapter;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;

public class ReleasesFragment extends Fragment {

    public ReleasesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_releases, container, false);

        GridView estrenosGrid = (GridView) v.findViewById(R.id.releasesGrid);
        ReleasesAdapter releasesAdapter = new ReleasesAdapter(getContext(), Utils.fachada.getEstrenos());
        estrenosGrid.setAdapter(releasesAdapter);

        estrenosGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ElementViewActivity.class);
                intent.putExtra("id", Utils.fachada.getEstrenos().get(position).getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.left_toright, R.anim.right_toleft);
            }
        });

        return v;
    }


}
