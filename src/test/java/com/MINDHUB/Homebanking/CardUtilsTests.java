package com.MINDHUB.Homebanking;

import com.MINDHUB.Homebanking.Services.CardService;
import com.MINDHUB.Homebanking.Services.ClientService;
import com.MINDHUB.Homebanking.Utils.CardUtils;
import com.MINDHUB.Homebanking.models.Card;
import com.MINDHUB.Homebanking.repositories.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
@SpringBootTest
public class CardUtilsTests {
    @Autowired
    CardService cardService;
    @Autowired
    CardRepository cardRepository;
    /*@Test
    public void cardNumberIsCreated(){
        String cardNumber = CardUtils.getCardNumber();
        assertThat(cardNumber,is(not(emptyOrNullString())));
    }
    @Test
    public void cardNumberStartsWhitScript(){
        String cardNumber= CardUtils.getCardNumber();
        assertThat(cardNumber, not(startsWith("-")));
    }
    @Test
    public void cvvIsLessThan(){
        int cvv = CardUtils.Make3numbers();
        assertThat(cvv, lessThan(100));
    }
    @Test
    public void cvvIsGreatherThan(){
        int cvv = CardUtils.Make3numbers();
        assertThat(cvv, greaterThan(99));
    }*/

}
