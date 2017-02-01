package co.com.quipux.prueba.base;

import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import co.com.quipux.prueba.entity.ItemsProduct;
import co.com.quipux.prueba.entity.User;
import io.realm.Realm;
import io.realm.RealmResults;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yeysonjimenez on 2/1/17.
 */

public class BaseActivity extends AppCompatActivity {

  public static String user = null;
  private Realm realm;

  @Override public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    realm = Realm.getDefaultInstance();
    //ceateUserAdmin();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (realm != null) realm.close();
  }

  public Realm getRealm() {
    realm = realm != null && !realm.isClosed() ? realm = Realm.getDefaultInstance() : realm;
    return realm;
  }

  public User getUserSync() {
    return getRealm() != null ? getRealm().where(User.class).findFirst() : null;
  }

  public void actulizarUsuario(final User upUser) {
    final RealmResults<User> result =
        realm.where(User.class).equalTo("documento", upUser.getDocument()).findAll();
    if (!result.isEmpty()) {
      getRealm().executeTransaction(new Realm.Transaction() {
        @Override public void execute(Realm realm) {
          User usuario = result.first();
          if (upUser.getName() != null) {
            usuario.setName(upUser.getName());
          }
          realm.copyToRealmOrUpdate(usuario);
        }
      });
    }
  }

  public void guardarUsuario(final User usuario) {
    getRealm().executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        realm.copyToRealmOrUpdate(usuario);
      }
    });
  }
  public void guardarItem(final ItemsProduct item) {
    Realm realm = Realm.getDefaultInstance();
    realm.executeTransaction(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        realm.copyToRealmOrUpdate(item);
      }
    });
    realm.close();
  }

  public void elimiarUsuario(final User upUser) {
    final RealmResults<User> result =
        realm.where(User.class).equalTo("documento", upUser.getDocument()).findAll();
    if (!result.isEmpty()) {
      getRealm().executeTransaction(new Realm.Transaction() {
        @Override public void execute(Realm realm) {
          User usuario = result.first();
          usuario.deleteFromRealm();
        }
      });
    } else {
      Log.e("usuario no encontrado" , upUser.toString());
    }
  }
}
