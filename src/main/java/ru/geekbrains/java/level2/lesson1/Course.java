package ru.geekbrains.java.level2.lesson1;

import java.util.List;

public class Course {
    List<Obstacle> obstacles;

    public Course(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void doIt(Team team){
        for (Sportsman s: team.getSportsmen()){
            for (Obstacle o: obstacles){
                if (s.canContinue()){
                    o.overcome(s);
                }
            }
        }
    }
}
