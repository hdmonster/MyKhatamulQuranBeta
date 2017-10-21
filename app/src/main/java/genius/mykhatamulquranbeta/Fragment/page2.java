package genius.mykhatamulquranbeta.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import genius.mykhatamulquranbeta.R;



/**
 * Created by DZAKY_PC on 10/21/2017.
 */

public class page2 extends Fragment {
    ImageView pg2;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.koala2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        pg2 = (ImageView) getView().findViewById(R.id.img2);
        pg2.setImageResource(R.drawable.a2);
    }



}
