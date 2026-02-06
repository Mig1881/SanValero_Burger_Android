package com.svalero.sv_burger_android.contract;

import com.svalero.sv_burger_android.domain.Burger;

import java.util.List;

public interface BurgerListContract {
    interface Model {
        interface OnLoadBurgersListener {
            void onSuccess(List<Burger> burgers);
            void onError(String message);
        }
        void loadBurgers(OnLoadBurgersListener listener);
    }
    interface View {
        void showBurgers(List<Burger> burgers);
        void showErrorMessage(String message);
    }
    interface Presenter {
        void loadBurgers();
    }
}