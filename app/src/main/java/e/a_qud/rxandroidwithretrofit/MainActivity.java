package e.a_qud.rxandroidwithretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import e.a_qud.rxandroidwithretrofit.Adapter.DataAdapter;
import e.a_qud.rxandroidwithretrofit.Model.Info;
import e.a_qud.rxandroidwithretrofit.Network.RequestInterface;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  public static final String BASE_URL="https://api.learn2crack.com/";
  private RecyclerView mRecyleView;
  private CompositeDisposable mCompositeDisposal;
  private DataAdapter mAdapter;
  private ArrayList<Info> mInfoArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompositeDisposal=new CompositeDisposable();

        mRecyleView=(RecyclerView)findViewById(R.id.recycleView);
        mRecyleView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        mRecyleView.setLayoutManager(layoutManager);
        ServerData();

    }

    private void ServerData(){

        RequestInterface requestInterface= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);


        mCompositeDisposal.add(requestInterface.register()


                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));



    }

    private void handleResponse(List<Info> infoList){

  mInfoArrayList=new ArrayList<>(infoList);
  mAdapter=new DataAdapter(mInfoArrayList);
  mRecyleView.setAdapter(mAdapter);
    }
    private void handleError(Throwable error){
        Toast.makeText(this, ""+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposal.clear();
    }
}
