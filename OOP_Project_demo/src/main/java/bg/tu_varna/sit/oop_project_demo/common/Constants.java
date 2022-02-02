package bg.tu_varna.sit.oop_project_demo.common;

public class Constants {
    public static class View{
        public static final String HELLO_VIEW = "/bg/tu_varna/sit/oop_project_demo/presentation.views/hello-view.fxml";
        public static final String ADMIN_LOGIN = "/bg/tu_varna/sit/oop_project_demo/presentation.views/login/admin-login.fxml";
        public static final String DISTRIBUTOR_LOGIN = "/bg/tu_varna/sit/oop_project_demo/presentation.views/login/distributor-login.fxml";
        public static final String COMPANY_LOGIN = "/bg/tu_varna/sit/oop_project_demo/presentation.views/login/company-login.fxml";
        public static final String CASHIER_LOGIN = "/bg/tu_varna/sit/oop_project_demo/presentation.views/login/cashier-login.fxml";
        public static final String ADMIN_VIEW = "/bg/tu_varna/sit/oop_project_demo/presentation.views/userviews/admin-view.fxml";
        public static final String CASHIER_VIEW = "/bg/tu_varna/sit/oop_project_demo/presentation.views/userviews/cashier-view.fxml";
        public static final String COMPANY_VIEW = "/bg/tu_varna/sit/oop_project_demo/presentation.views/userviews/company-view.fxml";
        public static final String DISTRIBUTOR_VIEW = "/bg/tu_varna/sit/oop_project_demo/presentation.views/userviews/distributor-view.fxml";
        public static final String ADD_CLIENTS = "/bg/tu_varna/sit/oop_project_demo/presentation.views/admin-clients.fxml";
        public static final String ADD_CASHIER = "/bg/tu_varna/sit/oop_project_demo/presentation.views/add/add-cashier.fxml";
        public static final String ADD_DISTRIBUTOR = "/bg/tu_varna/sit/oop_project_demo/presentation.views/add/add-distributor.fxml";

    }

    public static class Configurations {
        public static final String LOG4J_PROPERTIES = "/bg/tu_varna/sit/oop_project_demo/configuration/log4j.properties";
    }

    public static class Values {
        public static final String Title = "Transport app";
    }

    public static class User {
        public static int trackUser = 0;
    }
}
