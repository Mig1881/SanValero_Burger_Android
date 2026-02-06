package com.svalero.sv_burger_android.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import com.svalero.sv_burger_android.api.BurgerApi;
import com.svalero.sv_burger_android.api.BurgerApiInterface;
import com.svalero.sv_burger_android.contract.RegisterBurgerContract;
import com.svalero.sv_burger_android.domain.Burger;
import com.svalero.sv_burger_android.domain.BurgerInDto;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterBurgerPresenter implements RegisterBurgerContract.Presenter {

    private RegisterBurgerContract.View view;
    private BurgerApiInterface api;

    public RegisterBurgerPresenter(RegisterBurgerContract.View view) {
        this.view = view;
        this.api = BurgerApi.buildInstance();
    }

    @Override
    public void addBurger(String name, String ingredients, String priceStr, boolean isVegan, long foodTruckId, Uri imageUri, Context context) {

        if (name.isEmpty() || ingredients.isEmpty() || priceStr.isEmpty()) {
            view.showError("Por favor, rellena todos los campos");
            return;
        }

        float price;
        try {
            price = Float.parseFloat(priceStr);
        } catch (NumberFormatException e) {
            view.showError("El precio no es válido");
            return;
        }

        String base64Image = null;
        if (imageUri != null) {
            base64Image = convertUriToBase64(imageUri, context);
        }

        BurgerInDto burgerData = new BurgerInDto(
                name,
                ingredients,
                price,
                isVegan,
                foodTruckId,
                base64Image
        );

        Call<Burger> call = api.addBurger(burgerData);
        call.enqueue(new Callback<Burger>() {
            @Override
            public void onResponse(Call<Burger> call, Response<Burger> response) {
                if (response.isSuccessful()) {
                    view.showSuccess("¡Hamburguesa creada con éxito! 🍔");
                } else {
                    view.showError("Error al guardar: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Burger> call, Throwable t) {
                view.showError("Fallo de conexión: " + t.getMessage());
            }
        });
    }

    private String convertUriToBase64(Uri imageUri, Context context) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            bitmap = getResizedBitmap(bitmap, 800);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}