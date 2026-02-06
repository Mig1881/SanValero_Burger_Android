package com.svalero.sv_burger_android.presenter;

import android.content.Context;

import com.svalero.sv_burger_android.R;
import com.svalero.sv_burger_android.contract.BurgerDetailContract;
import com.svalero.sv_burger_android.domain.Burger;
import com.svalero.sv_burger_android.model.BurgerDetailModel;

public class BurgerDetailPresenter implements BurgerDetailContract.Presenter,
        BurgerDetailContract.Model.OnLoadBurgerListener,
        BurgerDetailContract.Model.OnDeleteBurgerListener {

    private BurgerDetailContract.View view;
    private BurgerDetailContract.Model model;
    private Context context;

    public BurgerDetailPresenter(BurgerDetailContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new BurgerDetailModel();
    }

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
        String successMsg = context.getString(R.string.success_delete);
        view.showSuccessMessage(successMsg);
    }

    @Override
    public void onDeleteError(String message) {
        view.showErrorMessage(message);
    }
}