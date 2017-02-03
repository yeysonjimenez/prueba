package co.com.quipux.prueba;

import android.os.Bundle;
import android.support.annotation.Nullable;
import co.com.quipux.prueba.R;
import co.com.quipux.prueba.base.BaseActivity;

/**
 * Created by yeysonjimenez on 2/2/17.
 */
public class ActivityEditProfile  extends BaseActivity{
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_user);
  }
}
