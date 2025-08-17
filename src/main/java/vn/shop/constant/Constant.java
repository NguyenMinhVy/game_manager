package vn.shop.constant;


public interface Constant {

    interface API {
        String ADMIN = "/api/admin";
        String PUBLIC = "/api/public";
    }

    interface RESPONSE_STATUS {
        int SUCCESS = 200;
        int BAD_REQUEST = 400;
        int UNAUTHORIZED = 401;
        int FORBIDDEN = 403;
        int NOT_FOUND = 404;
        int INTERNAL_SERVER_ERROR = 500;
    }

    interface RESPONSE_MESSAGE {
        String SUCCESS = "SUCCESS";
        String BAD_REQUEST = "BAD_REQUEST";
        String UNAUTHORIZED = "UNAUTHORIZED";
        String FORBIDDEN = "FORBIDDEN";
        String NOT_FOUND = "NOT_FOUND";
        String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
        String Account_already_exists ="Tài khoản đã tồn tại";
    }
}
