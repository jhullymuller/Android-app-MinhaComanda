package com.example.minhacomanda.bo;

import com.example.minhacomanda.model.ComandaProduto;

import java.util.List;

public class ComandaProdutoBo {
   public  static  boolean validaProduto(ComandaProduto comandaProduto){
      return  (comandaProduto.getProduto() != null) && comandaProduto.getQuantidade() != null && comandaProduto.getQuantidade()>0;
   }
   public static Double calcularTotalaPagar(List<ComandaProduto> comandaProdutos){
      Double total = 0d;
      if (comandaProdutos != null) {
          for (ComandaProduto comandaProduto : comandaProdutos) {
              total += comandaProduto.getSubtotal();
          }
      }

       return total;
   }
}
