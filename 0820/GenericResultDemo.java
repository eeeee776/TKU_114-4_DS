class Result<T> {
    private final boolean success;
    private final String message;
    private final T data;

    // 成功時的建構子
    public Result(T data, String message) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    // 失敗時的建構子 (data 為 null)
    public Result(String message) {
        this.success = false;
        this.message = message;
        this.data = null;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}

public class GenericResultDemo {
    public static void main(String[] args) {
        // 測試 String 型態
        Result<String> stringResult = new Result<>("Java Generics", "查詢成功");
        if (stringResult.isSuccess()) {
            // 不需轉型，直接使用 String 方法
            System.out.println("成功：" + stringResult.getData().toUpperCase());
        }

        // 測試 Integer 型態 (失敗情境)
        Result<Integer> intResult = new Result<>("找不到該筆資料");
        if (!intResult.isSuccess()) {
            System.out.println("失敗原因：" + intResult.getMessage());
            System.out.println("資料為 null 嗎？ " + (intResult.getData() == null));
        }
    }
}