class Result<T> {
    private boolean success;
    private String message;
    private T data;

    Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    boolean isSuccess() {
        return success;
    }

    String getMessage() {
        return message;
    }

    T getData() {
        return data;
    }
}

public class GenericResultDemo {
    public static void main(String[] args) {
        Result<String> stringResult = new Result<>(true, "Success", "Hello Generic");
        Result<Integer> intResult = new Result<>(false, "Not Found", null);

        System.out.println(stringResult.isSuccess() + " | " + stringResult.getMessage() + " | " + stringResult.getData());
        System.out.println(intResult.isSuccess() + " | " + intResult.getMessage() + " | " + intResult.getData());
    }
}