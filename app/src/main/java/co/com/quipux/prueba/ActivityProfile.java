package co.com.quipux.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import co.com.quipux.prueba.adapter.AdapterUser;
import co.com.quipux.prueba.base.BaseActivity;
import co.com.quipux.prueba.entity.User;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;

/**
 * Created by yeysonjimenez on 2/2/17.
 */
public class ActivityProfile extends BaseActivity implements View.OnClickListener {

  private FloatingActionButton addItem;
  private AdapterUser adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    Intent intent = getIntent();
    String id = intent.getStringExtra("roll");
    setTitle(id);
    //Log.e("rol",id);
    configTable();
    injectView();

  }

  private void injectView() {
    addItem = (FloatingActionButton) findViewById(R.id.fab);
    addItem.setOnClickListener(this);
  }

  private void configTable() {
    TableView tableView = (TableView) findViewById(R.id.tableView);
    tableView.setColumnCount(6);
    SimpleTableHeaderAdapter simpleTableHeaderAdapter =
        new SimpleTableHeaderAdapter(this, "Usuario", "Nombre", "Correo","Fecha","Estado","Contrasena");
    simpleTableHeaderAdapter.setTextColor(this.getResources().getColor(R.color.blue));
    tableView.setHeaderAdapter(simpleTableHeaderAdapter);
    Realm realm = Realm.getDefaultInstance();
    RealmResults<User> result = realm.where(User.class).findAll();
    ArrayList<User> list = new ArrayList<>();
    if (!result.isEmpty()) {
      for (User u : result) {
        list.add(u);
        Log.e("ItemProduct add",u.getName());
      }
      adapter = new AdapterUser(this,list);
      tableView.setDataAdapter(adapter);
    }


  }

  @Override public void onClick(View view) {
    Intent intent = new Intent(this, ActivityEditProfile.class);
    startActivity(intent);
    finish();
  }
}
