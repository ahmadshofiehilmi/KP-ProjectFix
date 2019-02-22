package adam.notebook.example.com.kpproject6.GeneralUtility.ConnectionUtility.apiservice;

import adam.notebook.example.com.kpproject6.model.BaseResponse;
import adam.notebook.example.com.kpproject6.model.User;
import adam.notebook.example.com.kpproject6.model.cart.CartResponse;
import adam.notebook.example.com.kpproject6.model.product.ProductResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiService {


    @POST
    @FormUrlEncoded
    Call<BaseResponse<User>> login(@Url String url,
                                   @Field("email") String email,
                                   @Field("password") String password);

    @POST
    @FormUrlEncoded
    Call<BaseResponse<Object>> register(@Url String url,
                                        @Field("email") String email,
                                        @Field("password") String password,
                                        @Field("fullname") String fullname,
                                        @Field("address") String address,
                                        @Field("city") String city,
                                        @Field("postal_code") String postal_code);

    @POST
    @FormUrlEncoded
    Call<CartResponse>addToCart(@Url String url ,
                                @Field("product_id") String idBarang,
                                @Field("qty") int qty);

    @GET
    Call<BaseResponse> getProfile(@Url String url);


    @POST
    @FormUrlEncoded
    Call<BaseResponse<Object>> updateProfile(@Url String url,
                                             @Field("email") String email,
                                             @Field("fullname") String fullname,
                                             @Field("address") String address,
                                             @Field("city") String city,
                                             @Field("postal_code") String postal_code,
                                             @Field("profile_image") String profile_image);

}
