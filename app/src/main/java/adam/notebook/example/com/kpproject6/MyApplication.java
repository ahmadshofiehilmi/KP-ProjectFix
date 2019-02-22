package adam.notebook.example.com.kpproject6;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.onesignal.OneSignal;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.HttpInterceptor;
import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice.ApiService;
import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice.OpenApiService;
import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    private static MyApplication mInstance;
    public SharedPreferences preferences;
    public String prefName = "app";
    private static OpenApiService openApiService;
    private static ApiService apiService;
    //    private static RegionApiService regionApiService;
    private Gson gson;

    public static PreferenceUtils pref;
    public MyApplication() {
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        OneSignal.startInit(this)
//                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
//                .init();
        mInstance = this;
//        TypefaceCompatUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Montserrat-Regular_0.ttf");
        pref = new PreferenceUtils(getApplicationContext());
//
        gson = new GsonBuilder()
                .setLenient()
                .create();
//        openApiRetrofit();
        apiRetrofit();
//        regionApiRetrofit();
    }

    private void apiRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getOkHttpClient())
                .build();
        apiService = retrofit.create(ApiService.class);
    }
//
//    private void openApiRetrofit() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BuildConfig.HostApi)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(getDefaultOkHttpClient())
//                .build();
//        openApiService = retrofit.create(OpenApiService.class);
//    }

    /**digunakan uk akses konfig api yang make default token*/
    public static OpenApiService getOpenApiService() {
        return openApiService;
    }


    /**digunakan uk akses konfig api yang make token user*/
    public static ApiService getApiService() {
        return apiService;
    }

    private OkHttpClient getDefaultOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .addInterceptor(new HttpInterceptor(this))
                .build();
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
    public void saveTheme1(boolean Theme1) {
        preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Theme1", Theme1);
        editor.apply();
    }

    public boolean getTheme1() {
        preferences = this.getSharedPreferences(prefName, 0);
        if (preferences != null) {
            boolean image = preferences.getBoolean("Theme1", true);
            return image;
        }
        return false;
    }

    public void saveTheme2(boolean Theme2) {
        preferences = this.getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("Theme2", Theme2);
        editor.commit();
    }

    public boolean getTheme2() {
        preferences = this.getSharedPreferences(prefName, 0);
        if (preferences != null) {
            boolean image = preferences.getBoolean("Theme2", false);
            return image;
        }
        return false;
    }
}