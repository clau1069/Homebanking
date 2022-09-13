package com.MINDHUB.Homebanking.Utils;

public final class CardUtils { //final:para que no se pueda heredar de esa clase

    private CardUtils() {
    }
    public static int Make4number() {
        return (int) (Math.round((Math.random() * (9999-1111))));
    }
    public static String getCardNumber() {
        String cardNumber;
        return cardNumber=  Make4number() + "-" + Make4number() + "-" + Make4number() + "-" + Make4number();
    }
    public static int Make3numbers() {
        return (int) (Math.round((Math.random() * (999-111))));
    }
}
