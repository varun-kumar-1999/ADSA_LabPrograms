import java.util.*;

public class SetCover {
    interface Filter<T> {
        boolean matches(T t);
    }

    private static <T> Set<T> shortestCombination(Filter<Set<T>> filter, List<T> sets) {
        final int size = sets.size();
        if (size > 20) throw new IllegalArgumentException("Too many combinations");

        int combinations = 1 << size;
        List<Set<T>> possibleSolutions = new ArrayList<>();

        for (int i = 0; i < combinations; i++) {
            Set<T> combination = new LinkedHashSet<>();
            for (int j = 0; j < size; j++) {
                if (((i >> j) & 1) != 0)
                    combination.add(sets.get(j));
            }
            possibleSolutions.add(combination);
        }

        possibleSolutions.sort(Comparator.comparingInt(Set::size));

        for (Set<T> x : possibleSolutions) {
            if (filter.matches(x))
                return x;
        }
        return null;
    }
    public static void main(String[] args) {
        Integer[][] all = {
                {1, 2, 3, 8, 9, 10},
                {1, 2, 3, 4, 5},
                {4, 5, 7},
                {5, 6, 7},
                {6, 7, 8, 9, 10},
        };
        Integer[] solution = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        List<Set<Integer>> sets = new ArrayList<>();

        for (Integer[] array : all)
            sets.add(new LinkedHashSet<>(Arrays.asList(array)));

        Set<Integer> solutionSet = new LinkedHashSet<>(Arrays.asList(solution));

        Filter<Set<Set<Integer>>> filter = integers -> {
            Set<Integer> union = new LinkedHashSet<>();
            for (Set<Integer> ints : integers)
                union.addAll(ints);
            return union.equals(solutionSet);
        };

        Set<Set<Integer>> firstSol = shortestCombination(filter, sets);
        System.out.println("The shortest combination was: " + firstSol);
    }
}