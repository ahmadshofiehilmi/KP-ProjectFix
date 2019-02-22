package adam.notebook.example.com.kpproject6.module.list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.ArrayList;
import java.util.List;

import adam.notebook.example.com.kpproject6.CheckedLogin;
import adam.notebook.example.com.kpproject6.GeneralUtility.Const;
import adam.notebook.example.com.kpproject6.GeneralUtility.PreferenceUtils.PreferenceUtils;
import adam.notebook.example.com.kpproject6.GeneralUtility.Utils;
import adam.notebook.example.com.kpproject6.MyApplication;
import adam.notebook.example.com.kpproject6.R;
import adam.notebook.example.com.kpproject6.model.product.Product;
import adam.notebook.example.com.kpproject6.module.cart.CartActivity;
import adam.notebook.example.com.kpproject6.module.login.LoginActivity;
import adam.notebook.example.com.kpproject6.service.detail.ProductDetailPresenter;
import adam.notebook.example.com.kpproject6.service.detail.ServiceCallback;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static adam.notebook.example.com.kpproject6.MyApplication.pref;

public class DetailActivity extends AppCompatActivity implements ServiceCallback{

    @BindView(R.id.layout_detail_container)
    CoordinatorLayout container;

    @BindView(R.id.title_product)
    TextView product_name;

    @BindView(R.id.desc_product)
    TextView product_desc;

    @BindView(R.id.img_produk)
    KenBurnsView image_product;

    @BindView(R.id.edt_jumlah)
    EditText edt_jumlah;

//    private List<Product> product ;
    private Product products;
    private Menu menu;
    private PreferenceUtils preferenceUtils = MyApplication.pref;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar() != null)
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        products =  getIntent().getParcelableExtra(Const.PRODUCT);

        String name = getIntent().getExtras().getString("product_name");
        String desc = getIntent().getExtras().getString("product_desc");
        String pict = getIntent().getExtras().getString("product_image");

        product_name.setText(name);
        product_desc.setText("Rp "+ Utils.toFormatCurrency(desc));

        Glide.with(this)
                .load(pict)
                .apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background))
                .into(image_product);
    }

    @OnClick(R.id.btn_add_to_cart)
    public void addToCart (View view){
        if (! CheckedLogin.hasLogin(DetailActivity.this))return;
        String jumlah = edt_jumlah.getText().toString();
        jumlah = (jumlah.isEmpty()) ? "0" : jumlah;
        int jml = Integer.parseInt(jumlah);
        if ( jml == 0) {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            return;
        }

        System.out.println("id barang : " + products.getId() + ", jml : " + jml);

        ProductDetailPresenter.addToCart(products.getId(), jml, this);
    }

    @OnClick(R.id.btn_increase)
    public void onIncrease(View view) {
        String jumlah = edt_jumlah.getText().toString();
        jumlah = (jumlah.isEmpty()) ? "0" : jumlah;
        int jml = Integer.parseInt(jumlah) + 1;
        edt_jumlah.setText(String.valueOf(jml));
    }

    @OnClick(R.id.btn_decrease)
    public void onDecrease(View view) {
        String jumlah = edt_jumlah.getText().toString();
        jumlah = (jumlah.isEmpty()) ? "0" : jumlah;
        int jml = Integer.parseInt(jumlah);
        jml = (jml == 1) ? 1 : (jml - 1);
        edt_jumlah.setText(String.valueOf(jml));
    }

    @Override
    public void addToCartSuccess() {
        Snackbar.make(container, "Ditambahkan ke keranjang", Snackbar.LENGTH_SHORT)
                .setAction("Lihat", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(DetailActivity.this, CartActivity.class));
                    }
                })
                .show();

    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}

