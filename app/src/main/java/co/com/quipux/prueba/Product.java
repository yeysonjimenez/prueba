package co.com.quipux.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import co.com.quipux.prueba.base.BaseActivity;
import co.com.quipux.prueba.entity.ItemsProduct;
import co.com.quipux.prueba.entity.User;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by yeysonjimenez on 2/1/17.
 */
public class Product extends BaseActivity implements View.OnClickListener {

  private FloatingActionButton addItem;
  private static final String[][] DATA_TO_SHOW = {
      { "This", "is", "a", "test" }, { "and", "a", "second", "test" }
  };

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.productos);
    Log.e("Product", "llego setv");
    Intent intent = getIntent();
    String id = intent.getStringExtra("roll");
    configTable();
    injectView();
  }

  private void injectView() {
    addItem = (FloatingActionButton) findViewById(R.id.fab);
    addItem.setOnClickListener(this);
  }

  private void configTable() {
    //TableView tableView = (TableView) findViewById(R.id.tableView);
    TableView<String[]> tableView = (TableView<String[]>) findViewById(R.id.tableView);
    tableView.setColumnCount(4);
    SimpleTableHeaderAdapter simpleTableHeaderAdapter =
        new SimpleTableHeaderAdapter(this, "Nombre", "Descripcion", "Estado", "Origen");
    simpleTableHeaderAdapter.setTextColor(this.getResources().getColor(R.color.blue));
    tableView.setHeaderAdapter(simpleTableHeaderAdapter);
    Realm realm = Realm.getDefaultInstance();
    RealmResults<ItemsProduct> result = realm.where(ItemsProduct.class).findAll();
    Log.e("result items", result.size() + "");
    if (result.size() > 0) Log.e("name", result.last().getName());
    //tableView.setDataAdapter(new SimpleTableDataAdapter(this, ));
    realm.close();
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {

      case R.id.fab:
        goaddItem();
        break;
    }
  }

  private void goaddItem() {
    Intent intent = new Intent(this, ItemForm.class);
    startActivity(intent);
  }
}
