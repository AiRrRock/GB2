package ru.geekbrains.java.level2.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Human human = new Human("Ed", 1000, 20);
        Cat cat = new Cat("Cat", 100, 50);
        Robot robot = new Robot("Rob", 3000000, 500);
        Cat cat2 = new Cat("Cat2", 9000, 90);
        Team team = new Team("SomeName", human, cat2, cat, robot);

        Wall wall = new Wall(19);
        Wall wall1 = new Wall(45);
        Wall wall2 = new Wall(99);

        Tredmill tredmill = new Tredmill(150);
        Tredmill tredmill1 = new Tredmill(400);
        List<Obstacle> obstacles = new ArrayList<Obstacle>();
        obstacles.add(wall);
        obstacles.add(wall1);
        obstacles.add(tredmill);
        obstacles.add(tredmill1);
        obstacles.add(wall2);

        Course course = new Course(obstacles);

        course.doIt(team);

        team.printAllFinished();
        team.printAllSportsman();

    }
}
