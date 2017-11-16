package eag.myapplication;

import android.widget.ImageView;

/**
 * Created by JotaC on 15/10/17.
 */

public class Heroes {

    private String name;
    private int idIcon;

    public Heroes(String name, int idIcon) {
        this.name = name;
        this.idIcon = idIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(int idIcon) {
        this.idIcon = idIcon;
    }
}
