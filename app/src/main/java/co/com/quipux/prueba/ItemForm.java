package co.com.quipux.prueba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import co.com.quipux.prueba.base.BaseActivity;
import co.com.quipux.prueba.entity.ItemsProduct;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by yeysonjimenez on 2/1/17.
 */
public class ItemForm extends BaseActivity implements View.OnClickListener {
  EditText edtName;
  EditText edtDescripction;
  EditText edtEstado;
  EditText edtOrigen;
  EditText edtCant;
  String name;
  String description;
  String estado;
  String origen;
  String cant;
  Button btnSave;
  private Context context;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.formproduct);
    context = this;
    injectView();

  }

  private void injectView() {

    edtName = (EditText) findViewById(R.id.edtName);
    edtDescripction = (EditText) findViewById(R.id.edtDescription);
    edtEstado = (EditText) findViewById(R.id.edtEstado);
    edtOrigen = (EditText) findViewById(R.id.edtOrigen);
    edtCant = (EditText) findViewById(R.id.edtCantidad);
    btnSave = (Button) findViewById(R.id.btnSave);
    btnSave.setOnClickListener(this);

  }

  @Override public void onClick(View view) {
    switch (view.getId())
    {
      case R.id.btnSave:
        saveItem();
        break;
    }
  }

  private void saveItem() {

    name = edtName.getText().toString();
    description = edtDescripction.getText().toString();
    estado = edtEstado.getText().toString();
    origen = edtOrigen.getText().toString();
    cant = edtCant.getText().toString();
    Realm realm = Realm.getDefaultInstance();
    realm.executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        ItemsProduct  item = new ItemsProduct();
        item.setName(name);
        item.setDescription(description);
        item.setEstado(estado);
        item.setOrigen(origen);
        item.setCantidad(cant);
        Log.e("guardar",item.toString());
        realm.copyToRealmOrUpdate(item);
        Intent intent = new Intent(context, Product.class);
        startActivity(intent);
      }
    });
    realm.close();



  }
}
