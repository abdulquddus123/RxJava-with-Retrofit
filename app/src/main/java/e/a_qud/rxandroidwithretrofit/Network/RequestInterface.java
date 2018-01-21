package e.a_qud.rxandroidwithretrofit.Network;



import java.util.List;

import e.a_qud.rxandroidwithretrofit.Model.Info;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by a_qud on 1/21/2018.
 */

public interface RequestInterface {
    @GET("android/jsonarray/")
    Observable<List<Info>> register();
}
