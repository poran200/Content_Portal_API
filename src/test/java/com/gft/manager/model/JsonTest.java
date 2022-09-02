package com.gft.manager.model;



import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

public class JsonTest {

     @Test
    public void getPrice(){
         String s = "RIO International Smart Gift Card - 15000 TK";
         Optional<String> first = Arrays.stream(s.split("-")).skip(1).findFirst();
         System.out.println(Integer.parseInt(first.get().trim().replace(" TK","")));

     }

}
