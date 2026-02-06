package com.svalero.sv_burger_android.contract;

import android.content.Context;
import android.net.Uri;

public interface RegisterBurgerContract {

    interface View {
        void showSuccess(String message);
        void showError(String message);
    }

    interface Presenter {
        void addBurger(String name, String ingredients, String price, boolean isVegan, long foodTruckId, Uri imageUri, Context context);
    }
}
