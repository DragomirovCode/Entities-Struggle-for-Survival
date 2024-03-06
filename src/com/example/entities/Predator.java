package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;
import com.example.Map;

import java.util.*;

public class Predator extends Creature{
    public Predator(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    @Override
    protected void makeMove() {}

    @Override
    protected Set<CoordinatesShift> getPossibleMoves() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(1, 0),
                new CoordinatesShift(-1, 0),
                new CoordinatesShift(0, -1),
                new CoordinatesShift(0, 1)
        ));
    }

    @Override
    public void searchPath(Coordinates start, Coordinates end, Map map) {
        Queue<Coordinates> queue = new LinkedList<>();
        HashMap<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Set<Coordinates> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        cameFrom.put(start, null);

        while(!queue.isEmpty()){
            Coordinates current = queue.poll();
            System.out.println(current + " - текущяя вершина");

            if(current.equals(end)) {
                break;
            }

            for(CoordinatesShift shift : getPossibleMoves()) {
                Coordinates next = current.shift(shift, map);
                if((next != null && (!visited.contains(next)) || (Objects.requireNonNull(next).equals(end)) &&
                        map.getAvailabilityStatusOfCoordinate(next))) {
                    queue.add(next);
                    visited.add(next);
                    cameFrom.put(next, current);
                }
            }
        }

        if(!cameFrom.containsKey(end)) {
            System.out.println("Путь не найден");
            return;
        }

        Coordinates current = end;
        List<Coordinates> path = new ArrayList<>();
        while(current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        System.out.println("Найденный путь: " + path);
    }
}
