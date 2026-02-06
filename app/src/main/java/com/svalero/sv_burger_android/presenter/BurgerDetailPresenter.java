package com.svalero.sv_burger_android.presenter;

import com.svalero.sv_burger_android.contract.BurgerDetailContract;
import com.svalero.sv_burger_android.domain.Burger;
import com.svalero.sv_burger_android.model.BurgerDetailModel;

public class BurgerDetailPresenter implements BurgerDetailContract.Presenter,
        BurgerDetailContract.Model.OnLoadBurgerListener,
        BurgerDetailContract.Model.OnDeleteBurgerListener {

    private BurgerDetailContract.View view;
    private BurgerDetailContract.Model model;

    public BurgerDetailPresenter(BurgerDetailContract.View view) {
        this.view = view;
        this.model = new BurgerDetailModel();
    }

    // --- Métodos que llama la Vista ---

    @Override
    public void loadBurger(long id) {
        model.loadBurger(id, this);
    }

    @Override
    public void deleteBurger(long id) {
        model.deleteBurger(id, this);
    }

    @Override
    public void onLoadSuccess(Burger burger) {
        view.showBurgerDetail(burger);
    }

    @Override
    public void onLoadError(String message) {
        view.showErrorMessage(message);
    }

    @Override
    public void onDeleteSuccess() {
        view.showSuccessMessage("Hamburguesa eliminada 🗑️");
    }

    @Override
    public void onDeleteError(String message) {
        view.showErrorMessage(message);
    }
}