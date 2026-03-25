import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Service;

@Service
public class MyServiceCompletableFut {

    private final MyRepository myRepository;
    private final ExecutorService executor = Executors.newFixedThreadPool(10); // Thread pool for async calls

    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public CompletableFuture<MyEntity> getEntityByIdAsync(Long id) {
        // Run the repository call asynchronously
        return CompletableFuture.supplyAsync(() -> myRepository.findById(id).orElse(null), executor);
    }
}