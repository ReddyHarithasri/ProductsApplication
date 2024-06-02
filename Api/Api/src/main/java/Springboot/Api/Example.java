package Springboot.Api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Example {
    public static void main (String[] args){
        List<String> input= Arrays.asList("Alice Smith","Bob Johnson","Eve miller","David lee");
        List<String>  output=filterNames(input);
        int characterCount = countCharactersInConsonantNames(input);

        System.out.println("input names :" +input);
        System.out.println("filter names :" +output);
        System.out.println(characterCount);

    }
    private static List<String> filterNames(List<String> names){
        return names.stream().filter(name-> startWithVowels(name.split(" ")[0]))
//                .sorted((name1, name2) -> {
//                    String[] lastName1 = name1.split(" ");
//                    String[] lastName2 = name2.split(" ");
//                    return lastName1[1].compareTo(lastName2[1]);
//                })
                .collect(Collectors.toList());
    }
    private static int countCharactersInConsonantNames(List<String> names) {
        return names.stream()
                .filter(name -> !startWithVowels(name.split(" ")[0]))
                .mapToInt(String::length)
                .sum();
    }
    private static boolean startWithVowels(String name){
        char firstChar = Character.toLowerCase(name.charAt(0));
        return "aeiou".indexOf(firstChar) != -1;
    }



}
