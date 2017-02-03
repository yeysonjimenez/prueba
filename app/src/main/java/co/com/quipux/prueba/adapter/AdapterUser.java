package co.com.quipux.prueba.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import co.com.quipux.prueba.ActivityEditProfile;
import co.com.quipux.prueba.ActivityProduct;
import co.com.quipux.prueba.ActivityProfile;
import co.com.quipux.prueba.ItemForm;
import co.com.quipux.prueba.R;
import co.com.quipux.prueba.entity.ItemsProduct;
import co.com.quipux.prueba.entity.User;
import de.codecrafters.tableview.TableDataAdapter;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.ArrayList;

/**
 * Created by yeysonjimenez on 2/2/17.
 */

public class AdapterUser extends TableDataAdapter{

  ArrayList<User> user;
  Context context;

  public AdapterUser(Context activityProfile, ArrayList<User> list) {
    super(activityProfile, list);
    this.user = list;
    this.context=activityProfile;

  }

  @Override public View getCellView(final int rowIndex, int columnIndex, ViewGroup parentView) {
    LayoutInflater mInflater = (LayoutInflater)
        context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    View view = mInflater.inflate(R.layout.itemtable,null);
    TextView it = (TextView) view.findViewById(R.id.txtItem);
    final ImageView btnEdit = (ImageView) view.findViewById(R.id.btnEdit);
    final ImageView btnDelete = (ImageView) view.findViewById(R.id.btnDelete);
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

        it.setText(user.get(rowIndex).getUser());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 1:
        it.setText(user.get(rowIndex).getName());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 2:
        it.setText(user.get(rowIndex).getEmail());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;

      case 3:
        it.setText(user.get(rowIndex).getDate());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 4:
        it.setText(user.get(rowIndex).getState());
        btnEdit.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        break;
      case 5:
        it.setText(user.get(rowIndex).getPass());
        btnEdit.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            goMyEdit(rowIndex,user);
          }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View view) {
            goDelete(rowIndex,user);
          }
        });
        break;
    }
    //TextView it = (TextView) view.findViewById(R.id.txtItem);
    //Log.e("star", ""+items.get(i).getIcon());

    //it.setText(data.get(rowIndex).getName());
    return view;

  }

  private void goMyEdit(int rowIndex, ArrayList<User> user) {

    Intent i = new Intent(context, ActivityEditProfile.class);
    i.putExtra("name", user.get(rowIndex).getName());
    i.putExtra("correo", user.get(rowIndex).getEmail());
    i.putExtra("fecha",user.get(rowIndex).getDate());
    i.putExtra("estado", user.get(rowIndex).getState());
    i.putExtra("contrasena", user.get(rowIndex).getPass());
    context.startActivity(i);

  }


  private void goDelete(final int rowIndex, final ArrayList<User> data) {

    Realm real = Realm.getDefaultInstance();
    real.executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        RealmResults<User>
            res =realm.where(User.class).equalTo("name",data.get(rowIndex).getName()).findAll();

        if(!res.isEmpty())
        {
          User item = res.first();
          item.deleteFromRealm();
          goMyProduct();
        }
      }
    });


  }

  private void goMyProduct() {
    Intent intent = new Intent(context, ActivityProfile.class);
    context.startActivity(intent);
  }
}
