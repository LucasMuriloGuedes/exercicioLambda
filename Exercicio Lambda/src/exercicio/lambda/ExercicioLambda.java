/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio.lambda;

import entities.Product;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Lucas Murilo
 */
public class ExercicioLambda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Fazer um programa para le um conjunto de produtos a partir de um arquivo em formato.csv(Suponha que exista pelo menos um produto).
         Em seguida mostrar o preco médio dos produtos. Depois, mostrar os nomes, em ordem decrescente, dos produtos que possuem preço inferior
        ao preceo medio */
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter full file path");
        String path = sc.next();
        
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Product> list = new ArrayList<>();
            
            String line = br.readLine();
            while(line != null){
                String[] fields = line.split(",");
                list.add(new Product(fields[0], Double.parseDouble(fields[1])));
                line = br.readLine();
            }
            
            double avg = list.stream().map(p -> p.getPrice()).reduce(0.0, (x,y) -> x + y) / list.size();
            
            System.out.println("Avarage price: " + avg);
            
            Comparator<String>  comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
            
            List<String> names = list.stream()
                    .filter(p -> p.getPrice() < avg)
                    .map(p -> p.getName())
                    .sorted(comp.reversed())
                    .collect(Collectors.toList());
            
            System.out.println(names);
                     
        }
        
          
        
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
            
        }
        
        
        
    }
    
}
