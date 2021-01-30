package com.example.ganache;

public class Cart {
    private int cartId;
    private String cartName;
    private String cartidImage;
    private String cartPrice;
    private int cartNumber;

    public Cart(int cartId, String cartName, String cartidImage, String cartPrice, int cartNumber) {
        this.cartId = cartId;
        this.cartName = cartName;
        this.cartidImage = cartidImage;
        this.cartPrice = cartPrice;
        this.cartNumber = cartNumber;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getCartidImage() {
        return cartidImage;
    }

    public void setCartidImage(String cartidImage) {
        this.cartidImage = cartidImage;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    public int getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(int cartNumber) {
        this.cartNumber = cartNumber;
    }
}
