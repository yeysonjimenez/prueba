package co.com.quipux.prueba.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import co.com.quipux.prueba.ActivityProduct;
import co.com.quipux.prueba.ActivityProfile;
import co.com.quipux.prueba.ItemForm;
import co.com.quipux.prueba.R;
import co.com.quipux.prueba.entity.ItemsProduct;
import co.com.quipux.prueba.entity.User;
import co.com.quipux.prueba.utils.UserRoll;
import de.codecrafters.tableview.TableDataAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;

/**
 * Created by yeysonjimenez on 2/2/17.
 */

public class AdapterTable extends TableDataAdapter {

  ArrayList<User> user;
  ArrayList<ItemsProduct> data ;

  Context context;

  public AdapterTable(Context context, ArrayList<ItemsProduct> result) {
    super(context, result);
    this.data = result;
    this.context=context;
  }



  @Override public View getCellView(final int rowIndex, int columnIndex, ViewGroup parentView) {

    //ItemsProduct car = (ItemsProduct) getRowData(rowIndex);
      LayoutInflater mInflater = (LayoutInflater)
          context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
      View view = mInflater.inflate(R.layout.itemtable,null);
    TextView it = (TextView) view.findViewById(R.id.txtItem);
    ImageView btnEdit = (ImageView) view.findViewById(R.id.btnEdit);
    ImageView btnDelete = (ImageView) view.findViewById(R.id.btnDelete);
    //
    //Log.e("columnas",columnIndex+"");
    //
    //Log.e("filas",rowIndex+"");
    //for (ItemsProduct p:data
    //) {
    //  Log.e("dato",p.getName());
    //}
    switch (columnIndex) {
      case 0:

        it.setText(data.get(rowIndex).getName());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 1:
        it.setText(data.get(rowIndex).getDescription());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 2:
        it.setText(data.get(rowIndex).getEstado());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 3:
        it.setText(data.get(rowIndex).getOrigen());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 4:
        it.setText(data.get(rowIndex).getFecha());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 5:
        it.setText(data.get(rowIndex).getCantidad());

        btnEdit.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            goMyEdit(rowIndex,data);
          }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            goDelete(rowIndex,data);
          }
        });

        break;
    }
      //TextView it = (TextView) view.findViewById(R.id.txtItem);
      //Log.e("star", ""+items.get(i).getIcon());

      //it.setText(data.get(rowIndex).getName());
      return view;

  }

  private void goDelete(final int rowIndex, final ArrayList<ItemsProduct> data) {

    Realm real = Realm.getDefaultInstance();
    real.executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        RealmResults<ItemsProduct> res =realm.where(ItemsProduct.class).equalTo("name",data.get(rowIndex).getName()).findAll();

        if(!res.isEmpty())
        {
          ItemsProduct item = res.first();
          item.deleteFromRealm();
          goMyProduct();

        }
      }
    });


  }

  private void goMyProduct() {
    Intent intent = new Intent(context, ActivityProduct.class);
    context.startActivity(intent);
  }

  private void goMyEdit(int rowIndex, ArrayList<ItemsProduct> data) {
    Intent i = new Intent(context, ItemForm.class);
    i.putExtra("name", data.get(rowIndex).getName());
    i.putExtra("description", data.get(rowIndex).getDescription());
    i.putExtra("estado",data.get(rowIndex).getEstado());
    i.putExtra("origen", data.get(rowIndex).getOrigen());
    i.putExtra("cant", data.get(rowIndex).getCantidad());
    context.startActivity(i);
  }


}
