package pegas;

import pegas.functions.Action1;
import pegas.functions.Client;
import pegas.functions.Func1;
import pegas.functions.Func2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        ArrayList<Client> arr = new ArrayList<>();
        arr.add(new Client("Nik", 15, true));
        arr.add(new Client("Adam", 27, true));
        arr.add(new Client("Alex", 9, true));
        arr.add(new Client("Kale", 115, true));
        arr.add(new Client("Otto", 7, true));
//        Action1<Client> arq = item -> {
//            item.setActive(false);
//        };
        foreach(arr, c-> c.setBalance(c.getBalance()-20));
        print(arr);
        List<String> res1 = map(arr, Client::getName);
        print(res1);
        print(filter(arr, c->c.getBalance()>=0));
        print(Collections.singleton(find(arr, c -> c.getBalance() >= 0)));
        print(Collections.singleton(fold(arr, 0, (Client client, Integer sum) -> client.getBalance() + sum)));
    }
    private static void print(Collection<?> item){
        for(Object i: item){
            System.out.println(i);
        }
    }
    public static <X> void foreach(List<X> list, Action1<X> action1){
        for (X item: list){
            action1.apply(item);
        }
    }

    public static <X,Y> List<Y> map(List<X> list, Func1<X,Y> mapFunc){
        ArrayList<Y> result = new ArrayList<>();
        for (X i: list){
            result.add(mapFunc.apply(i));
        }
        return result;
    }
    public static <X> List<X> filter(List<X> list, Func1<X,Boolean> filterFunc){
        ArrayList<X> result = new ArrayList<>();
        for (X i: list){
            if(filterFunc.apply(i))
                result.add(i);
        }
        return result;
    }
    public static <X> X find(List<X> list, Func1<X,Boolean> filterFunc){
        for (X i: list){
            if(filterFunc.apply(i))return i;
        }
        return null;
    }
    public static <X, Y> Y fold(List<X> list, Y intValue, Func2<X,Y,Y> foldFunction){
        Y result = intValue;
        for (X i: list){
            result = foldFunction.apply(i, result);
        }return result;
    }

}
