package adam.notebook.example.com.kpproject6.service.detail;

import android.util.Log;

import adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.ConnectionUrl;
import adam.notebook.example.com.kpproject6.GeneralUtility.LogUtility;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.model.cart.CartResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailPresenter {

    private static final String TAG ="ProductDetailPresenter";

    public static void addToCart(String idBarang, int qty ,final ServiceCallback callback){
        MyApplication.getApiService()
                .addToCart(ConnectionUrl.ADD_TO_CART, idBarang, qty)
                .enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        LogUtility.logging(TAG, LogUtility.debugLog,"addToCart","onResponse",Utils.toJson(response.body()));
                        if (response.body() !=null){
                            if(response.body().getStatus()){
                                callback.addToCartSuccess();
                            } else{
                                callback.showMessage("Maaf , terjadi kesalahan pada server");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {
                        LogUtility.logging(TAG, LogUtility.errorLog, "addToCart", "onFailure", t.getMessage());
                        t.printStackTrace();
                    }
                });
    }
}
