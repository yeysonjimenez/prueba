package co.com.quipux.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import co.com.quipux.prueba.adapter.AdapterTable;
import co.com.quipux.prueba.base.BaseActivity;
import co.com.quipux.prueba.entity.ItemsProduct;
import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;

/**
 * Created by yeysonjimenez on 2/1/17.
 */
public class ActivityProduct extends BaseActivity implements View.OnClickListener {

  private FloatingActionButton addItem;
  private static final String[][] DATA_TO_SHOW = {
      { "This", "is", "a", "test" }, { "and", "a", "second", "test" }
  };
  private AdapterTable adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.productos);
    Log.e("ActivityProduct", "llego setv");
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
    TableView tableView = (TableView) findViewById(R.id.tableView);
    tableView.setColumnCount(5);
    SimpleTableHeaderAdapter simpleTableHeaderAdapter =
        new SimpleTableHeaderAdapter(this, "Nombre", "Descripcion", "Estado", "Origen");
    simpleTableHeaderAdapter.setTextColor(this.getResources().getColor(R.color.blue));

    Realm realm = Realm.getDefaultInstance();
    RealmResults<ItemsProduct> result = realm.where(ItemsProduct.class).findAll();
    ArrayList<ItemsProduct> list = new ArrayList<>();
    if (!result.isEmpty()) {
      for (ItemsProduct item : result) {
        list.add(item);
        Log.e("ItemProduct add", item.getName());
      }
      adapter = new AdapterTable(this, list);
      tableView.setDataAdapter(adapter);
    }





    //

    // RealmResults<ItemsProduct> result = realm.where(ItemsProduct.class).findAll();
    // Log.e("result items", result.size() + "");
    // if (result.size() > 0) Log.e("name", result.last().getName());
    // //tableView.setDataAdapter(new SimpleTableDataAdapter(this, ));

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
