import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DinnerConstructor {
    private Map<String, List<String>> dishesByType = new HashMap<>();
    private Random random = new Random();

    public void addDish(String type, String name) {
        if (!dishesByType.containsKey(type)) {
            dishesByType.put(type, new ArrayList<>());
        }
        dishesByType.get(type).add(name);
    }

    public boolean checkType(String type) {
        return dishesByType.containsKey(type);
    }

    public List<List<String>> generateCombos(int count, List<String> requestedTypes) {
        List<List<String>> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<String> combo = new ArrayList<>();
            for (String type : requestedTypes) {
                List<String> dishes = dishesByType.get(type);
                if (dishes != null && !dishes.isEmpty()) {
                    int index = random.nextInt(dishes.size());
                    combo.add(dishes.get(index));
                }
            }
            result.add(combo);
        }

        return result;
    }
}

