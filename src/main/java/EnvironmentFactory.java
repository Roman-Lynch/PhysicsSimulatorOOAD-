public class EnvironmentFactory {
    public Environment createEnvironment(String EnvironmentType, int height, int width, double wallElasticity) {
        if ("Earth".equals(EnvironmentType)) {
            return new Earth(height, width,wallElasticity);
        } else if ("Mars".equals(EnvironmentType)) {
            return new Mars(height, width,wallElasticity);
        } else if ("Jupiter".equals(EnvironmentType)) {
            return new Jupiter(height, width,wallElasticity);
        } else if ("Neptune".equals(EnvironmentType)) {
            return new Neptune(height, width,wallElasticity);
        } else if ("Moon".equals(EnvironmentType)) {
            return new Moon(height, width,wallElasticity);
        } else if ("Venus".equals(EnvironmentType)) {
            return new Venus(height, width,wallElasticity);
        } else if ("Saturn".equals(EnvironmentType)) {
            return new Saturn(height, width, wallElasticity);
        } else if ("Mercury".equals(EnvironmentType)) {
            return new Mercury(height, width, wallElasticity);
        } else if ("Uranus".equals(EnvironmentType)) {
            return new Mercury(height, width, wallElasticity);
        } else if ("BlackHole".equals(EnvironmentType)) {
            return new BlackHole(height, width, wallElasticity);
        } else if ("Sun".equals(EnvironmentType)) {
            return new Sun(height, width, wallElasticity);
        } else {
            throw new IllegalArgumentException("Invalid environment type");
        }
    }
}
