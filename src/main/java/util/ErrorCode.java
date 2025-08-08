package util;

public enum ErrorCode {
    PRODUCT_NOT_FOUND("E404", "Producto no encontrado"),
    VALIDATION_ERROR("E400", "Error de validación"),
    DATABASE_ERROR("E500", "Error de base de datos");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return "[" + code + "] " + message;
    }
}
