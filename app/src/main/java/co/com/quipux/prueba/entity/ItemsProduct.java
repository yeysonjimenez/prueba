package co.com.quipux.prueba.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yeysonjimenez on 2/1/17.
 */

public class ItemsProduct extends RealmObject {

  @PrimaryKey private String name;
  private String description;
  private String estado;
  private String origen;
  private String cantidad;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getOrigen() {
    return origen;
  }

  public void setOrigen(String origen) {
    this.origen = origen;
  }

  public String getCantidad() {
    return cantidad;
  }

  public void setCantidad(String cantidad) {
    this.cantidad = cantidad;
  }

  @Override public String toString() {
    JSONObject jsonObject = new JSONObject();
    try {
      jsonObject.put("name", getName());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return jsonObject.toString();
  }
}
