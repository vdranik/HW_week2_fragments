package ua.com.vdranik.hw_week2_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxR;
    private CheckBox checkBoxG;
    private CheckBox checkBoxB;

    private Fragment fragment_r;
    private Fragment fragment_g;
    private Fragment fragment_b;

    private static final String FRAGMENT_R_TAG = "fragment_r";
    private static final String FRAGMENT_G_TAG = "fragment_g";
    private static final String FRAGMENT_B_TAG = "fragment_b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxR = (CheckBox) findViewById(R.id.checkBoxR);
        checkBoxG = (CheckBox) findViewById(R.id.checkBoxG);
        checkBoxB = (CheckBox) findViewById(R.id.checkBoxB);

        fragment_r = new Fragment_R();
        fragment_g = new Fragment_G();
        fragment_b = new Fragment_B();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (checked) {
            switch (view.getId()) {
                case R.id.checkBoxR:
                    addFragment(fragment_r, FRAGMENT_R_TAG);
                    break;
                case R.id.checkBoxG:
                    addFragment(fragment_g, FRAGMENT_G_TAG);
                    break;
                case R.id.checkBoxB:
                    addFragment(fragment_b, FRAGMENT_B_TAG);
                    break;
                default:
                    break;
            }
        } else getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String fragmentName = null;
        Fragment topFragment = getTopFragment();
        if(topFragment!=null) fragmentName = getTopFragment().getTag();
        if(fragmentName == null) return;

        switch (fragmentName) {
            case FRAGMENT_R_TAG:
                checkBoxR.setChecked(false);
                break;
            case FRAGMENT_G_TAG:
                checkBoxG.setChecked(false);
                break;
            case FRAGMENT_B_TAG:
                checkBoxB.setChecked(false);
                break;
            default:
                break;
        }
    }

    private void addFragment(Fragment fragment, String fragmentTag){
        Fragment topFragment = getTopFragment();
        int replacedAreaId;

        if(topFragment == null){
            replacedAreaId = R.id.fragmentContainer;
        } else replacedAreaId = topFragment.getId();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(replacedAreaId, fragment, fragmentTag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    private Fragment getTopFragment() {
        Fragment top;
        List<Fragment> fragentList = getSupportFragmentManager().getFragments();

        if(fragentList != null) {
            for (int i = fragentList.size() - 1; i >= 0; i--) {
                top = fragentList.get(i);
                if (top != null) {
                    return top;
                }
            }
        }
        return null;
    }
}
