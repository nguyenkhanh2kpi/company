package com.example.project.core.control;

import com.example.project.core.Routes;
import javafx.stage.Stage;

public class LeaveRequestControl {

    Routes routes = new Routes(new Stage());

    public void onAddClick() {
        routes.goToLeaveRequest();
    }

    public void onViewClick() {
        routes.viewOrEditLeave();
    }

}
