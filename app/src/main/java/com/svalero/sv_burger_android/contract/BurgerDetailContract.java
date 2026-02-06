package com.svalero.sv_burger_android.contract;

import com.svalero.sv_burger_android.domain.Burger;

public interface BurgerDetailContract {

    interface View {
        void showBurgerDetail(Burger burger);
        void showSuccessMessage(String message);
        void showErrorMessage(String message);
    }

    interface Presenter {
        void loadBurger(long id);
        void deleteBurger(long id);
    }


    interface Model {
        // Callback para cuando cargamos la burger
        interface OnLoadBurgerListener {
            void onLoadSuccess(Burger burger);
            void onLoadError(String message);
        }

        // Callback para cuando borramos
        interface OnDeleteBurgerListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }

        void loadBurger(long id, OnLoadBurgerListener listener);
        void deleteBurger(long id, OnDeleteBurgerListener listener);
    }
}