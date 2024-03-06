package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;
import com.example.Map;

import java.util.*;

public class Predator extends Creature{
    private int pathCount = 1;
    public Predator(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    @Override
    public void makeMove(Coordinates from, Coordinates to, List<Coordinates> path, Map map) {
        if (path == null || path.isEmpty() || path.size() == 1) {
            System.out.println("Путь не найден или недействителен");
            return;
        }

        Coordinates nextMove = path.get(pathCount);

        map.updateEntityPosition(from, nextMove);
        setCoordinates(nextMove);
        pathCount++;
    }

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
    public List<Coordinates> searchPath(Coordinates start, Coordinates end, Map map) {
        Queue<Coordinates> queue = new LinkedList<>();
        HashMap<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Set<Coordinates> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        cameFrom.put(start, null);

        while(!queue.isEmpty()){
            Coordinates current = queue.poll();

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
            return null;
        }

        Coordinates current = end;
        List<Coordinates> path = new ArrayList<>();
        while(current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        System.out.println("Найденный путь: " + path);
        return path;
    }
}
