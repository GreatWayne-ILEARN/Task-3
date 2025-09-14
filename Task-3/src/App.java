import java.lang.annotation.*;

public class App {
    
    public static void main(String[] args) throws Exception {
         // List of classes to inspect
        Class<?>[] classes = { LoginFeature.class, VotingFeature.class };

        for (Class<?> cls : classes) {
            // Check if class has the annotation
            if (cls.isAnnotationPresent(EnhancementRequest.class)) {
                // Get the annotation
                EnhancementRequest er = cls.getAnnotation(EnhancementRequest.class);

                // Print values
                System.out.println("Class: " + cls.getSimpleName());
                System.out.println("  ID: " + er.id());
                System.out.println("  Synopsis: " + er.synopsis());
                System.out.println("  Engineer: " + er.engineer());
                System.out.println("  Date: " + er.date());
                System.out.println();
            }
        }
    

        System.out.println("Hello, World!");
    }

    @Retention(RetentionPolicy.RUNTIME)   // Keep it available at runtime
    
    @Target(ElementType.TYPE)             // Can be applied to classes/interfaces
    public @interface EnhancementRequest {
        int id();
        String synopsis();
        String engineer() default "unassigned";
        String date() default "unknown";
    }

    @EnhancementRequest(
        id = 101,
        synopsis = "Add login authentication"
    )
    class LoginFeature {}

    @EnhancementRequest(
        id = 102,
        synopsis = "Implement voting system",
        engineer = "Wisdom",
        date = "2025-09-14"
    )
    class VotingFeature {}
}
