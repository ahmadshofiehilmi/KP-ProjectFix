package adam.notebook.example.com.kpproject6.model.product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {
    @SerializedName("id")
    @Expose
    private String idBarang;

    @SerializedName("product_name")
    @Expose
    private String product_name;

    @SerializedName("product_price")
    @Expose
    private String product_price;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private Images image;

    public Product(String id, String product_name, String product_price, String description, Images image) {
        this.idBarang = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.description = description;
        this.image = image;
    }

    protected Product(Parcel in) {
        idBarang = in.readString();
        product_name = in.readString();
        product_price = in.readString();
        description = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return idBarang;
    }

    public void setId(String id) {
        this.idBarang = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_prize(String product_prize) {
        this.product_price = product_prize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Images getImage() {
        return image;
    }

    public void setImage(Images images) {
        this.image = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBarang);
        dest.writeString(product_name);
        dest.writeString(product_price);
        dest.writeString(description);

    }
}
