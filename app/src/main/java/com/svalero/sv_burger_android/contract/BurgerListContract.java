package com.svalero.sv_burger_android.contract;

import com.svalero.sv_burger_android.domain.Burger;

import java.util.List;

public interface BurgerListContract {
    interface View {
        void showBurgers(List<Burger> burgers);
        void showErrorMessage(String message);
    }

    interface Presenter {
        // CAMBIO: Añadimos el Boolean
        void loadBurgers(Boolean isVegan);
    }

    interface Model {
        interface OnLoadBurgersListener {
            void onSuccess(List<Burger> burgers);
            void onError(String message);
        }
        // CAMBIO: Añadimos el Boolean
        void loadBurgers(Boolean isVegan, OnLoadBurgersListener listener);
    }
}