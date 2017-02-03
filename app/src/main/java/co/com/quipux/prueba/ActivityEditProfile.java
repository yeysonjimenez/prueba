package co.com.quipux.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.internal.Utils;
import co.com.quipux.prueba.base.BaseActivity;
import co.com.quipux.prueba.entity.User;
import co.com.quipux.prueba.utils.UserRoll;
import fr.ganfra.materialspinner.MaterialSpinner;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerInputEditText;
import io.realm.Realm;

import static co.com.quipux.prueba.R.id.edtEstado;
import static co.com.quipux.prueba.R.id.edtName;
import static co.com.quipux.prueba.R.id.email;
import static co.com.quipux.prueba.R.id.fullname;
import static co.com.quipux.prueba.R.id.password;

/**
 * Created by yeysonjimenez on 2/2/17.
 */
public class ActivityEditProfile extends BaseActivity {

  @BindView(fullname) EditText edtfullname;
  @BindView(R.id.fullnameWrapper) TextInputLayout fullnameWrapper;
  @BindView(email) EditText edtEmail;
  @BindView(R.id.emailWrapper) TextInputLayout emailWrapper;
  @BindView(R.id.fecha_nacimiento) DatePickerInputEditText datafechaNacimiento;
  @BindView(R.id.fechaWrapper) TextInputLayout fechaWrapper;
  @BindView(R.id.documento) EditText edtDocumento;
  @BindView(R.id.documentoWrapper) TextInputLayout documentoWrapper;
  @BindView(password) EditText edtPassword;
  @BindView(R.id.spinner) MaterialSpinner spinner;


  String[] ITEMS = { UserRoll.ESTADO_ACTIVO, UserRoll.ESTADO_INACTIVO };

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_user);
    ButterKnife.bind(this);
    Intent intent = getIntent();
    if (intent != null) {
      String name = intent.getStringExtra("name");
      String correo = intent.getStringExtra("correo");
      String fecha = intent.getStringExtra("fecha");
      String estado = intent.getStringExtra("estado");
      String contrasena = intent.getStringExtra("contrasena");
      editItem(name, correo, fecha, estado, contrasena);
    }
    datafechaNacimiento.setManager(getSupportFragmentManager());
    //edtPassword.setText();
    ArrayAdapter<String> adapter =
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setSelection(0);


  }

  private void editItem(String name, String correo, String fecha, String estado,
      String contrasena) {
    edtfullname.setText(name);
    edtEmail.setText(correo);
    datafechaNacimiento.setText(fecha);
    edtPassword.setText(contrasena);
  }

  @OnClick(R.id.btn) public void onClick() {
    Log.e("ON click","save");
    Realm realm = Realm.getDefaultInstance();
    realm.executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        User usuario = new User();

        usuario.setName(UserRoll.ROL_USURIO);
        usuario.setUser(edtfullname.getText().toString());
        usuario.setEmail(edtEmail.getText().toString());
        usuario.setDocument(Integer.parseInt(edtDocumento.getText().toString()));
        usuario.setDate(datafechaNacimiento.getText().toString());
        usuario.setPass(edtPassword.getText().toString());
        String element = ITEMS[spinner.getSelectedItemPosition()];

        usuario.setState(element);
        realm.copyToRealmOrUpdate(usuario);
      }
    });
    realm.close();
    Intent intent = new Intent(this, ActivityProfile.class);
    startActivity(intent);
    finish();
  }
}
