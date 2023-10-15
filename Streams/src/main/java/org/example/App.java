package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;

/**
 * Ventajas -> facilidad y rapidez de escritura
 * No va a ocupar ni mas tiempo ni mas memoria de lo que puedo hacer yo
 * Incluso puede hacer varios hilos de ejecucion si cada dato no depende de otro
 * Mitad de flujo por cada hilo (si son 2) -> va a ser mas rapido, obviamente
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayList<Integer> lista = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < 30; i++)
        {
            lista.add(r.nextInt(-1000, 1000));
        }

        System.out.println(lista);
        System.out.println("Con programación estructurada: ");
        for(Integer x: lista){
            if (x>0){
                System.out.println(x);
            }
        }

        System.out.println("Con Streams: "); // Se van a ver en otro orden que en estructurada igualmente
        //creo un fitro desde un arrayList y una operacion funcional (predicate) al filter
        // Ademas, a cada uno de esos los quiero imprimir, le hago forEach y le paso un consumer

        //lista.stream().filter(x -> x>0).forEach(x->System.out.println(x));



        // Podemos asignar para no tener que escribir las expresiones lambda
        // en cada parametro

        Predicate<Integer> esPositivo = x -> x>0;
        Function<Integer, Integer> cuadrado = x -> x*x;
        // Podemos poner referencia a metodo de la siguiente manera cuando el
        // unico parametro se pasa directamente a una función:
        Consumer<Integer> imprimir = System.out::println;

        lista.stream().
                filter(esPositivo).
                forEach(System.out::println);

        System.out.println("Cuadrados de todos con map:");
        lista.stream().
                map(cuadrado).
                sorted(). // asi de menor a mayor, pero le puedo pasar parametro el comparador para organizar objetos producto por stock, por ejemplo
                forEach(imprimir);

        System.out.println("Segundo par de Cuadrados de los positivos:");
        lista.stream().
                filter(esPositivo).
                map(cuadrado).
                sorted().
                skip(2).
                limit(2).
                forEach(imprimir);

        System.out.println("Suma de todos: ");
        Integer suma = lista.stream().reduce((a,b)->a+b).orElse(0);
        System.out.println(suma);

        System.out.println("Productoria de todos: ");
        Integer productoria = lista.stream().reduce((a,b)->a*b).orElse(0);
        System.out.println(productoria);

        System.out.println("Mayor de todos: ");
        Integer mayor = lista.stream().reduce(Math::max).orElse(0);
        System.out.println(mayor);

        //allMatch(predicate) -> V si todos dan V
        //anyMatch(predicate) -> V si alguno da V
        //noneMatch(predicate) -> V si ninguno da V

        Boolean algunMultiploDe5 = lista.stream().anyMatch(x -> x % 5 == 0);
        System.out.println("Hay multiplo de 5?: " + algunMultiploDe5);

        //Generar un infinitos ints en un rango que limito en cantidad con limit
        List<Integer>  listaInteger = new Random().ints(-100, 100)
                .limit(10)
                .boxed()
                .toList();

        System.out.println(lista);


    }
}
