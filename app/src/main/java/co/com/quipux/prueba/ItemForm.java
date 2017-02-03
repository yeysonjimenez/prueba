package co.com.quipux.prueba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import co.com.quipux.prueba.base.BaseActivity;
import co.com.quipux.prueba.entity.ItemsProduct;
import java.util.Calendar;

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
    Intent intent = getIntent();

    context = this;
    injectView();
    if (intent != null) {
      String name = intent.getStringExtra("name");
      String description = intent.getStringExtra("description");
      String estado = intent.getStringExtra("estado");
      String origen = intent.getStringExtra("origen");
      String cant = intent.getStringExtra("cant");
      editItem(name, description, estado, origen, cant);
    }
  }

  private void editItem(String name, String description, String estado, String origen,
      String cant) {
    edtName.setText(name);
    edtDescripction.setText(description);
    edtOrigen.setText(origen);
    edtEstado.setText(estado);
    edtCant.setText(cant);
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
    switch (view.getId()) {
      case R.id.btnSave:
        saveItem();
        break;
      default:
        break;
    }
  }

  private void saveItem() {
    Calendar c = Calendar.getInstance();
    int seconds = c.get(Calendar.SECOND);
    int minut = c.get(Calendar.MINUTE);
    int Hour = c.get(Calendar.HOUR);
    name = edtName.getText().toString();
    description = edtDescripction.getText().toString();
    estado = edtEstado.getText().toString();
    origen = edtOrigen.getText().toString();
    cant = edtCant.getText().toString();
    if ((name!=null&&!name.equals(""))&&
        (description!=null&&!description.equals(""))&&
            (estado!=null&&!estado.equals(""))&&
                (origen!=null&&!origen.equals(""))){
      ItemsProduct item = new ItemsProduct();
      item.setName(name);
      item.setDescription(description);
      item.setEstado(estado);
      item.setOrigen(origen);
      item.setCantidad(cant);
      item.setFecha(Hour + ":" + minut + ":" + seconds);
      guardarItem(item);
      Intent intent = new Intent(context, ActivityProduct.class);
      startActivity(intent);
    }
   else {
      edtName.setError(getString(R.string.error_field_required));
      edtDescripction.setError(getString(R.string.error_field_required));
      edtEstado.setError(getString(R.string.error_field_required));
      edtOrigen.setError(getString(R.string.error_field_required));
      edtCant.setError(getString(R.string.error_field_required));

    }
    finish();

  }
}
