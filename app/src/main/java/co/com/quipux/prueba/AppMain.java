package co.com.quipux.prueba;

import android.app.Application;
import android.util.Log;
import co.com.quipux.prueba.entity.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yeysonjimenez on 2/1/17.
 */

public class AppMain extends Application {


  @Override public void onCreate() {
    super.onCreate();
    Log.e("AppMain","App");
    Realm.init(this);
    RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
    Realm.setDefaultConfiguration(realmConfiguration);
    createSperUser();
  }


  private void createSperUser() {

      Realm realm = Realm.getDefaultInstance();
    Log.e("AppMain","Creando User");
      realm.executeTransaction(new Realm.Transaction() {
        @Override
        public void execute(Realm realm) {
          User admin = new User();
          admin.setUser("Admin");
          admin.setName("Administrador");
          admin.setDocument(123456789);
          admin.setEmail("admin@quipux.com");
          admin.setPass("123456789");
          admin.setState("activo");
          admin.setDate("01/02/2017");
          realm.copyToRealmOrUpdate(admin);
          Log.e("AppMain","Despues User");
        }
      });


  realm.close();
  }

}
