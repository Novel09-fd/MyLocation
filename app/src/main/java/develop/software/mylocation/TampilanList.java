package develop.software.mylocation;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import develop.software.mylocation.adapter.AdapterListSimple;
import develop.software.mylocation.model.GpsTracking;
import develop.software.mylocation.model.ModelLocation;
import develop.software.mylocation.service.APIClient;
import develop.software.mylocation.service.APIInterfacesRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TampilanList extends AppCompatActivity {


    RecyclerView rv;
    TextView isiCoba, isiCoba2, isiCoba3, isiCoba4
            ,isiCoba5,isiCoba6,isi_lat1,isi_long1;
    int arr;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_list);

        rv = findViewById(R.id.rview);
        isiCoba = findViewById(R.id.isiCoba);
        isiCoba2 = findViewById(R.id.isiCoba2);
        isiCoba3 = findViewById(R.id.isiCoba3);
        isiCoba4 = findViewById(R.id.isiCoba4);
        isiCoba5 = findViewById(R.id.isiCoba5);
        isiCoba6 = findViewById(R.id.isiCoba6);

        isi_lat1 = findViewById(R.id.isi_lat1);

        isi_long1 = findViewById(R.id.isi_long1);


        callGetLocationn();

    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void callGetLocationn(){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(TampilanList.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<ModelLocation> call3 = apiInterface.getDataLocation();
        call3.enqueue(new Callback<ModelLocation>() {
            @Override
            public void onResponse(Call<ModelLocation> call, Response<ModelLocation> response) {
                progressDialog.dismiss();
                ModelLocation data = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();

                List<ModelGpsData> gpsData= new ArrayList<ModelGpsData>();
                if (data !=null) {

                   // arr = data.getData().getGpsTracking().size();
/*


                    AdapterListSimple adapter = new AdapterListSimple(TampilanList.this,data.getData().getGpsTracking());

                    rv.setLayoutManager(new LinearLayoutManager(TampilanList.this));
                    rv.setItemAnimator(new DefaultItemAnimator());
                    rv.setAdapter(adapter);


 */


                List<GpsTracking> datax = data.getData().getGpsTracking() ;

                    String usernameDummy="";
                    String latitude = "";
                    String longitude ="";
                    for(int x = 0 ; x < datax.size();x++){

                        if (!datax.get(x).getUsername().equalsIgnoreCase(usernameDummy)){

                            if(x >0) {
                                ModelGpsData mgd = new ModelGpsData();

                                mgd.setNama(usernameDummy);
                                mgd.setLatitude(latitude);
                                mgd.setLongitude(longitude);

                                gpsData.add(mgd);

                            }
                            usernameDummy = datax.get(x).getUsername();




                        }else{
                            latitude = datax.get(x).getLatitude();
                            longitude = datax.get(x).getLongitude();
                        }


                    }
                    ModelGpsData mgd = new ModelGpsData();

                    mgd.setNama(usernameDummy);
                    mgd.setLatitude(latitude);
                    mgd.setLongitude(longitude);

                    gpsData.add(mgd);
                    Log.d("test","test");


                        isiCoba.setText(data.getData().getGpsTracking().get(x).getUsername());
                        isiCoba2.setText(data.getData().getGpsTracking().get(x).getTime());
                        isi_lat1.setText(data.getData().getGpsTracking().get(x).getLatitude());
                        isi_long1.setText(data.getData().getGpsTracking().get(x).getLongitude());
                        isiCoba3.setText(data.getData().getGpsTracking().get(3).getUsername());
                        isiCoba4.setText(data.getData().getGpsTracking().get(3).getTime());
                        isiCoba5.setText(data.getData().getGpsTracking().get(1).getUsername());
                        isiCoba6.setText(data.getData().getGpsTracking().get(1).getTime());



                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(TampilanList.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(TampilanList.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ModelLocation> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }


    class ModelGpsData{

        private String nama;

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        private String latitude;
        private String longitude;

    }





}
