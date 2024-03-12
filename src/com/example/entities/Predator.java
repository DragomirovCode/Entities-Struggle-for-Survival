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
        if (path == null || path.isEmpty()) {
            System.out.println("Путь не найден");
            return;
        }if(path.size() == 1){
            System.out.println("Путь недействителен");
            return;
        }
        Coordinates nextMove = path.get(pathIndex);
        map.updateEntityPosition(from, nextMove);
        setCoordinates(nextMove);


        if (pathIndex >= path.size() - 1) { // Проверка, достигли ли мы конца пути
            List<Coordinates> newPath = searchPath(from, to, map, this);
            if (newPath != null && !newPath.isEmpty()) {
                pathIndex = 0;
                path.clear();
                path.addAll(newPath);

            } else {
                System.out.println("Новый путь не найден");
                return;
            }
        }else {
            pathIndex++;
        }
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
