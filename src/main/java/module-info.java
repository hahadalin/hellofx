/**
 * @author hahadalin
 * @date 2019/1/1
 */
module hellofx {
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;

    opens cn.dalin to javafx.fxml;
    exports cn.dalin;
}
