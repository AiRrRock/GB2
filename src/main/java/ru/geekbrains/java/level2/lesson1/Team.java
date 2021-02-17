package ru.geekbrains.java.level2.lesson1;

public class Team {
    private String name;
    private Sportsman[] sportsmen = new Sportsman[4];

    public Team(String name, Sportsman sportsman1, Sportsman sportsman2, Sportsman sportsman3, Sportsman sportsman4) {
        this.name = name;
        this.sportsmen[0] = sportsman1;
        this.sportsmen[1] = sportsman2;
        this.sportsmen[2] = sportsman3;
        this.sportsmen[3] = sportsman4;
    }

    public void printAllFinished(){
        for (Sportsman s: sportsmen){
            if(s.canContinue()){
                System.out.println(s);
            }
        }
    }

    public void printAllSportsman(){
        for (Sportsman s: sportsmen){
            System.out.println(s);
        }
    }

    public Sportsman[] getSportsmen() {
        return sportsmen;
    }
}
