package optimizations;

public class UninitializedStartError extends Exception{
    public UninitializedStartError(String errorMessage){
        super(errorMessage);
    }
}