module com.snakerealms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.prefs;

    exports com.snakerealms.main;
    exports com.snakerealms.core;
    exports com.snakerealms.entities;
    exports com.snakerealms.realms;
    exports com.snakerealms.ui;

    opens com.snakerealms.main to javafx.fxml;
    opens com.snakerealms.realms to javafx.fxml;
}
