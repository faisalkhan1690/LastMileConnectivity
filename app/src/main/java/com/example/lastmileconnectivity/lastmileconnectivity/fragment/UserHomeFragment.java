package com.example.lastmileconnectivity.lastmileconnectivity.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.lastmileconnectivity.lastmileconnectivity.R;
import com.example.lastmileconnectivity.lastmileconnectivity.util.CustomUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kelltontech.ui.fragment.BaseFragment;
import com.kelltontech.utils.StringUtils;

import java.lang.reflect.Array;

public class UserHomeFragment extends BaseFragment {

    private MapView mMapView;
    private GoogleMap googleMap;
    private String mSelectedPaymentOption;
    private String[] mSelectedPaymentOptionArray;

    private IUserHomeFragmentCallBack iUserHomeFragmentCallBack;
    private EditText mEtSource;
    private EditText mEtDestination;
    private View mRootView;

    public UserHomeFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iUserHomeFragmentCallBack=(IUserHomeFragmentCallBack)context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        iUserHomeFragmentCallBack=(IUserHomeFragmentCallBack)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_home, container, false);

        mRootView=rootView.findViewById(R.id.root_view);
        mEtSource=(EditText)rootView.findViewById(R.id.et_source);
        mEtDestination=(EditText)rootView.findViewById(R.id.et_destination);
        mSelectedPaymentOptionArray=getResources().getStringArray(R.array.payment_option);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);
                View myLocationButton = mMapView.findViewWithTag("GoogleMapMyLocationButton");
                RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) myLocationButton.getLayoutParams();
                rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);

                LatLng sydney = new LatLng(28.490982, 77.076140);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("E Rickshaw").snippet("Available near you "));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(13).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        Button btnBook = (Button) rootView.findViewById(R.id.btn_book);
        Spinner payment_option = (Spinner) rootView.findViewById(R.id.payment_option);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String source=mEtSource.getText().toString();
                String destination=mEtDestination.getText().toString();
                if(!StringUtils.isNullOrEmpty(source) && !StringUtils.isNullOrEmpty(destination)){
                    iUserHomeFragmentCallBack.sendData(source,destination,mSelectedPaymentOption);
                }else{
                    CustomUtil.showSnackBar(mRootView,"Please enter source ad destination");
                }

            }
        });

        payment_option.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedPaymentOption=mSelectedPaymentOptionArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void getData(int actionID) {

    }

    @Override
    public void updateUi(boolean status, int actionID, Object serviceResponse) {

    }

    public interface IUserHomeFragmentCallBack{
        void sendData(String source,String destination,String paymentOption);
    }

}