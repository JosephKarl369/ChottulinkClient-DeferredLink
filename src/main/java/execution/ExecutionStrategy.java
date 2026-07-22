package execution;

public interface ExecutionStrategy {

    void beforeSuite();

    void beforeMethod();

    void afterMethod();

    void afterSuite();

}