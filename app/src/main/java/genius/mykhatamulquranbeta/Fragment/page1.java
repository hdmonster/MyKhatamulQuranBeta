package genius.mykhatamulquranbeta.Fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import genius.mykhatamulquranbeta.R;

/**
 * Created by DZAKY_PC on 10/21/2017.
 */

public class page1 extends Fragment {
    ImageView pg1;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.koala1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        pg1 = (ImageView) getView().findViewById(R.id.img1);
        pg1.setImageResource(R.drawable.a1);
    }
}
