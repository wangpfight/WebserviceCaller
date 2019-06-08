package utils

import android.app.Activity
import android.support.design.widget.Snackbar
import com.example.retrofitdemo.R


class AndroidUtility {
    companion object {
        fun showSnackBar(activity: Activity, message: String) {
            val snackbar: Snackbar
            snackbar = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
            val snackBarView = snackbar.getView();
            //snackBarView.setBackgroundColor(activity.resources.getColor(R.color.abc_hint_foreground_material_light));
            snackbar.show();
        }
    }
}