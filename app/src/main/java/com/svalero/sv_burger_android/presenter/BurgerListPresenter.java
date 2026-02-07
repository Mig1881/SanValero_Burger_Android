package com.svalero.sv_burger_android.presenter;

import com.svalero.sv_burger_android.contract.BurgerListContract;
import com.svalero.sv_burger_android.domain.Burger;
import com.svalero.sv_burger_android.model.BurgerListModel;

import java.util.List;

public class BurgerListPresenter implements BurgerListContract.Presenter, BurgerListContract.Model.OnLoadBurgersListener {

    private BurgerListContract.View view;
    private BurgerListContract.Model model;

    public BurgerListPresenter(BurgerListContract.View view) {
        this.view = view;
        this.model = new BurgerListModel();
    }

    @Override
    public void loadBurgers(Boolean isVegan) {
        model.loadBurgers(isVegan, this);
    }

    @Override
    public void onSuccess(List<Burger> burgers) {
        view.showBurgers(burgers);
    }

    @Override
    public void onError(String message) {
        view.showErrorMessage(message);
    }
}
