package com.example.entities;

import com.example.Coordinates;
import com.example.CoordinatesShift;
import com.example.Mapping;

import java.util.*;

public class AbstractAnimal extends Creature {
    private int pathIndex = 1;
    public AbstractAnimal(String Appearance, Coordinates coordinates, int HP) {
        super(Appearance, coordinates, HP);
    }

    @Override
    public void makeMove(Coordinates from, Coordinates to, List<Coordinates> path, Mapping map, Entity entity) {
        if (path == null || path.isEmpty() || pathIndex >= path.size()) {
            findNewPath(from, to, path, map);
        } else {
            Coordinates nextMove = path.get(pathIndex);
            if(from.areAdjacent(from, nextMove) && map.checkSpeciesCollision(nextMove, entity)) {
                if (map.determineTargetAtNextCoordinates(nextMove, entity)) {
                    Entity targetEntity = map.getEntities().get(nextMove);
                    if (targetEntity.getHP() > 1) {
                        // Атаковать целевую сущность
                        attackTarget(from, nextMove, targetEntity, map, entity);
                    } else {
                        // Поедает сущность
                        entityDevouringAlongPath(from, path, map, entity, targetEntity);
                    }
                } else {
                    // Нет целевой сущности на следующих координатах, продолжить движение по пути
                    move(from, path, map, entity);
                }
            } else {
                // Если текущая цель стала недоступной, найти новую цель
                findNewPath(from, to, path, map);
            }
        }
    }

    private void entityDevouringAlongPath(Coordinates from, List<Coordinates> path, Mapping map, Entity entity, Entity targetEntity) {
        Coordinates nextMove = path.get(pathIndex);
        System.out.println(entity.getAppearance() + " перемещается с " + map.getEntities().get(from).getCoordinates()
                + " в " + nextMove  + " и съедает " + targetEntity.getAppearance());
        map.updateEntityPosition(from, nextMove);
        entity.setCoordinates(nextMove);
        pathIndex++;
    }

    private void move(Coordinates from, List<Coordinates> path, Mapping map, Entity entity){
        Coordinates nextMove = path.get(pathIndex);
        System.out.println(entity.getAppearance() + " перемещается с " + map.getEntities().get(from).getCoordinates() + " в " + nextMove);
        map.updateEntityPosition(from, nextMove);
        entity.setCoordinates(nextMove);
        pathIndex++;
    }

    private void attackTarget(Coordinates from, Coordinates targetCoords, Entity targetEntity, Mapping map, Entity entity) {
        System.out.println(entity.getAppearance() + " перемещается с " + map.getEntities().get(from).getCoordinates() +
                " к " + targetCoords + " и атакует " + targetEntity.getAppearance() +
                " на координатах " + targetCoords + " с " + targetEntity.getHP() + " ХП");
        targetEntity.setHP(targetEntity.getHP() - 1);
    }


    private void findNewPath(Coordinates from, Coordinates to, List<Coordinates> path, Mapping map) {
        List<Coordinates> newPath = searchPath(from, to, map);
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
    public List<Coordinates> searchPath(Coordinates start, Coordinates end, Mapping map) {
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
                    if (next.equals(end) || map.getAvailabilityStatusOfCoordinate(next)) {
                        queue.add(next);
                        visited.add(next);
                        cameFrom.put(next, current);
                    }
                }
            }
        }

        if(!cameFrom.containsKey(end)) {
            //   System.out.println("Путь не найден");
            return null;
        }

        Coordinates current = end;
        List<Coordinates> path = new ArrayList<>();
        while(current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        // System.out.println("Найденный путь: " + path);
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return pathIndex == that.pathIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathIndex);
    }
}
