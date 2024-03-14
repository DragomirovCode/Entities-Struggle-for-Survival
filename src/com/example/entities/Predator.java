package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;
import com.example.Mapping;

import java.util.*;

public class Predator extends Creature{
    private int pathIndex = 0;

    public Predator(String Appearance, Coordinates coordinates) {
        super(Appearance, coordinates);
    }

    @Override
    public void makeMove(Coordinates from, Coordinates to, List<Coordinates> path, Mapping map) {
        if (path == null || path.isEmpty() || pathIndex >= path.size()) {
            findNewPath(from, to, path, map);
        }
        Coordinates nextMove = path.get(pathIndex);
        map.updateEntityPosition(from, nextMove);
        setCoordinates(nextMove);


        if (pathIndex >= path.size() - 1) { // Проверка, достигли ли мы конца пути
            findNewPath(from, to, path, map);
        }else {
            pathIndex++;
        }
    }

    private void findNewPath(Coordinates from, Coordinates to, List<Coordinates> path, Mapping map) {
        List<Coordinates> newPath = searchPath(from, to, map, this);
        if (newPath != null && !newPath.isEmpty()) {
            pathIndex = 1;
            path.clear();
            path.addAll(newPath);

        } else {
            System.out.println("Новый путь не найден");
        }
    }

    @Override
    protected Set<CoordinatesShift> getPossibleMoves() {
        return new HashSet<>(Arrays.asList(
                new CoordinatesShift(1, 0),   // вправо
                new CoordinatesShift(-1, 0),  // влево
                new CoordinatesShift(0, -1),  // вниз
                new CoordinatesShift(0, 1),   // вверх
                new CoordinatesShift(1, 1),   // вверх и вправо
                new CoordinatesShift(-1, -1), // вниз и влево
                new CoordinatesShift(1, -1),  // вниз и вправо
                new CoordinatesShift(-1, 1)   // вверх и влево
        ));
    }

    @Override
    public List<Coordinates> searchPath(Coordinates start, Coordinates end, Mapping map, Entity entity) {
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
                if (next != null && !visited.contains(next)) {
                    if (next.equals(end) || map.getAvailabilityStatusOfCoordinate(next) &&
                            map.checkSpeciesCollision(next, entity)) {
                        queue.add(next);
                        visited.add(next);
                        cameFrom.put(next, current);
                    }
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
