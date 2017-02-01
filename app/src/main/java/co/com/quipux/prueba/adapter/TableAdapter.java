package co.com.quipux.prueba.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import de.codecrafters.tableview.TableDataAdapter;
import de.codecrafters.tableview.model.TableColumnModel;
import java.util.List;

/**
 * Created by yeysonjimenez on 2/1/17.
 */

public class TableAdapter extends TableDataAdapter {

  public TableAdapter(Context context, Object[] data)
  {
    super(context, data);
  }

  public TableAdapter(Context context, List data) {
    super(context, data);
  }

  protected TableAdapter(Context context, int columnCount, List data) {
    super(context, columnCount, data);
  }

  protected TableAdapter(Context context, TableColumnModel columnModel, List data) {
    super(context, columnModel, data);
  }

  @Override public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
    return null;
  }
}
